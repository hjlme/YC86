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
	
	@Test
	public void test4() {
		SqlSession session = MyBatisHelper.openSession();
		FavoriteMapper fm = session.getMapper(FavoriteMapper.class);
		fm.selectBytid(null);
		fm.selectBytid(1);
		fm.selectBytid(0);
	}
	
	@Test
	public void test5() {
		FavoriteBiz fb = new FavoriteBiz();
		Favorite f = new Favorite();
		f.setFlabel("bilibli");
		f.setFdesc("你喜欢的视频都在b站");
		f.setFurl("bilibili.com");
		f.setFtags("");
		fb.addFavorite(f);
	}
	
	@Test
	public void test6() {
		FavoriteBiz fb = new FavoriteBiz();
		Favorite f = new Favorite();
		f.setFlabel("虎牙");
		f.setFdesc("你喜欢看的电视剧都在虎牙");
		f.setFurl("huya.com");
		f.setFtags("直播，游戏，电视剧，电影");
		fb.addFavorite(f);
	}
}
