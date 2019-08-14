package cn.sitedev.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorDemo {

    public static final String CONN_STR = "192.168.75.128:2181,192.168.75.128:2182,192.168.75.128:2183";

    public static void main(String[] args) throws Exception {
        // 构建CuratorFramework方式1:
//        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONN_STR, new RetryForever(200));
        // 构建CuratorFramework方式2:
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONN_STR).sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();
//        重试策略:
//        ExponentialBackoffRetry:递增重试
//        RetryForever:永远重试
//        RetryNTimes:重试指定次数
//        RetryOneTime:重试一次
//        RetryUntilElapsed:重试直到直到时间结束

        // 使用CuratorFramework进行CRUD操作
//        curatorFramework.create();  // 增加
//        curatorFramework.setData(); // 修改
//        curatorFramework.delete(); //删除
//        curatorFramework.getData(); // 查询


//        createData(curatorFramework);
//        updateData(curatorFramework);
        deleteData(curatorFramework);
        getData(curatorFramework);

    }



    private static void createData(CuratorFramework curatorFramework) throws Exception {
        // creatingParentsIfNeeded() : 是否创建父节点
        // WithMode(CreateMode.EPHEMERAL):这里创建的是临时节点, 如果不设置, 则默认是持久节点
//        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/data/program", "test".getBytes());
        // 注意:临时节点中无法创建子节点, 下面我们将其修改为持久节点
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/data/program", "test".getBytes());
    }

    private static void updateData(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.setData().forPath("/data/program", "test1".getBytes());

    }

    private static  void deleteData(CuratorFramework curatorFramework) throws Exception {
        // 如果删除指定的旧版本的数据,会删除失败
//        curatorFramework.delete().withVersion(0).forPath("/data/program");
        // 删除数据
        Stat stat = new Stat();
        // 获取数据版本号, 并将其存入Stat对象中
        String version = new String(curatorFramework.getData().storingStatIn(stat).forPath("/data/program"));
        curatorFramework.delete().withVersion(stat.getVersion()).forPath("/data/program");
    }

    private static void getData(CuratorFramework curatorFramework) throws Exception {
        String value = new String(curatorFramework.getData().forPath("/data/program"));
        System.out.println("value=" + value);
    }

}
