package cn.sitedev.lesson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//
public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;
    private String version;

    public RemoteInvocationHandler(String host, int port, String version) {
        this.host = host;
        this.port = port;
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

        // 远程通信
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        Object result = rpcNetTransport.send(rpcRequest);
        return result;
    }
}
