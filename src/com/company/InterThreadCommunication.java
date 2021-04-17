package com.company;

class Q {

    private int num;
    private boolean isChanged = false;

    public synchronized void getNum() {
        while (!isChanged) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

//        Синхронізація тільки кількох конструкцій
//        synchronized (this) {
//            isChanged = true;
//        }
        isChanged = false;
        System.out.println("Get: " + num);
        notify();
    }

    public synchronized void setNum(int num) {
        while (isChanged) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.num = num;
        isChanged = true;
        System.out.println("Set: " + num);
        notify();
    }
}

class Producer implements Runnable {

    private Q q;

    Producer(Q q) {
        this.q = q;
        Thread t = new Thread(this, "Producer");
        t.start();
    }

    @Override
    public void run() {
        int i = 0;

        while (true) {
            q.setNum(i++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}

class Consumer implements Runnable {

    private Q q;

    Consumer(Q q) {
        this.q = q;
        Thread t = new Thread(this, "Consumer");
        t.start();
    }

    @Override
    public void run() {
        int i = 0;

        while (true) {
            q.getNum();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}

public class InterThreadCommunication {

    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
    }
}
