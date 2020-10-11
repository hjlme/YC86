package com.yc.springcloud81.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//一个配置类:由spring托管
@SpringBootConfiguration
public class RestTemplateConfig {

    @Bean            //创建一个 restTemplate 的模板操作对象
    @LoadBalanced   //启用ribbon的客户端负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
