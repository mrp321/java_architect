package cn.sitedev.lesson.discovery;

import java.util.List;

/**
 * 负载均衡算法
 */
public abstract class LoadBalanceStrategy {
    String selectHost(List<String> repos) {
        // repos可能为空, 也有可能只有1个
        if (repos == null || repos.size() == 0) {
            return null;
        }
        if (repos.size() == 1) {
            return repos.get(0);
        }
        return doSelect(repos);
    }

    protected abstract String doSelect(List<String> repos);
}
