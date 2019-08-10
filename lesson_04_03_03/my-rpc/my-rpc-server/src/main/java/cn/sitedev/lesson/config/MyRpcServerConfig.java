package cn.sitedev.lesson.config;

import cn.sitedev.lesson.MyRpcServerRemote;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "cn.sitedev")
public class MyRpcServerConfig {
    @Bean
    public MyRpcServerRemote myRpcServerRemote() {
        return  new MyRpcServerRemote(8080);
    }
}
