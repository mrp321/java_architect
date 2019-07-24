package cn.sitedev.lesson.starter.format;

import com.alibaba.fastjson.JSON;

/**
 * JSON格式化处理器
 */
public class JsonFormatProcessor implements FormatProcessor {
    @Override
    public <T> String format(T obj) {
        return "JsonFormatProcessor : " + JSON.toJSONString(obj);
    }
}
