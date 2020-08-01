package Thread.D0726;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

/**
 * 从本地读取文件，然后向服务器上传文件
 *
 */
public class TransferfileCilent {
    private String host = "localhost";
    private int port = 8888;
    private Socket socket;
    private static String fileName="D:/JavaStudyFiles/test.txt";

    public TransferfileCilent() throws IOException {
        socket = new Socket(host, port);
    }

    private void sendFile(String filePath) {

            while (true) {

                try {
                    File file = new File(filePath);
                    System.out.println("文件大小：" + file.length() + "kb");
                    DataInputStream dis = new DataInputStream(new FileInputStream(filePath));
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    byte[] buf = new byte[1024 * 9];
                    int len = 0;
                    while ((len = dis.read(buf)) != -1) {
                        dos.write(buf, 0, len);

                    }
                    dos.flush();
                    System.out.println("文件上传结束，，，，");
                    dis.close();
                    dos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
    }

    public static void main(String[] args) throws IOException {
        new TransferfileCilent().sendFile(fileName);
    }

}
