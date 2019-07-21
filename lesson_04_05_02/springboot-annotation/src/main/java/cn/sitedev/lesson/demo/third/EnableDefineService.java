package cn.sitedev.lesson.demo.third;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自定义一个注解, 用来排除bean
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CacheImportSelector.class, LoggerDefineRegistrar.class})
public @interface EnableDefineService {
    // 要排除的bean
    Class<?>[] exclude() default {};
}
