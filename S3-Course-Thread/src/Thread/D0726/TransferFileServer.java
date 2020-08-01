package Thread.D0726;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接受客户端发来的文件，然后保存在本地
 * 
 */
public class TransferFileServer {

    private int port = 8888;
    private ServerSocket serverSocket;
    private static String fileName="D:/JavaStudyFiles/testtest.txt";

    public TransferFileServer() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("服务器已经启动，，，，，，");
    }

    private void receieveFile(String filePath) {
        while (true) {
            try {
                Socket socket = null;

                socket = serverSocket.accept();
                System.out.println("接收到客户端的连接，，，，");

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath));

                byte[] buf = new byte[1027 * 9];
                int len = 0;

                while ((len = dis.read(buf)) != -1) {
                    dos.write(buf, 0, len);
                }
                dos.flush();

                System.out.println("文件接受结束，，，，");
                dis.close();
                dos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws IOException {
        new TransferFileServer().receieveFile(fileName);
    }

}
