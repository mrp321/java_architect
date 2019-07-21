package cn.sitedev.thread.lesson;

public class MyThread2 extends OtherClass implements Runnable {
    public void run() {
        System.out.println("MyThread2.run");
    }

    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        Thread thread1 = new Thread(myThread2);
        Thread thread2 = new Thread(myThread2);
        thread1.start();
        thread2.start();
    }
}

class OtherClass {

}