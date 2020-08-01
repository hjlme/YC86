package Thread.D0727;

import java.io.*;
import java.net.Socket;

public class FiletransferCilent {
	
	public static void main(String[] args) throws IOException {
		
		Socket socket = new Socket("127.0.0.1",7777);
		System.out.println("开始发送");
		
		//本地的文件路径,自己建一个文件
		String path = "D:/JavaStudyFiles/test.txt";
		//从本地获取文件
		FileInputStream fis = new FileInputStream(path);
		//获取输出流
		OutputStream out = socket.getOutputStream();
		//本地文件转化为数据流
		DataInputStream dis = new DataInputStream(fis);
		//发送的文件转化成数据流
        DataOutputStream dos = new DataOutputStream(out);
		/*读取文件数据*/
        byte[] buffer = new byte[1024];
		int count;
		while((count = fis.read(buffer)) > 0) {
			dos.write(buffer,0,count);
		}
		dos.flush();//传输数据
		System.out.println("发送成功");
		dis.close();
		dos.close();
		socket.close();
	}
}
