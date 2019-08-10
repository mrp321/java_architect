package cn.sitedev.lesson;

import java.lang.reflect.Proxy;

public class RpcProxyClient {

    // JDK动态代理
    public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port, final String version) {
        // 取得动态代理对象
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(host, port, version));

    }
}
