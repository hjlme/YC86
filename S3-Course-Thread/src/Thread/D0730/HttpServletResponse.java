package Thread.D0730;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpServletResponse {
	//通过Socket获取的输出流
	private OutputStream out;
	private int status;
	private String msg;
	
	//存放头域键值对的集合
	private Map<String,String> headerMap = new HashMap<>();
	//存放cookie
	private List<Cookie> cookieList = new ArrayList<>();	
	
	public HttpServletResponse(OutputStream out) {
		this.out = out;
	}
	//资源流字符串输出流，将输出内容保存到一个字符串中
	private CharArrayWriter caw = new CharArrayWriter();
	//处理流
	private PrintWriter pw = new PrintWriter(caw);
	/*
	 * 获取响应输出流，临时保存servlet输出的内容
	 */
	public PrintWriter getWriter() {
		return pw;
	}
	/**设置结果码，结果码消息
	 * @param status 状态码
	 * @param msg 状态码说明
	 */
	public void setStatus(int status,String msg) {
		if(this.status==0) {
			this.status = status;
			this.msg = msg;
		}
	}
	
	/*
	 * 将响应报文推送给浏览器
	 */
	public void flushBuffer() throws IOException {
		//响应头行
		out.write(("HTTP/1.1 " + status + " " + msg + "\n").getBytes());
		//响应头域
		out.write(("Content-Type: text/html; charset=utf-8\n").getBytes());
		//将头域集合中的值写入响应报文
		for(Entry<String,String> entry: headerMap.entrySet()) {
			out.write((entry.getKey() + ": " + entry.getValue() + "\n").getBytes());
		}
		//迭代器循环
		for (Iterator<Cookie> iterator = cookieList.iterator(); iterator.hasNext();) {
			Cookie cookie = iterator.next();
			out.write(cookie.toString().getBytes());
		}
		//空行
		out.write("\n".getBytes());
		//实体
		out.write(caw.toString().getBytes());
	}
	/**
	 * 响应重定向
	 * @param uri 跳转的地址
	 */
	public void sendRedirect(String uri) {
		//写结果码 301 或者 302
		setStatus(301, "Redirect");
		//在头域中写入Location：要跳转的页面
		headerMap.put("Location", uri);
	}
	public void addCookie(Cookie cookie) {
		cookieList.add(cookie);
	}
}
