package cn.sitedev.lesson.starter.demo.jmx;

/**
 * 把需要发布出去的指标信息,通过MBean来进行发布
 */
//标准的MBean由一个MBean接口和一个Class类组成，并且接口必须命名为 xxxMBean 而 Class类名必须为 xxx 放与同一个包下，若不在同一包下运行时将出异常。
public interface MachineMBean {

    // 这里可以定义属性和操作

    int getAvailableProcessors();

    long getFreeMemory();

    long getMaxMemory();

    long getTotalMemory();

    void shutdown();

}
