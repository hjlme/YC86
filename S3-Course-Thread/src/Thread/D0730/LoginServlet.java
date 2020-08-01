package Thread.D0730;


public class LoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		response.getWriter().append("用户名<input value='"+cookies[0].getValue()+"'><br>");
		response.getWriter().append("密码<input value=''><br>");
		response.getWriter().append("登录<input type='button' value='登录'><br>");
	}
	

}
