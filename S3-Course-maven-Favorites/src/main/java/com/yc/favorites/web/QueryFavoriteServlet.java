package com.yc.favorites.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.yc.favorites.dao.FavoriteMapper;
import com.yc.favorites.util.MyBatisHelper;

@WebServlet("/QueryFavorite.do")
public class QueryFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		SqlSession session = MyBatisHelper.openSession();
		FavoriteMapper fm = session.getMapper(FavoriteMapper.class);
		
		String stid = request.getParameter("tid");
		Integer tid;
		if(stid.trim().isBlank() || stid==null || "null".equals(stid)) {
			tid = null;
		} else {
			tid = Integer.valueOf(stid);
		}
		Gson gson = new Gson();
		String json = gson.toJson(fm.selectBytid(tid));
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
