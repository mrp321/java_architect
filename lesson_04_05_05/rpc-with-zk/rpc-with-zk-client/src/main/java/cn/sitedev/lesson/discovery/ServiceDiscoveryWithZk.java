package cn.sitedev.lesson.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务发现ZK实现
 */
public class ServiceDiscoveryWithZk implements IServiceDiscovery {
    CuratorFramework curatorFramework = null;

    /**
     * 服务地址的本地缓存
     */
    List<String> serviceRepos = new ArrayList<>();

    {
        // 初始化zk的连接,会话超时时间为5s, 衰减重试
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.CONN_STR)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("registry")
                .build();
        curatorFramework.start();
    }

    /**
     * 服务发现
     *
     * @param serviceName
     * @return
     */
    @Override
    public String discovery(String serviceName) {
        //
        String path = "/" + serviceName;
        try {
            serviceRepos = curatorFramework.getChildren().forPath(path);
            registryWatch(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 针对已有的地址做负载均衡
        LoadBalanceStrategy loadBalanceStrategy = new RandomLoadBalance();
        return loadBalanceStrategy.selectHost(serviceRepos);
    }

    private void registryWatch(final String path) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/watch", true);
        PathChildrenCacheListener pathChildrenCacheListener = (curatorFramework1, pathChildrenCacheEvent) -> {
            System.out.println(pathChildrenCacheEvent.getType() + "->" + new String(pathChildrenCacheEvent.getData().getData()));
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);
    }
}
