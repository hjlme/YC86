package Thread.D0730;


public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		/*
		 * http://127.0.0.1:8080/index.html
		 * 设置响应重定向
		 */
		response.sendRedirect("/photo/index.html");
	}
}
