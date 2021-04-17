package com.company;

class A extends Thread {

    public void run () {
        for (int i = 0; i < 1; i++) {
            System.out.println("A: " + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class B extends Thread {

    public void run () {
        for (int i = 0; i < 1; i++) {
            System.out.println("B: " + i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class C implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            System.out.println("C: " + i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


public class Main {

    public static void main(String[] args) throws Exception {
        Thread test = new MyThread();

//        test.start();

        var a = new A();
        var b = new B();
        var c = new C();

        a.start();
        b.start();

//        Thread t1 = new Thread(c);
//        t1.start();

//        Runnable d = () -> {
//            for (int i = 0; i < 5; i++) {
//                System.out.println("D: " + i);
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        d.run();

        a.join();
        b.join();

        System.out.println(a.isAlive()); // return true if thread is running

        System.out.println("Finished");

        a.setName("A thread");
        b.setName("B thread");
        a.setPriority(Thread.NORM_PRIORITY);
        System.out.println(a.getPriority());
    }
}
