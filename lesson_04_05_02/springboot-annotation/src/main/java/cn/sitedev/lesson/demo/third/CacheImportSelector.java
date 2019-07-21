package cn.sitedev.lesson.demo.third;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.*;

/**
 * 自定义ImportSelector
 */
public class CacheImportSelector implements ImportSelector {
    /**
     * @param importingClassMetadata 注解的元数据
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 读取EnableDefineService注解的元数据
        Map<String, Object> attrs = importingClassMetadata.getAnnotationAttributes(EnableDefineService.class.getName());
        // 动态注入bean:根据注解的元数据,自己编写判断逻辑来实现动态注入bean
        // 以下是一个动态注入bean的示例
        // 获取"exclude"注解属性信息
        Class[] excludeClasses = (Class[]) attrs.get("exclude");
        // 定义所有可能需要被注入的bean数组
        String[] allClasses = {CacheService.class.getName(), LoggerService.class.getName()};
        // 定义需要被注入的bean数组
        String[] importClasses = new String[allClasses.length - excludeClasses.length];
        // 将数组转为list, 以便遍历
        List<String> allClassList = new ArrayList<>(Arrays.asList(allClasses));
        // 获取list 的迭代器
        Iterator<String> iterator = allClassList.iterator();
        while (iterator.hasNext()) {
            String clazz = iterator.next();
            for (Class excludeClass : excludeClasses) {
                // 如果发现allClassList中有需要被exclude的class, 则将其从allClassList中移除
                if (clazz.equals(excludeClass.getName())) {
                    iterator.remove();
                }
            }
        }
        // 返回最终被注入的bean数组
        return allClassList.toArray(importClasses);
    }
}
