package com.yc.springcloud81.service;

import com.yc.springcloud81.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookServiceImp1 implements BookService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Book getBook(Integer id) {
        //远程调用mis-provider中的服务逻辑.
        //   这里采用通用服务名来自动查找这个服务名下的服务列表，并通过ribbon实现自动负载均衡
       return restTemplate.getForObject("http://application/book/"+id,Book.class);
    }
}
