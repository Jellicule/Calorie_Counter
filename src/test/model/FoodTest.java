package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodTest {

    private Food food1;
    private Food food2;
    private Food food3;

    @BeforeEach
    public void setUp() {
        food1 = new Food("Rice", 100, 130, MacroType.CARBOHYDRATE);
        food2 = new Food("Milk", 250, 127, MacroType.FAT);
        food3 = new Food("Pork Chop", 100, 211, MacroType.PROTEIN);
    }

    @Test
    public void constructorTest() {
        assertEquals("Rice", food1.getFoodName());
        assertEquals(100, food1.getAmount());
        assertEquals(130, food1.getCalories());
        assertEquals(MacroType.CARBOHYDRATE, food1.getMacro());
        assertEquals("Milk", food2.getFoodName());
        assertEquals(250, food2.getAmount());
        assertEquals(127, food2.getCalories());
        assertEquals(MacroType.FAT, food2.getMacro());
        assertEquals("Pork Chop", food3.getFoodName());
        assertEquals(100, food3.getAmount());
        assertEquals(211, food3.getCalories());
        assertEquals(MacroType.PROTEIN, food3.getMacro());
    }

}
