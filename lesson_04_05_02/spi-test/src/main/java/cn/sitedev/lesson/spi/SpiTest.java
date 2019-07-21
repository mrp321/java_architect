package cn.sitedev.lesson.spi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpiTest {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpiTest.class, args);
        // 从容器中获取bean SpiCore, 并调用其方法
        System.out.println(applicationContext.getBean(SpiCore.class).testSpi());
    }
}
