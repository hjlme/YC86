package com.yc.favorites.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.yc.favorites.bean.Favorite;

public interface FavoriteMapper {
	
	@Insert("insert into favorite values(null,#{flabel},#{furl},#{fdesc},#{ftags})")
	@Options(keyProperty="fid",keyColumn="fid",useGeneratedKeys=true)
	int insert(Favorite f);

	/**
	 * tid == null 查未分类
	 * tid > 0 查指定分类
	 * tid = 0 查全部
	 * @param tid
	 * @return
	 */
	List<Favorite> selectBytid(@Param("tid")Integer tid);
}
