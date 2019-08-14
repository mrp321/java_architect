package cn.sitedev.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

public class AclDemo {
    public static final String CONN_STR = "192.168.75.128:2181,192.168.75.128:2182,192.168.75.128:2183";

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONN_STR).sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();

        List<ACL> acls = new ArrayList<>();
        ACL acl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
        acls.add(acl);
        curatorFramework.create().withMode(CreateMode.PERSISTENT).withACL(acls).forPath("/auth");
    }
}
