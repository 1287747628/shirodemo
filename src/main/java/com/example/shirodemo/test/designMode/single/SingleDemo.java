package com.example.shirodemo.test.designMode.single;

public class SingleDemo {

    private SingleDemo() {

    }

    private static class SingleDemoHolder {
        private static final SingleDemo instance = new SingleDemo();
    }

    public static SingleDemo getInstance() {
        return SingleDemoHolder.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(SingleDemo.getInstance());
        }
    }

}
