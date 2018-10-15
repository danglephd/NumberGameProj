package com.pxr.tutorial.menu.dto;

/**
 * Created by danglph on 10/07/2017.
 */

public class Fibonacci{
    private int seed;
    private int first;
    private int second;

//    public Fibonacci(int seed) {
//        this.seed = seed;
//        first = 1;
//        second = 0;
//    }

    public Fibonacci() {
        this.seed = 1;
        first = 1;
        setSecond(1);
    }

    public int next(){
        int temp = second;
        setSecond(getSecond() + first);
        first = temp;
        return getSecond();
    }

    public int getSeed() {
        return seed;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

//    public void setSeed(int seed) {
//        this.seed = seed;
//    }
}
