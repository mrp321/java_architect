package cn.sitedev.lesson.demo.first;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// @ComponentScan注解默认扫描当前包下所有子包
@ComponentScan(basePackages = "cn.sitedev.lesson.demo.first")
public class ConfigurationDemo {
    @Bean
    public DemoClass demoClass() {
        return new DemoClass();
    }
}
