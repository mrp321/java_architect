package cn.sitedev.lesson.discovery;

import java.util.List;
import java.util.Random;

/**
 * 自定义随机负载均衡
 */
public class RandomLoadBalance extends LoadBalanceStrategy {
    @Override
    protected String doSelect(List<String> repos) {
        int length = repos.size();
        // 从repos集合中随机获取一个地址
        Random random = new Random();
        return repos.get(random.nextInt(length));
    }
}
