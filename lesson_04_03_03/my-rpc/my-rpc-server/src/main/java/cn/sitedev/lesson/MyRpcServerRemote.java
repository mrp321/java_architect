package cn.sitedev.lesson;

import cn.sitedev.lesson.annotation.MyRpcService;
import cn.sitedev.lesson.request.RpcRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * 服务端远程通信
 */
public class MyRpcServerRemote {

    @Autowired
    private ApplicationContext context;

    private int port;

    public MyRpcServerRemote(int port) {
        this.port = port;
    }

    /**
     * 处理客户端的请求, 并向客户端发送响应
     *
     * @throws IOException
     */
    public void handler() throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        // 使用while保持服务端通信连接
        while (true) {
            try {
                // 建立Socket服务端通信
                serverSocket = new ServerSocket(port);
                // 监听并接受连接
                socket = serverSocket.accept();
                // 获取输出流
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                // 获取rpc请求参数
                RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
                // 获取调用结果
                Object result = this.invoke(rpcRequest);
                //  获取输出流
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                // 将调用结果写入到输出流
                objectOutputStream.writeObject(result);
                // 刷新输出流
                objectOutputStream.flush();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } finally {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            }
        }

    }

    /**
     * 调用指定的服务
     *
     * @param rpcRequest
     * @return
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // 获取要远程调用的接口的名称
        String className = rpcRequest.getClassName();
        // 获取要远程调用的方法
        String methodName = rpcRequest.getMethodName();
        // 获取要远程调用的方法的参数
        Object[] args = rpcRequest.getArgs();
        // 声明要远程调用的方法参数类型数组
        Class<?>[] argTypes = new Class<?>[0];
        String version = rpcRequest.getVersion();
        if (args != null) {
            argTypes = new Class<?>[args.length];
            for (int i = 0, j = args.length; i < j; i++) {
                argTypes[i] = args[i].getClass();
            }
        }
        // 从容器中获取到标有指定注解的bean
        Map<String, Object> beans = context.getBeansWithAnnotation(MyRpcService.class);
        if (!CollectionUtils.isEmpty(beans)) {
            // 遍历所有的bean
            for (Object bean : beans.values()) {
                // 获取注解信息
                MyRpcService myRpcServiceAnnotation = bean.getClass().getAnnotation(MyRpcService.class);
                // 获取注解中标注的类型[type]值
                Class<?> beanTypeClass = myRpcServiceAnnotation.type();
                // 获取注解中标注的版本[version]值
                String beanVersion = myRpcServiceAnnotation.version();
                // 如果类型和版本都匹配上, 说明已经找到要调用的服务
                if (beanTypeClass.getName().equals(className) && beanVersion.equals(version)) {
                    // 加载指定的服务
                    Class<?> clazz = Class.forName(bean.getClass().getName());
                    // 获取Method服务
                    Method method = clazz.getMethod(methodName, argTypes);
                    // 通过反射调用指定服务,并返回结果
                    Object result = method.invoke(bean, args);
                    return result;
                }
            }
        }
        return null;

    }
}
