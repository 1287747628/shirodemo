package com.example.shirodemo.test.designmode.factorymode.general;

import com.example.shirodemo.test.designmode.factorymode.general.Shape;

public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("draw Square");
    }
}
