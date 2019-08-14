package cn.sitedev.lesson.registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 注册中心Zk实现
 */
public class RegistryCenterWithZk implements IRegistryCenter {

    CuratorFramework curatorFramework = null;

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

    @Override
    public void registry(String serviceName, String serviceAddress) {
        String servicePath = "/" + serviceName;
        // 判断节点是否存在
        try {
            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                // 节点不存在则创建
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath);
            }
            // serviceAddress : ip:port
            String addrPath = servicePath + "/" + serviceAddress;
            // 创建临时节点
            curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addrPath);
            System.out.println("服务注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
