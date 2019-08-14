package cn.sitedev.lesson;

import cn.sitedev.lesson.discovery.IServiceDiscovery;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//
public class RemoteInvocationHandler implements InvocationHandler {
    private IServiceDiscovery serviceDiscovery;
    private String version;

    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery, String version) {
        this.serviceDiscovery = serviceDiscovery;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 请求会进入到这里
        System.out.println("RemoteInvocationHandler.invoke");
        // 请求数据的包装
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParams(args);
        rpcRequest.setVersion(version);
        String serviceName = rpcRequest.getClassName();
        if (!StringUtils.isEmpty(version)) {
            serviceName = serviceName + "-" +version;
        }
        String serviceAddress = serviceDiscovery.discovery(serviceName);
        // 远程通信
        RpcNetTransport rpcNetTransport = new RpcNetTransport(serviceAddress);
        Object result = rpcNetTransport.send(rpcRequest);
        return result;
    }
}
