package cn.sitedev.lesson.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface MyRpcService {

    /**
     * 服务类型
     *
     * @return
     */
    Class<?> type();

    /**
     * 版本号
     *
     * @return
     */
    String version() default "";
}
