package Thread.D0730;

import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		/*
		 * http://127.0.0.1:8080/photo/hello?name=john
		 * 页面输出 hello john
		 * 请求实现请求解析参数
		 */
		String name = request.getParameter("name");
		PrintWriter out = response.getWriter();
		//输出到页面
		out.print("<h1>hello "+name+"</h1>");
		//控制台
		System.out.println("hello world");
	}
}
