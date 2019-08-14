package cn.sitedev.lesson;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        applicationContext.start();

    }
}
