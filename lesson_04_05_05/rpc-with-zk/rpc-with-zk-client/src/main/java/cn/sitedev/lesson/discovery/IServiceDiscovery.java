package cn.sitedev.lesson.discovery;

/**
 * 服务发现接口
 */
public interface IServiceDiscovery {

    /**
     * 根据服务名称返回服务地址
     *
     * @param serviceName
     * @return
     */
    String discovery(String serviceName);
}
