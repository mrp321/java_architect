package cn.sitedev.lesson;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

// 多线程处理, 处理客户端请求
public class ProcessorHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private Map<String, Object> handlerMap;


    public ProcessorHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // 反射调用
        String serviceName = rpcRequest.getClassName();
        String version = rpcRequest.getVersion();
        // 版本号判断
        if (!StringUtils.isEmpty(version)) {
            serviceName += "-" + version;
        }
        Object service = handlerMap.get(serviceName);
        if (service == null) {
            throw new RuntimeException("service not found " + service);
        }
        Object[] args = rpcRequest.getParams(); // 拿到客户端请求的参数
        Class<?>[] types = new Class[0]; // 获得每个参数的类型
        if (args != null) {
            types = new Class[args.length];
            for (int i = 0, j = args.length; i < j; i++) {
                types[i] = args[i].getClass();
            }
        }
        Class clazz = Class.forName(service.getClass().getName()); // 加载请求的类, 获取IHelloService
        Method method = clazz.getMethod(rpcRequest.getMethodName(), types); // 找到类中指定的方法, 获取sayHello,saveUser
        return method.invoke(service, args); // 进行反射调用, 获得结果, 调用HelloServiceImpl中方法返回结果
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest) throws Exception {
        Object result = invoke(rpcRequest);
        channelHandlerContext.writeAndFlush(result).addListener(ChannelFutureListener.CLOSE);

    }
}
