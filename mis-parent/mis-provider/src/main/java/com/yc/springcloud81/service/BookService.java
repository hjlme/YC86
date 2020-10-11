package com.yc.springcloud81.service;

import com.yc.springcloud81.bean.Book;
import com.yc.springcloud81.dao.BookMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Service
public class BookService {

    @Resource
    private BookMapper bm;


    public Book getBook(Integer id){
        return bm.selectByPrimaryKey(id);
    }
}
