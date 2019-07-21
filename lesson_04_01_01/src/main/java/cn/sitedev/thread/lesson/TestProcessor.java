package cn.sitedev.thread.lesson;

/**
 * 测试类
 */
public class TestProcessor {

    private PrintProcessor printProcessor;

    TestProcessor() {
        // 构造请求处理器的责任链
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();
        printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
    }

    private void test(Request request) {
        printProcessor.processRequest(request);
    }

    public static void main(String[] args) {
        // 构造请求
        Request request = new Request();
        request.setName("My Request");
        // 处理请求
        new TestProcessor().test(request);

    }
}
