package cn.sitedev.lesson;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class RpcServer implements ApplicationContextAware, InitializingBean {


    ExecutorService executorService = Executors.newCachedThreadPool();

    private Map<String, Object> handlerMap = new HashMap<>();

    private int port;

    public RpcServer(int port) {
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.publisher(port);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 拿到标有指定注解的bean
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (serviceBeanMap != null && !serviceBeanMap.isEmpty()) {
            for (Object serviceBean : serviceBeanMap.values()) {
                // 拿到注解
                RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
                String rpcServiceName = rpcService.value().getName(); // 取得接口实现类
                String version = rpcService.version(); // 取得服务版本号
                if (!StringUtils.isEmpty(version)) {
                    rpcServiceName += "-" + version;
                }
                handlerMap.put(rpcServiceName, serviceBean);
            }
        }
    }

    public void publisher(int port) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            // 不断接收请求
            while (true) {
                Socket socket = serverSocket.accept(); // BIO
                // 每个socket交给一个processorHandler处理
                executorService.execute(new ProcessorHandler(handlerMap, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
