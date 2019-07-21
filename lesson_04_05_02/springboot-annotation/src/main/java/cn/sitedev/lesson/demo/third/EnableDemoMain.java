package cn.sitedev.lesson.demo.third;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDefineService(exclude = {LoggerService.class})
public class EnableDemoMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(EnableDemoMain.class, args);

        // 从容器中获取CacheService
        try {
            System.out.println(applicationContext.getBean(CacheService.class));
        } catch (BeansException e) {
            e.printStackTrace();
        }
        // 从容器中获取LoggerService
        try {
            System.out.println(applicationContext.getBean(LoggerService.class));
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
