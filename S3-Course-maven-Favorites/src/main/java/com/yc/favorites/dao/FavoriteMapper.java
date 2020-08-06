package com.yc.favorites.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.yc.favorites.bean.Favorite;

public interface FavoriteMapper {
	
	@Insert("insert into favorite values(null,#{flabel},#{furl},#{fdesc},#{ftags})")
	@Options(keyProperty="fid",keyColumn="fid",useGeneratedKeys=true)
	int insert(Favorite f);
}
