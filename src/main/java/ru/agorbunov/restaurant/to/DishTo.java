package ru.agorbunov.restaurant.to;

import ru.agorbunov.restaurant.model.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class use to simplify JSON marshalling/unmarshalling
 * dish with quantity of orders
 */
public class DishTo extends Dish {

    public static List<DishTo> toList(Map<Dish,Integer> dishesMap){
        List<DishTo> result = new ArrayList<>();
        for (Map.Entry<Dish,Integer> dish : dishesMap.entrySet()){
            result.add(new DishTo(dish.getKey(),dish.getValue()));
        }
        return result;
    }

    private int quantity;

    private DishTo(int id, String description, Double price, int quantity) {
        super(description, price);
        this.quantity = quantity;
        setId(id);
    }

    private DishTo(Dish dish, int quantity){
        this(dish.getId() ,dish.getDescription(),dish.getPrice(),dish.getId());
        this.quantity = quantity;
    }



}
