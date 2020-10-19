package com.example.shirodemo.test.designmode.buildermode;

/**
 * @author jocken
 * @date 2020/10/19
 */
public class TestDemo {

    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal meat = mealBuilder.preMeatMeal();
        System.out.println("Meat Meal:");
        meat.showItems();
        System.out.println("Total cost:" + meat.cost());

        Meal veg = mealBuilder.preVegMeal();
        System.out.println("Veg meal:");
        veg.showItems();
        System.out.println("Total cost:" + veg.cost());
    }
}
