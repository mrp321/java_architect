package cn.sitedev.thread.lesson;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 请求处理器-负责打印
 */
public class PrintProcessor extends Thread implements RequestProcessor {

    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<Request>();

    private RequestProcessor nextProcessor;

    /**
     * 对外提供关闭的方法
     */
    public void shutdown() {
        this.hasFinished = true;
    }

    private volatile boolean hasFinished;

    public PrintProcessor() {
    }

    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public void processRequest(Request request) {
        // 生产者
        // 这里根据实际情况作出一些处理
        // TODO
        requests.add(request);
    }

    @Override
    public void run() {
        // 消费者
        while (!hasFinished) {
            try {
                // 阻塞式获取数据
                Request request = requests.take();
                // 处理数据
                System.out.println("print data:" + request.getName());
                // 交给下一个责任链
                if (nextProcessor != null) {
                    nextProcessor.processRequest(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
