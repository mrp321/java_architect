package cn.sitedev.lesson.spi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类, 向容器中注入SoreCore组件
 */
@Configuration
public class SpiConfiguration {
    @Bean
    public SpiCore spiCore() {
        return new SpiCore();
    }
}
