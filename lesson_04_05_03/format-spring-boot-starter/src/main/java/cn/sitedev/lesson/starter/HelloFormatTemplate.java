package cn.sitedev.lesson.starter;

import cn.sitedev.lesson.starter.autoconfiguration.HelloProperties;
import cn.sitedev.lesson.starter.format.FormatProcessor;

/**
 * Hello格式化模板
 */
public class HelloFormatTemplate {
    private FormatProcessor formatProcessor;

    private HelloProperties helloProperties;

    public HelloFormatTemplate(HelloProperties helloProperties, FormatProcessor formatProcessor) {
        this.helloProperties = helloProperties;
        this.formatProcessor = formatProcessor;
    }

    /**
     * 执行格式化操作
     *
     * @param obj
     * @param <T>
     * @return
     */
    public <T> String doFormat(T obj) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("begin:Execute format").append("<br>");
        stringBuilder.append("HelloProperties:").append(formatProcessor.format(helloProperties.getInfo())).append("<br>");
        stringBuilder.append("obj format result:").append(formatProcessor.format(obj)).append("\n");
        return stringBuilder.toString();
    }
}
