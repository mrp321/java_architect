package cn.sitedev.lesson.config;

import cn.sitedev.lesson.MyRpcClientRemote;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRpcClientConfig {
    @Bean
    public MyRpcClientRemote myRpcClientRemote() {
        return new MyRpcClientRemote("localhost", 8080);
    }
}
