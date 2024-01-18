import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransferProtocolServer {

    public static void main(String[] args) {
        final int portNumber = 5555;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server waiting for client on port " + portNumber);

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client connected: " + socket);

                    receiveFile(socket);
                } catch (IOException e) {
                    System.err.println("Error accepting connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating server socket: " + e.getMessage());
        }
    }

    private static void receiveFile(Socket socket) {
        try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
            String fileName = dis.readUTF();
            long fileSize = dis.readLong();

            System.out.println("Receiving file: " + fileName + " (" + fileSize + " bytes)");

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = dis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

                System.out.println("File received successfully.");
            } catch (IOException e) {
                System.err.println("Error writing file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error receiving file: " + e.getMessage());
        }
    }
}
