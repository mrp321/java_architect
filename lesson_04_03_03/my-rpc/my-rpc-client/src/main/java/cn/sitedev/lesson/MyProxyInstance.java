package cn.sitedev.lesson;

import cn.sitedev.lesson.request.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxyInstance implements InvocationHandler {
    private MyRpcClientRemote myRpcClientRemote;
    private String version;

    public MyProxyInstance(MyRpcClientRemote myRpcClientRemote, String version) {
        this.myRpcClientRemote = myRpcClientRemote;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setArgs(args);
        rpcRequest.setVersion(version);
        return myRpcClientRemote.request(rpcRequest);
    }

}
