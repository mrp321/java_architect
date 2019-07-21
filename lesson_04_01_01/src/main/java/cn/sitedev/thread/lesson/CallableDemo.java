package cn.sitedev.thread.lesson;

import java.util.concurrent.*;

public class CallableDemo implements Callable<String> {
    public String call() throws Exception {
        int a = 1;
        int b = 2;
        int sum = a + b;
        System.out.println(sum);
        return "执行结果:" + sum;

    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<String> callable = new CallableDemo();
        Future<String> future = executorService.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
