package com.example.shirodemo.test.designmode.buildermode;

/**
 * @author jocken
 * @date 2020/10/19
 */
public class MealBuilder {

    public Meal preVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal preMeatMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }

}
