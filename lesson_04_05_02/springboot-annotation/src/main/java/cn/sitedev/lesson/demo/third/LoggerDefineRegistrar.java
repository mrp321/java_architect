package cn.sitedev.lesson.demo.third;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

/**
 * 自定义ImportBeanDefinitionRegistrar
 */
public class LoggerDefineRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * @param importingClassMetadata 注解元数据
     * @param registry               持有bean定义信息的registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Class beanClass = LoggerService.class;
        RootBeanDefinition beanDefinition = new RootBeanDefinition(beanClass);
        // bean名称首字母小写
        String beanName = StringUtils.uncapitalize(beanClass.getSimpleName());
        // 注册bean定义信息
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
