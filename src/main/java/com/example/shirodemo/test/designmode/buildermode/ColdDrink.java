package com.example.shirodemo.test.designmode.buildermode;

/**
 * @author jocken
 * @date 2020/10/19
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();

}
