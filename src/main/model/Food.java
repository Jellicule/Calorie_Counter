package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents Food with a name, amount eaten, calories it has and the macro-nutrient it belongs to
public class Food implements Writable {

    private String foodName;
    private int amount;
    private int calories;
    private MacroType macro;


    //REQUIRES: amount > 0
    //          calories > 0
    //EFFECTS: Creates a new food with its name, amount eaten, estimated calories, and macronutrient it belongs to
    public Food(String foodName, int amount, int calories, MacroType macro) {
        this.foodName = foodName;
        this.amount = amount;
        this.calories = calories;
        this.macro = macro;
    }

    //EFFECTS: Returns a food's name
    public String getFoodName() {
        return foodName;
    }

    //EFFECTS: Returns the amount of that food eaten
    public int getAmount() {
        return amount;
    }

    //EFFECTS: Returns the amount of calories
    public int getCalories() {
        return calories;
    }

    //EFFECTS: Returns the macronutrient
    public MacroType getMacro() {
        return macro;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", foodName);
        json.put("amount", Integer.toString(amount));
        json.put("calories", Integer.toString(calories));
        json.put("macro", macro);
        return json;
    }
}
