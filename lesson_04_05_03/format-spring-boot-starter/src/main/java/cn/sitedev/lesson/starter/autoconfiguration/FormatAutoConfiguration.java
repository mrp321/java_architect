package cn.sitedev.lesson.starter.autoconfiguration;

import cn.sitedev.lesson.starter.format.FormatProcessor;
import cn.sitedev.lesson.starter.format.JsonFormatProcessor;
import cn.sitedev.lesson.starter.format.StringFormatProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 格式化Bean自动配置类
 */
//@Configuration
public class FormatAutoConfiguration {

    /**
     * 字符串方式格式化Bean
     *
     * @return
     */
    // 在classpath下无JSON类的时候生效
    @ConditionalOnMissingClass("com.alibaba.fastjson.JSON")
    @Bean
    // 该Bean会作为首选
    @Primary
    public FormatProcessor stringFormat() {
        return new StringFormatProcessor();
    }

    /**
     * JSON方式格式化Bean
     *
     * @return
     */
    // 在classpath下无JSON类时生效
    @ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
    @Bean
    public FormatProcessor jsonFormat() {
        return new JsonFormatProcessor();
    }
}
