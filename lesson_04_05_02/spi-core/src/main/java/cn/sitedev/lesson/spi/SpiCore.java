package cn.sitedev.lesson.spi;

/**
 * 该类会被SpiConfiguration配置类注入Spring容器中
 */
public class SpiCore {

    public String testSpi() {
        System.out.println("this is a spi demo");
        return "SpiCore.testSpi";
    }
}
