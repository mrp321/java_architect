package cn.sitedev.lesson;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

// 多线程处理, 处理客户端请求
public class ProcessorHandler implements Runnable {

    private Map<String, Object> handlerMap;
    private Socket socket;


    public ProcessorHandler(Map<String, Object> handlerMap, Socket socket) {
        this.handlerMap = handlerMap;
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            // 输入流中应该有请求的类/方法名称/方法参数
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            // 反射调用本地服务
            Object result = invoke(rpcRequest);
            // 将结果写到输出流
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
}
