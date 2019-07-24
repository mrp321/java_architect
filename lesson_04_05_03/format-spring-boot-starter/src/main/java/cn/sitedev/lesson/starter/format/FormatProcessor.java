package cn.sitedev.lesson.starter.format;

/**
 * 格式化处理接口
 */
public interface FormatProcessor {
    /**
     * 定义一个格式化方法
     *
     * @param obj
     * @param <T>
     * @return
     */
    <T> String format(T obj);
}
