package com.example.shirodemo.test.designmode.buildermode;

/**
 * @author jocken
 * @date 2020/10/19
 */
public class Coke extends ColdDrink {

    @Override
    public float price() {
        return 10.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}
