package cn.sitedev.lesson.demo.second.current;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// @Import导入其他组件, id默认是组件的全类名
//@Import({OtherConfig.class, OtherBean.class})
public class SpringConfig {
    @Bean
    public DefaultBean defaultBean() {
        return new DefaultBean();
    }
}
