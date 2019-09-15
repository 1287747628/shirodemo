package com.example.shirodemo.test.spi;

public class ClsForNameTest {

    private String call() {
        return "success";
    }

    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("com.example.shirodemo.test.spi.ClsForNameTest");
        Class<ClsForNameTest> testTwo = ClsForNameTest.class;
        Class testThree = ClsForNameTest.class;
        ClsForNameTest clsTest = (ClsForNameTest) cls.newInstance();
        ClsForNameTest testOne = ClsForNameTest.class.newInstance();
        System.out.println(clsTest.call());
    }
}
