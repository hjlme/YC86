package Thread.D0729;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Tomcat {
	//Servlet容器
	private HashMap<String,Servlet> servletMap;
	
	public void startup() throws IOException {
		//服务器初始化SErvlet容器==》Map（URL，Servlet对象）
		servletMap = new HashMap<>();
		servletMap.put("/photo/hello", new HelloServlet());
		
		ServerSocket tomcat = new ServerSocket(8080);
		System.out.println("TOMCAT服务器启动，端口8080,等待连接....");
		
		boolean running = true;
		while(running) {
			System.out.println("已接受到客户端的连接...");
			Socket socket = tomcat.accept();
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
							//打印请求报文
							String requestText = new String(buffer,0,count);
							System.out.println(requestText);
							
							//解析请求报文
							HttpServletRequest request = buildRequest(requestText);
							HttpServletResponse response = new HttpServletResponse(out);
							//使用资源地址区分动态请求和静态请求
							//使用资源地址到Servlet容器中获取Servlet对象
							String uri = request.getRequestURI();
							Servlet servlet = servletMap.get(uri);
							if(servlet != null) {
								//使用Servlet处理动态请求
								servlet.service(request, response);
							} else {
								//如果没有找到对应的Servlet对象，视为静态请求
								//处理静态请求，判断资源是否存在，不存在返回404
								processStaticRequest(request,out);
							}
						}
						socket.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}

			}.start();
		}	
			tomcat.close();
	}
	
	/*
	 * 处理静态请求的方法
	 */
	public void processStaticRequest(HttpServletRequest request,OutputStream out) throws IOException {
		String webpath = request.getRequestURI();
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
		FileInputStream fis = new FileInputStream(path);
		byte [] buffer = new byte[1024];
		int count;
		while( (count = fis.read(buffer)) > 0 ) {
			out.write(buffer,0,count);
		}					
		fis.close();
	}
	
	public void shutdown() {
		
	}
	
	private HttpServletRequest buildRequest(String requestText) {
		return new HttpServletRequest(requestText);
	}
	
	public static void main(String[] args) throws IOException {
		new Tomcat().startup();
	}
}
