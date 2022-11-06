import com.google.gson.Gson;

import java.util.ArrayList;

public class ChatResponse {
    public String inputMsg;
    public Message outputMsg;
    public static ArrayList<Room> rooms;
    public byte[] responseFrame;
    ChatResponse(ChatMessage chatMessage) {
        inputMsg = chatMessage.GetPayloadMessage();
        HandleMessage();
        responseFrame = GenerateResponseFrame();
    }

    private byte[] GenerateResponseFrame() {
        Gson gson = new Gson();
        String jsonMsg = gson.toJson(outputMsg);
        var payloadSize = jsonMsg.getBytes().length;
        int payloadLen;
        byte secBit;
        byte thirdBit = 0x00;
        byte fourthBit = 0x00;
        if (payloadSize < 126) {
            payloadLen = 1;
            secBit = (byte) (payloadSize & 0x7F);
        }
        else if (payloadSize < Math.pow(2, 16)) {
            payloadLen = 3;
            secBit = 126 & 0x7F;
            thirdBit = (byte) (((short) payloadSize) >> 8);
            fourthBit = (byte) (((short) payloadSize) & 0x00FF);
        }
        else {
            payloadLen = 9;
            secBit = 127 & 0x7F;
        }

        byte[] responseFrame = new byte[1 + payloadLen + payloadSize];
        responseFrame[0] = (byte) 0x81  ;
        responseFrame[1] = secBit;
        if (payloadLen == 3) {
            responseFrame[2] = thirdBit;
            responseFrame[3] = fourthBit;
        }
        else if (payloadLen == 9) {
            for (int i = 2; i < 9; i++) {
                responseFrame[i] = (byte) (payloadLen >> (64- (8*(i-1))) & 0xff);
            }
        }

        for (int i = payloadLen + 1, j = 0; i < responseFrame.length; i++, j++) {
            responseFrame[i] = jsonMsg.getBytes()[j];
        }
        return responseFrame;
    }
    private synchronized void AddUserToRoom(String[] msg) {
        String userName = msg[0];
        String roomName = msg[1];

        if (rooms != null) {
            for (Room room: rooms) {
                if (room.roomName.equals(roomName)) {
                    //outputMsg = "Room " + roomName + " already present. ";
                    if (room.addMember(userName)) {
                        //outputMsg += userName + " is added to the room.";
                        outputMsg = new Message();
                        outputMsg.type = "join";
                        outputMsg.user = userName;
                        outputMsg.room = roomName;
                        outputMsg.message = "Member is added to the room";
                    }
                    else {
                        //outputMsg += userName + " already present in the room.";
                        outputMsg = new Message();
                        outputMsg.type = "Error";
                        outputMsg.user = userName;
                        outputMsg.room = roomName;
                        outputMsg.message = "Member is already present in the room";
                    }
                    return;
                }
            }
        }
        else {
            rooms = new ArrayList<>();
        }
        Room rm = new Room(roomName, userName);
        rooms.add(rm);
        outputMsg = new Message();
        outputMsg.type = "join";
        outputMsg.user = userName;
        outputMsg.room = roomName;
        outputMsg.message = "Member is added to the new room created";
        //outputMsg = "Room " + roomName + "is created and user " + userName + " is added to the room.";
    }

    private synchronized void RemoveUserFromRoom(String[] msg) {
        String userName = msg[0];
        String roomName = msg[1];
        if (rooms != null) {
            for (Room room: rooms) {
                if (room.roomName.equals(roomName)) {
                    if (room.removeMember(userName)) {
                        //outputMsg = userName + " has been removed from the room.";
                        outputMsg = new Message();
                        outputMsg.type = "leave";
                        outputMsg.user = userName;
                        outputMsg.room = roomName;
                        outputMsg.message = "Member has left the room";
                    }
                    else {
                        //outputMsg = "There is no member with the name " + userName + " in the room";
                        outputMsg = new Message();
                        outputMsg.type = "error";
                        outputMsg.user = userName;
                        outputMsg.room = roomName;
                        outputMsg.message = "There is no member with that name in this room";
                    }
                    return;
                }
            }
        }
            outputMsg = new Message();
            outputMsg.type = "error";
            outputMsg.user = userName;
            outputMsg.room = roomName;
            outputMsg.message = "There is no room with that name";
        //outputMsg = "There is no room with the name " + roomName;
    }

    private void getRoomList(String roomName) {
        if (roomName != null) {
            for (Room room: rooms) {
                if (room.roomName.equals(roomName)) {
                    outputMsg = new Message();
                    outputMsg.type = "roomlist";
                    outputMsg.room = roomName;
                    outputMsg.message = room.members.toString();
                }
            }
        }
    }
    private boolean userPresent(String userName, String roomName) {
        if (rooms != null) {
            for (Room room: rooms) {
                if (room.roomName.equals(roomName) && room.members.contains(userName)) {
                    return true;
                }
            }
        }
        return false;
    }
    private void BroadcastTxtMsg(String rmName, String[] msg) {
        String userName = msg[0];
        String text = msg[1];
        if (userPresent(userName, rmName)) {
            //outputMsg = userName + ": " + text;
            outputMsg = new Message();
            outputMsg.type = "message";
            outputMsg.user = userName;
            outputMsg.room = rmName;
            outputMsg.message = text;
        }
        else {
            //outputMsg = "Please enter correct username: " + userName;
            outputMsg = new Message();
            outputMsg.type = "error";
            outputMsg.user = userName;
            outputMsg.room = "";
            outputMsg.message = "Member is not present";
        }
    }

    public void HandleMessage() {
        var msg = inputMsg.split(" ", 2);
        if (msg[0].equals("join")) {
            AddUserToRoom(msg[1].split(" "));
        } else if (msg[0].equals("leave")) {
            RemoveUserFromRoom(msg[1].split(" "));
        } else if (msg[0].equals("roomlist")) {
            getRoomList(msg[1]);
        } else {
            BroadcastTxtMsg(msg[0], msg[1].split(" ", 2));
        }
    }
}
