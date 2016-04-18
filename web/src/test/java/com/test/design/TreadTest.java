package com.test.design;

/**
 * Thread Join 方法
 * 这里需要理解的就是该线程是指的主线程等待子线程的终止。
 * 也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了才能执行。
 * Created by 志华 on 2016/4/19.
 */
public class TreadTest {


    public class TreadA extends Thread {
        public TreadA() {
            super();
        }

        @Override
        public void run() {
            try {
                System.out.println("a is sleep");
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadA is over");
        }

    }

    public class TreadB extends Thread {
        TreadA at;

        public TreadB(TreadA at) {
            super("thread b");
            this.at = at;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                at.join();
                System.out.println("b is sleep");
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadB is over");
        }

    }


    class TreadC extends Thread {
        TreadB bt;

        public TreadC(TreadB bt) {
            super("thread b");
            this.bt = bt;
        }

        @Override
        public void run() {
            try {
                bt.join();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("threadc  is over");
        }
    }

    public static void main(String[] args) {
        TreadTest treadTest = new TreadTest();
        treadTest.startSer();
    }

    void startSer() {
        TreadA treadA = new TreadA();
        TreadB treadB = new TreadB(treadA);
        TreadC treadC = new TreadC(treadB);
        treadA.start();
        treadB.start();
        treadC.start();
    }


}
