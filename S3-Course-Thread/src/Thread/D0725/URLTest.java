package Thread.D0725;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;


public class URLTest {
	
	public static void main(String[] args) throws IOException {
		
		URL url = new URL("http://www.hyycinfo.com");
		
		System.out.println("协议"+url.getProtocol());
		System.out.println("端口"+url.getPort());
		System.out.println("资源路径"+url.getPath());
		System.out.println("域名"+url.getHost());
		System.out.println("资源名"+url.getFile());
		System.out.println("地址中参数"+url.getQuery());
		
		URLConnection conn = url.openConnection();
		
		System.out.println("最后修改时间:"+conn.getLastModified());
		System.out.println("目标资源大小:"+conn.getContentLengthLong());
		System.out.println("目标资源的类型:"+conn.getContentType());
		
		System.out.println("======================================");
		
		InputStream in = conn.getInputStream();
		byte[] buffer = new byte[1024];
		int count;
		while((count = in.read(buffer)) >0 ) {
			System.out.println(new String(buffer,0,count));
		}
		in.close();
	}
}


