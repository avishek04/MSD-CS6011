import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ChatMessage {
    private DataInputStream inputStream;
    private boolean finalMsg;
    private int opCode;
    private boolean maskPresent;
    private byte[] maskKey;
    private int payLoadLength;
    private String payLoad;

    ChatMessage(InputStream inStream) {
        try {
            inputStream = new DataInputStream(inStream);

            AnalyzeFirstByte();
            AnalyzeSecondByte();
            SetMask();
            SetPayload();
        }
        catch (Exception ex) {

        }
    }
    public String GetPayloadMessage() {
        return payLoad;
    }
    public void SetPayload() {
        try {
            //byte[] payloadBytes = inputStream.readNBytes((int) payLoadLength);
            byte[] decodedPayload = new byte[payLoadLength];
            //byte[] decodedPayload = Uint8Array.from(payloadBytes, (elt, i) => elt ^ mask[i % 4]);
            for (int i = 0; i < payLoadLength; i++) {
                decodedPayload[i] = (byte) (inputStream.readByte() ^ maskKey[i % 4]);
            }
            ByteBuffer wrapped = ByteBuffer.wrap(decodedPayload);
            payLoad = new String(wrapped.array(), StandardCharsets.UTF_8);
            //System.out.println(Arrays.toString(decodedPayload));
            //payLoad = wrapped.toString();
        } catch (Exception e) {

        }
    }
    private void SetMask() {
        if (maskPresent) {
            try {
                maskKey = inputStream.readNBytes(4);
//                ByteBuffer wrapped = ByteBuffer.wrap(maskBytes);
//                maskKey = wrapped.getInt();
            } catch (Exception e) {

            }
        }
    }
    private void AnalyzeSecondByte() {
        try {
            byte secondByte = inputStream.readByte();
            maskPresent = ((secondByte & 0x80) != 0);
            int payloadLen = secondByte & 0x7F;
            if (payloadLen <= 125) {
                payLoadLength = payloadLen;
            } else if (payloadLen == 126) {
                payLoadLength = inputStream.readShort();
            }
            else {
                payLoadLength = (int)inputStream.readLong(); // 1
            }
        }
        catch (Exception ex) {

        }
    }
    private void AnalyzeFirstByte() {
        try {
            byte firstByte = inputStream.readByte();
            finalMsg = (firstByte & 0x80) != 0;
            opCode = firstByte & 0x0F;
        }
        catch (Exception ex) {

        }
    }
}
