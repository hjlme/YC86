package com.yc.favorites.dao;

import org.apache.ibatis.annotations.Insert;

import com.yc.favorites.bean.TagFavorite;

public interface TagFavoriteMapper {
	
	@Insert("insert into tagfavorite values(#{tid},#{fid})")
	int insert(TagFavorite tf);
}
