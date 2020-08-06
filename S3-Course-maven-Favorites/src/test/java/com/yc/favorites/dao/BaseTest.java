package com.yc.favorites.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yc.favorites.FavoriteBiz;
import com.yc.favorites.bean.Favorite;
import com.yc.favorites.util.MyBatisHelper;

public class BaseTest {
	
	@Test
	public void test1() {
		
		new MyBatisHelper();
		SqlSession session = MyBatisHelper.openSession();
		
		FavoriteMapper fmapper = session.getMapper(FavoriteMapper.class);
		Favorite ft = new Favorite();
		ft.setFlabel("淘宝");
		ft.setFdesc("败家娘们最喜欢的网址");
		ft.setFurl("taobao.com");
		ft.setFtags("生活");
		fmapper.insert(ft);
		session.commit();
		session.close();
	}
	
	@Test
	public void test2() {
	
		FavoriteBiz fb = new FavoriteBiz();
		Favorite f = new Favorite();
		f.setFlabel("淘宝");
		f.setFdesc("败家娘们最喜欢的网址");
		f.setFurl("taobao.com");
		f.setFtags("生活;资讯");
		fb.addFavorite(f);
	}
	
	@Test
	public void test3() {
		FavoriteBiz fb = new FavoriteBiz();
		Favorite f = new Favorite();
		f.setFlabel("腾讯");
		f.setFdesc("败家肥宅最喜欢的网址");
		f.setFurl("tenxun.com");
		f.setFtags("游戏,生活，资讯");
		fb.addFavorite(f);
	}
}
