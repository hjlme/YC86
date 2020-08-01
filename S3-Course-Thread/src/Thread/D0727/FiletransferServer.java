package Thread.D0727;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FiletransferServer {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket serversocket = new ServerSocket(7777);
		System.out.println("服务器已经启动.....正在等待连接");
		Socket socket = serversocket.accept();
		System.out.println("接收到客服端的连接");

		//要保存到的路径
		String path = "D:/JavaStudyFiles/testSuccess.txt";
		//文件操作流
		InputStream in = socket.getInputStream();//从客户端获取文件
		FileOutputStream fout = new FileOutputStream(path);//保存到本地
		DataInputStream dis = new DataInputStream(in);
        DataOutputStream dos = new DataOutputStream(fout);
		//读取文件数据
		byte[] buffer = new byte[1024];
		int count;
		while((count = dis.read(buffer)) > 0) {
			dos.write(buffer,0,count);
		}
		dos.flush();
		System.out.println("接受成功！");
		dis.close();
		dos.close();
		serversocket.close();
	}
}
