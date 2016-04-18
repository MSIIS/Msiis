package com.test.design.productcustom;

/**
 * Wait()和notify():如果条件不满足，则等待。当条件满足时，等待该条件的线程将被唤醒。
 * 一般用在synchronized机制中
 * 例如:
 * 线程A
 * synchronized(obj) {
 * while(!condition) {
 * obj.wait();
 * }
 * obj.doSomething();
 * }
 * 当线程A获得了obj锁后，发现条件condition不满足，无法继续下一处理，于是线程A就wait（）。
 * 在另一线程B中，如果B更改了某些条件，使得线程A的condition条件满足了，就可以唤醒线程A：
 * 线程B
 * synchronized(obj) {
 * condition = true;
 * obj.notify();
 * }
 * 对于sleep()方法，我们首先要知道该方法是属于Thread类中的。而wait()方法，则是属于Object类中的。
 * <p/>
 * sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
 * <p/>
 * 在调用sleep()方法的过程中，线程不会释放对象锁。
 * <p/>
 * 而当调用wait()方法的时候，线程会放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象调用notify()方法后本线程才进入对象锁定池准备
 * <p/>
 * 获取对象锁进入运行状态。
 * Created by 志华 on 2016/4/18.
 */
public class TestD {

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            synchronized (TestD.class) {
                System.out.println("enter thread1...");
                System.out.println("thread1 is waiting...");
                try {
                    //调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
                    TestD.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on ....");
                System.out.println("thread1 is over!!!");
            }
        }
    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            synchronized (TestD.class) {
                System.out.println("enter thread2....");
                System.out.println("thread2 is sleep....");
                //只有针对此对象调用notify()方法后本线程才进入对象锁定池准备获取对象锁进入运行状态。
                TestD.class.notify();
                //==================
                //区别
                //如果我们把代码：TestD.class.notify();给注释掉，即TestD.class调用了wait()方法，但是没有调用notify()
                //方法，则线程永远处于挂起状态。
                try {
                    //sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，
                    //但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
                    //在调用sleep()方法的过程中，线程不会释放对象锁。
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on....");
                System.out.println("thread2 is over!!!");
            }
        }
    }


}
