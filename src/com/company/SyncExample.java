package com.company;

class Counter {

    public int count;

    public synchronized int increment () {
        return ++count;
    }
}

class CounterThread extends Thread {

    private Counter c;

    public CounterThread (Counter c) {
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            c.increment();
        }
    }
}

public class SyncExample {
    public static void main(String[] args) throws Exception {
        var c = new Counter();
        CounterThread ct1 = new CounterThread(c);
        CounterThread ct2 = new CounterThread(c);

        ct1.start();
        ct2.start();

        ct1.join();
        ct2.join();

        System.out.println(c.count);
    }
}
