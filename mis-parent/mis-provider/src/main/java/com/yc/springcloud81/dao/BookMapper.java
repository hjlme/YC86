package com.yc.springcloud81.dao;

import com.yc.springcloud81.bean.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends MisBaseMapper<Book>{
}
