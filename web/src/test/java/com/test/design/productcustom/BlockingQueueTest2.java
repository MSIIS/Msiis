package com.test.design.productcustom;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 1）wait()、notify()和notifyAll()方法是本地方法，并且为final方法，无法被重写。
 * <p/>
 * 　　2）调用某个对象的wait()方法能让当前线程阻塞，并且当前线程必须拥有此对象的monitor（即锁）
 * <p/>
 * 　　3）调用某个对象的notify()方法能够唤醒一个正在等待这个对象的monitor的线程，如果有多个线程都在等待这个对象的monitor，则只能唤醒其中一个线程；
 * <p/>
 * 　　4）调用notifyAll()方法能够唤醒所有正在等待这个对象的monitor的线程；
 * 这里要注意一点：notify()和notifyAll()方法只是唤醒等待该对象的monitor的线程，并不决定哪个线程能够获取到monitor。
 * 上面尤其要注意一点，一个线程被唤醒不代表立即获取了对象的monitor，
 * 只有等调用完notify()或者notifyAll()并退出synchronized块，释放对象锁后，其余线程才可获得锁执行
 * 多线程模拟实现生产者／消费者模型
 * 阻塞队列的方式
 *
 * @author
 * @version 1.0 2013-7-25 下午05:23:11
 */
public class BlockingQueueTest2 {
    /**
     * 定义装苹果的篮子
     */
    public class Basket {
        // 篮子，能够容纳3个苹果
        BlockingQueue<String> basket = new LinkedBlockingQueue<String>(3);

        // 生产苹果，放入篮子
        public void produce() throws InterruptedException {
            // put方法放入一个苹果，若basket满了，等到basket有位置
            basket.put("An apple");
        }

        // 消费苹果，从篮子中取走
        public String consume() throws InterruptedException {
            // take方法取出一个苹果，若basket为空，等到basket有苹果为止(获取并移除此队列的头部)
            return basket.take();
        }

        public int getSize() {
            return basket.size();
        }
    }

    // 定义苹果生产者
    class Producer implements Runnable {
        private String instance;
        private Basket basket;

        public Producer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    // 生产苹果
                    System.out.println("生产者准备生产苹果：" + instance + ",before size = " + basket.getSize());
                    basket.produce();
                    System.out.println("!生产者生产苹果完毕：" + instance + ",after size = " + basket.getSize());
                    // 休眠300ms
                    Thread.sleep(300);
                }
            } catch (InterruptedException ex) {
                System.out.println("Producer Interrupted");
            }
        }
    }

    // 定义苹果消费者
    class Consumer implements Runnable {
        private String instance;
        private Basket basket;

        public Consumer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    // 消费苹果
                    System.out.println("消费者准备消费苹果：" + instance + ",before size = " + basket.getSize());
                    System.out.println(basket.consume());
                    System.out.println("!消费者消费苹果完毕：" + instance + ",after size = " + basket.getSize());
                    // 休眠1000ms
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                System.out.println("Consumer Interrupted");
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueueTest2 test = new BlockingQueueTest2();

        // 建立一个装苹果的篮子
        Basket basket = test.new Basket();

        ExecutorService service = Executors.newCachedThreadPool();
        Producer producer = test.new Producer("生产者001", basket);
        Producer producer2 = test.new Producer("生产者002", basket);
        Consumer consumer = test.new Consumer("消费者001", basket);
        service.submit(producer);
        service.submit(producer2);
        service.submit(consumer);
        // 程序运行5s后，所有任务停止
        try {
            Thread.sleep(1000 * 4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdownNow();
    }

}