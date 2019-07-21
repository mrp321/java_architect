package cn.sitedev.lesson.demo.second.current;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SecondMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        String[] defs = applicationContext.getBeanDefinitionNames();
        for (String def : defs) {
            System.out.println(def);
        }
    }
}
