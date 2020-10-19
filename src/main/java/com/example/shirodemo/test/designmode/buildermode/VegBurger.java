package com.example.shirodemo.test.designmode.buildermode;

/**
 * @author jocken
 * @date 2020/10/19
 */
public class VegBurger extends Burger {

    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }

}
