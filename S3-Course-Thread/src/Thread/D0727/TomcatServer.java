package Thread.D0727;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TomcatServer {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket serversocket = new ServerSocket(8080);
		System.out.println("TOMCAT服务器启动，端口8080");
		
		boolean running = true;
		while(running) {
			Socket socket = serversocket.accept();
			new Thread() {
				public void run() {
					try {
						System.out.println("接收到请求");
						InputStream in = socket.getInputStream();
						OutputStream out = socket.getOutputStream();
						/*
						 * 浏览器端接收
						 */
						byte [] buffer = new byte[1024];
						int count;
						count = in.read(buffer);
						if( count > 0 ) {
							String requestText = new String(buffer,0,count);
							System.out.println(requestText);
							/*
							 * 解析出请求资源路径
							 */
							String[] lines = requestText.split("\\n");
							String[] requestLines = lines[0].split("\\s");
							String webpath = requestLines[1];
							String contentType;
							int statusCode = 200;
							String path = "D:/JavaStudyFiles/Season3/"+webpath;
							File file = new File(path);
							if(!file.exists()) {
								statusCode = 404;
								path = "D:/JavaStudyFiles/Season3/photo/404.html";
							}
							if(webpath.endsWith(".js")) {
								contentType = "application/javascript; charset=utf-8";
							} else if(webpath.endsWith(".css")) {
								contentType = "text/css; charset=utf-8";
							} else {
								//图片可以返回html，浏览器会自动识别
								contentType = "text/html; charset=utf-8";
							}
							
							/*
							 * 发送给浏览器
							 */
							//响应头行
							out.write(("HTTP/1.1 " + statusCode + " OK\n").getBytes());
							//响应头域
							out.write(("Content-Type: " + contentType + "\n").getBytes());
							//空行
							out.write("\n".getBytes());
							//实体
							//out.write("<h1>hello</h1>".getBytes());
							
							FileInputStream fis = new FileInputStream(path);
							while( (count = fis.read(buffer)) > 0 ) {
								out.write(buffer,0,count);
							}					
							fis.close();
						}
						socket.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}	
			serversocket.close();
	}
	
}
