package persistence;

import model.Food;
import model.MacroType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFood(String foodName, int amount, int calories, MacroType macro, Food food) {
        assertEquals(foodName, food.getFoodName());
        assertEquals(amount, food.getAmount());
        assertEquals(calories, food.getCalories());
        assertEquals(macro, food.getMacro());
    }
}
