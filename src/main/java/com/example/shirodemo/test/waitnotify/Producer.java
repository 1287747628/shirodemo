package com.example.shirodemo.test.waitnotify;

public class Producer extends Thread {

    private int num;
    private IStorage storage;

    public Producer(IStorage storage) {
        this.storage = storage;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        storage.produce(num);
    }

}
