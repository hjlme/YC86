package Thread.D0725;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Server {
	
	public static void main(String[] args) throws IOException {
		
		@SuppressWarnings("resource")
		ServerSocket scsk = new ServerSocket(6666);
		System.out.println("服务器启动成功，端口号为6666");
		Socket socket = scsk.accept();
		
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		new Thread() {
			public void run() {
				byte [] buffer = new byte [1024];
				int count;
				while(true) {
					try {
						count = in.read(buffer);
						System.out.println("他说:"+new String(buffer,0,count));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				while(true) {
					try {
						System.out.println("我说:");
						out.write(sc.nextLine().getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
