package cn.sitedev.lesson.demo.first;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationDemo.class);
        // 通过DL(dependency lookup) 在Spring容器中查找指定的bean
//        DemoClass demoClass = applicationContext.getBean(DemoClass.class);
//        demoClass.demo();
        String[] defs = applicationContext.getBeanDefinitionNames();
        for (String def : defs) {
            System.out.println(def);
        }
    }
}
