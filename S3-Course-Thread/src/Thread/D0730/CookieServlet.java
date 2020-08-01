package Thread.D0730;


public class CookieServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		
		Cookie cookie = new Cookie("name","wusong");
		cookie.setMaxAge(600);
		response.addCookie(cookie);
		response.getWriter().append("Add Cookie Success!");
	}
	

}
