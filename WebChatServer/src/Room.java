import java.util.ArrayList;

public class Room {
    public String roomName;
    public ArrayList<String> members;
    Room(String rmName, String userName) {
        roomName = rmName;
        members = new ArrayList<>();
        members.add(userName);
    }

    public boolean addMember(String userName) {
        for (String member: members) {
            if (member.equals(userName)) {
                return false;
            }
        }
        members.add(userName);
        return true;
    }

    public boolean removeMember(String userName) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).equals(userName)) {
                members.remove(i);
                return true;
            }
        }
        return false;
    }
}
