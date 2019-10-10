package com.example.shirodemo.test.designMode.factoryMode;

import com.custom.mutil.StringUtil;

public class ShapeFactory {

    public static final String CIRCLE = "CIRCLE";
    public static final String RECTANGLE = "RECTANGLE";
    public static final String SQUARE = "SQUARE";

    public static Shape getShape(String shapeType) {
        if (StringUtil.isEmpty(shapeType)) {
            return null;
        }
        if (CIRCLE.equalsIgnoreCase(shapeType)) {
            return new Circle();
        } else if (RECTANGLE.equalsIgnoreCase(shapeType)) {
            return new Rectangle();
        } else if (SQUARE.equalsIgnoreCase(shapeType)) {
            return new Square();
        }
        return null;
    }

}
