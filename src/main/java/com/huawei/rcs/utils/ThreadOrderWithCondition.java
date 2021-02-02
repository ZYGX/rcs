package com.huawei.rcs.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程有序输出ABCABCABC...
 */
public class ThreadOrderWithCondition {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        CountDownLatch countDownLatchB=new CountDownLatch(1);
        CountDownLatch countDownLatchC=new CountDownLatch(1);


        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();

                    for (int i = 0; i < 100; i++) {

                        conditionB.signal();
                        System.out.println("A");
                        if(i==0){countDownLatchB.countDown();}
                        conditionA.await();

                    }
                    conditionA.signal();
                } catch (Exception e) {
                    System.out.println(e);
                }finally {
                    lock.unlock();
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatchB.await();
                    lock.lock();
                    for (int i = 0; i < 100; i++) {

                        conditionC.signal();
                        System.out.println("B");
                        if(i==0){countDownLatchC.countDown();}
                        conditionB.await();

                    }
                    conditionB.signal();
                } catch (Exception e) {
                    System.out.println(e);
                }finally {
                    lock.unlock();
                }

            }
        });
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatchC.await();
                    lock.lock();
                    for (int i = 0; i < 100; i++) {
                        conditionA.signal();
                        System.out.println("C");
                        conditionC.await();
                    }
                    conditionC.signal();
                } catch (Exception e) {
                    System.out.println(e);
                }finally {
                    lock.unlock();
                }

            }
        });


        threadA.start();
        threadB.start();
        threadC.start();


    }


}
