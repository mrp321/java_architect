package cn.sitedev.lesson;

import cn.sitedev.lesson.discovery.IServiceDiscovery;
import cn.sitedev.lesson.discovery.ServiceDiscoveryWithZk;

import java.lang.reflect.Proxy;

public class RpcProxyClient {
    private IServiceDiscovery serviceDiscovery = new ServiceDiscoveryWithZk();

    // JDK动态代理
    public <T> T clientProxy(final Class<T> interfaceCls, final String version) {
        // 取得动态代理对象
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(serviceDiscovery, version));

    }
}
