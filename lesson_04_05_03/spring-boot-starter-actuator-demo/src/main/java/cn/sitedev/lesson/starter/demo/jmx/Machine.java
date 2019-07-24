package cn.sitedev.lesson.starter.demo.jmx;

/**
 * MBean接口的实现
 */
public class Machine implements MachineMBean {


    @Override
    public int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    @Override
    public long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    @Override
    public long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    @Override
    public void shutdown() {
        Runtime.getRuntime().halt(0);
    }
}
