import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class FileTransferProtocolClient {

    public static void main(String[] args) {
        final String serverAddress = "localhost";
        final int serverPort = 5555;

        String fileName = "\"C:\\Users\\vivek\\OneDrive\\Desktop\\Books\"";

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connected to server: " + socket);

            sendFile(socket, fileName);
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }

    private static void sendFile(Socket socket, String fileName) {
        try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             FileInputStream fis = new FileInputStream(fileName)) {

            dos.writeUTF(fileName);

            long fileSize = fis.available();
            dos.writeLong(fileSize);

            System.out.println("Sending file: " + fileName + " (" + fileSize + " bytes)");

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully.");
        } catch (IOException e) {
            System.err.println("Error sending file: " + e.getMessage());
        }
    }
}
