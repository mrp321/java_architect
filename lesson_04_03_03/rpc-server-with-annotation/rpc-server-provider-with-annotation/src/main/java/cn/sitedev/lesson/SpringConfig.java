package cn.sitedev.lesson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "cn.sitedev")
public class SpringConfig {
    @Bean(name = "rpcServer")
    public RpcServer rpcServer() {
        return new RpcServer(8080);
    }
}
