package com.example.shirodemo.test.designmode.buildermode;

/**
 * @author jocken
 * @date 2020/10/19
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 32.0f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }

}
