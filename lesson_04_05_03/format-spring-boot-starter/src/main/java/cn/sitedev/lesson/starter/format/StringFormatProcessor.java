package cn.sitedev.lesson.starter.format;

/**
 * 字符串格式化处理器
 */
public class StringFormatProcessor implements FormatProcessor {
    @Override
    public <T> String format(T obj) {
        return "StringFormatProcessor : " + obj.toString();
    }
}
