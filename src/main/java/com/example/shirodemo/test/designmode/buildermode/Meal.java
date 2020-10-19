package com.example.shirodemo.test.designmode.buildermode;

import java.util.ArrayList;
import java.util.List;

/**
 * 套餐
 *
 * @author jocken
 * @date 2020/10/19
 */
public class Meal {

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float cost() {
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems() {
        for (Item item : items) {
            System.out.println("Item:" + item.name() + " ,packing:" + item.packing().pack() + " ,price:" + item.price());
        }
    }

}
