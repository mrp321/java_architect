package cn.sitedev.lesson.starter.demo.jmx;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * JMX测试类
 */
public class JMXMain {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {
        // 创建MBeanServer，相当于管理MBean的容器，创建方式有两种一获取JVM中默认启动的Mbean server 或者 自己创建。
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 创建ObjectName，用于标识唯一的资源MBean，格式为："域名：name=MBean名称"。
        ObjectName objectName = new ObjectName(Machine.class.getName() + ":type=machine");

        MachineMBean machineMBean = new Machine();
        //  绑定MBean与对应的ObjectName并注册到MBeanServer。
        mBeanServer.registerMBean(machineMBean, objectName);
        // 保持服务运行
        System.in.read();

    }
}
