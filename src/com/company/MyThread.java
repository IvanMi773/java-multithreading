package com.company;

public class MyThread extends Thread { // or implements Runnable interface

    int[] values = { 6, 5, 1, 7, 8 };

    public void run () {
        for (int num: values) {
            num = num * 2;
            System.out.println(num);
        }
    }
}
