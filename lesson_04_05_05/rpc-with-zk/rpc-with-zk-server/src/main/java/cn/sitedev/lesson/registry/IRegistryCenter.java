package cn.sitedev.lesson.registry;

/**
 * 注册中心接口
 */
public interface IRegistryCenter {

    /**
     * 注册
     *
     * @param serviceName    服务名称
     * @param serviceAddress 服务地址
     */
    void registry(String serviceName, String serviceAddress);
}
