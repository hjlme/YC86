package Thread.D0725;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cilent {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		@SuppressWarnings("resource")
		Socket socket = new Socket("127.0.0.1",6666);
		
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
