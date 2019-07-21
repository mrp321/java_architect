package cn.sitedev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @GetMapping
    public String order() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        System.out.println("开始创建订单");
        restTemplate.put("http://localhost:8888/repo/{pid}", null, 1001);
        return "SUCCESS";
    }
}
