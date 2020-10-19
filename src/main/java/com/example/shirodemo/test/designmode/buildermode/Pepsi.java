package com.example.shirodemo.test.designmode.buildermode;

/**
 * @author jocken
 * @date 2020/10/19
 */
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 12.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}
