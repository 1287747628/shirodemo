package com.example.shirodemo.test.designMode.factoryMode;

public class Demo {

    public static void main(String[] args) {
        Shape circle = ShapeFactory.getShape(ShapeFactory.CIRCLE);
        circle.draw();
        //
        Shape rectangle = ShapeFactory.getShape(ShapeFactory.RECTANGLE);
        rectangle.draw();
        //
        Shape square = ShapeFactory.getShape(ShapeFactory.SQUARE);
        square.draw();
    }

}
