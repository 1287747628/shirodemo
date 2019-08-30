package com.example.shirodemo.test.waitnotify;

public class Consumer extends Thread {

    private int num;
    private IStorage storage;

    public Consumer(IStorage storage) {
        this.storage = storage;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        storage.consume(num);
    }

}
