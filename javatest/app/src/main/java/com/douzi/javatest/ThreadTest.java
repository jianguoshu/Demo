package com.douzi.javatest;

/**
 * Created on 2017/3/2.
 */

public class ThreadTest {

    static Object lockMain = new Object();

    public static void main(String[] args) {
        say();
    }

    public  static void say() {
        new ThreadTest().sayA();

    }

    private void sayA() {
        new TestThread().start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "--a" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sayB();
    }

    void sayB() {
        synchronized (ThreadTest.lockMain) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "--b" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    void sayChild() {
//        synchronized (ThreadTest.class) {
//            for (int i = 0; i < 10; i++) {
//                System.out.println(Thread.currentThread().getName() + "--b" + i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


    static class TestThread extends Thread {
        public TestThread() {
            super("thread_child");
        }

        @Override
        public void run() {
            synchronized (ThreadTest.class) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "--" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
