package cn.sitedev.lesson.starter.autoconfiguration;

import cn.sitedev.lesson.starter.HelloFormatTemplate;
import cn.sitedev.lesson.starter.format.FormatProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Hello自动配置类
 */
@Configuration
// 通过Import注解导入FormatAutoConfiguration组件
@Import(FormatAutoConfiguration.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {
    /**
     * 注入HelloFormatTemplate类型的bean
     * @param helloProperties
     * @param formatProcessor
     * @return
     */
    @Bean
    public HelloFormatTemplate helloFormatTemplate(HelloProperties helloProperties, FormatProcessor formatProcessor) {
        return new HelloFormatTemplate(helloProperties, formatProcessor);
    }
}
