package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalorieCounterTest {

    private CalorieCounter calorieCounterNoActivityNoGoal;
    private CalorieCounter calorieCounterMCMA;
    private CalorieCounter calorieCounterMLWS;
    private CalorieCounter calorieCounterMLBLA;
    private CalorieCounter calorieCounterMDBVA;
    private CalorieCounter calorieCounterMDBEA;
    private CalorieCounter calorieCounterFCS;
    private CalorieCounter calorieCounterFDBLA;
    private Food food1;
    private Food food2;
    private Food food3;

    @BeforeEach
    public void setUp() {
        calorieCounterMCMA = new CalorieCounter(64, 165, 20, "Male", "Cut",
                "Moderately Active");
        calorieCounterMLWS = new CalorieCounter(64, 165, 20, "Male", "Lose Weight",
                "Sedentary");
        calorieCounterMLBLA = new CalorieCounter(64, 165, 20, "Male", "Lean Bulk",
                "Lightly Active");
        calorieCounterMDBVA = new CalorieCounter(64, 165, 20, "Male", "Dirty Bulk",
                "Very Active");
        calorieCounterMDBEA = new CalorieCounter(64, 165, 20, "Male", "Dirty Bulk",
                "Extra Active");
        calorieCounterFCS = new CalorieCounter(64, 165, 20, "Female", "Cut",
                "Sedentary");
        calorieCounterFDBLA = new CalorieCounter(64, 165, 20, "Female", "Dirty Bulk",
                "Lightly Active");
        calorieCounterNoActivityNoGoal = new CalorieCounter(64, 165, 20, "Male",
                                                                "Fake", "Fake");
        food1 = new Food("Rice", 100, 130, MacroType.CARBOHYDRATE);
        food2 = new Food("Milk", 250, 127, MacroType.FAT);
        food3 = new Food("Pork Chop", 100, 211, MacroType.PROTEIN);
    }

    @Test
    public void constructorTest() {
        assertEquals(0, calorieCounterMCMA.getTotalCalories());
        assertEquals(0, calorieCounterMCMA.getCaloriesFromProtein());
        assertEquals(0, calorieCounterMCMA.getCaloriesFromCarbohydrates());
        assertEquals(0, calorieCounterMCMA.getCaloriesFromFats());
        assertEquals(64, calorieCounterMCMA.getBodyWeight());
        assertEquals(165, calorieCounterMCMA.getHeight());
        assertEquals(20, calorieCounterMCMA.getAge());
        assertEquals("Male", calorieCounterMCMA.getSex());
        assertEquals("Cut", calorieCounterMCMA.getGoal());
        assertEquals("Moderately Active", calorieCounterMCMA.getActivity());
        assertEquals(0, calorieCounterMLWS.getTotalCalories());
        assertEquals(0, calorieCounterMLWS.getCaloriesFromProtein());
        assertEquals(0, calorieCounterMLWS.getCaloriesFromCarbohydrates());
        assertEquals(0, calorieCounterMLWS.getCaloriesFromFats());
        assertEquals(64, calorieCounterMLWS.getBodyWeight());
        assertEquals(165, calorieCounterMLWS.getHeight());
        assertEquals(20, calorieCounterMLWS.getAge());
        assertEquals("Male", calorieCounterMLWS.getSex());
        assertEquals("Lose Weight", calorieCounterMLWS.getGoal());
        assertEquals("Sedentary", calorieCounterMLWS.getActivity());
        assertEquals(0, calorieCounterMLBLA.getTotalCalories());
        assertEquals(0, calorieCounterMLBLA.getCaloriesFromProtein());
        assertEquals(0, calorieCounterMLBLA.getCaloriesFromCarbohydrates());
        assertEquals(0, calorieCounterMLBLA.getCaloriesFromFats());
        assertEquals(64, calorieCounterMLBLA.getBodyWeight());
        assertEquals(165, calorieCounterMLBLA.getHeight());
        assertEquals(20, calorieCounterMLBLA.getAge());
        assertEquals("Male", calorieCounterMLBLA.getSex());
        assertEquals("Lean Bulk", calorieCounterMLBLA.getGoal());
        assertEquals("Lightly Active", calorieCounterMLBLA.getActivity());
        assertEquals(0, calorieCounterMDBVA.getTotalCalories());
        assertEquals(0, calorieCounterMDBVA.getCaloriesFromProtein());
        assertEquals(0, calorieCounterMDBVA.getCaloriesFromCarbohydrates());
        assertEquals(0, calorieCounterMDBVA.getCaloriesFromFats());
        assertEquals(64, calorieCounterMDBVA.getBodyWeight());
        assertEquals(165, calorieCounterMDBVA.getHeight());
        assertEquals(20, calorieCounterMDBVA.getAge());
        assertEquals("Male", calorieCounterMDBVA.getSex());
        assertEquals("Dirty Bulk", calorieCounterMDBVA.getGoal());
        assertEquals("Very Active", calorieCounterMDBVA.getActivity());
        assertEquals(0, calorieCounterMDBEA.getTotalCalories());
        assertEquals(0, calorieCounterMDBEA.getCaloriesFromProtein());
        assertEquals(0, calorieCounterMDBEA.getCaloriesFromCarbohydrates());
        assertEquals(0, calorieCounterMDBEA.getCaloriesFromFats());
        assertEquals(64, calorieCounterMDBEA.getBodyWeight());
        assertEquals(165, calorieCounterMDBEA.getHeight());
        assertEquals(20, calorieCounterMDBEA.getAge());
        assertEquals("Male", calorieCounterMDBEA.getSex());
        assertEquals("Dirty Bulk", calorieCounterMDBEA.getGoal());
        assertEquals("Extra Active", calorieCounterMDBEA.getActivity());
        assertEquals(0, calorieCounterFCS.getTotalCalories());
        assertEquals(0, calorieCounterFCS.getCaloriesFromProtein());
        assertEquals(0, calorieCounterFCS.getCaloriesFromCarbohydrates());
        assertEquals(0, calorieCounterFCS.getCaloriesFromFats());
        assertEquals(64, calorieCounterFCS.getBodyWeight());
        assertEquals(165, calorieCounterFCS.getHeight());
        assertEquals(20, calorieCounterFCS.getAge());
        assertEquals("Female", calorieCounterFCS.getSex());
        assertEquals("Cut", calorieCounterFCS.getGoal());
        assertEquals("Sedentary", calorieCounterFCS.getActivity());
        assertEquals(0, calorieCounterFDBLA.getTotalCalories());
        assertEquals(0, calorieCounterFDBLA.getCaloriesFromProtein());
        assertEquals(0, calorieCounterFDBLA.getCaloriesFromCarbohydrates());
        assertEquals(0, calorieCounterFDBLA.getCaloriesFromFats());
        assertEquals(64, calorieCounterFDBLA.getBodyWeight());
        assertEquals(165, calorieCounterFDBLA.getHeight());
        assertEquals(20, calorieCounterFDBLA.getAge());
        assertEquals("Female", calorieCounterFDBLA.getSex());
        assertEquals("Dirty Bulk", calorieCounterFDBLA.getGoal());
        assertEquals("Lightly Active", calorieCounterFDBLA.getActivity());
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

    @Test
    public void calculateInitialCaloriesWithSexMaleTest() {
        calorieCounterMCMA.calculateInitialCaloriesWithSex();
        assertEquals(1637, calorieCounterMCMA.getTotalCalories());
    }

    @Test
    public void calculateInitialCaloriesWithSexFemaleTest() {
        calorieCounterFCS.calculateInitialCaloriesWithSex();
        assertEquals(1479, calorieCounterFCS.getTotalCalories());
    }

    @Test
    public void calculateInitialCaloriesWithUnacceptableActivityTest() {
        calorieCounterNoActivityNoGoal.calculateInitialCaloriesWithSex();
        calorieCounterNoActivityNoGoal.calculateInitialCaloriesWithActivity();
        assertEquals(1637, calorieCounterNoActivityNoGoal.getTotalCalories());

    }

    @Test
    public void calculateInitialCaloriesWithActivitySedentaryTest() {
        calorieCounterMLWS.calculateInitialCaloriesWithSex();
        calorieCounterMLWS.calculateInitialCaloriesWithActivity();
        assertEquals(1964, calorieCounterMLWS.getTotalCalories());
    }

    @Test
    public void calculateInitialCaloriesWithActivityLightlyActiveTest() {
        calorieCounterMLBLA.calculateInitialCaloriesWithSex();
        calorieCounterMLBLA.calculateInitialCaloriesWithActivity();
        assertEquals(2251, calorieCounterMLBLA.getTotalCalories());
    }

    @Test
    public void calculateInitialCaloriesWithActivityModeratelyActiveTest() {
        calorieCounterMCMA.calculateInitialCaloriesWithSex();
        calorieCounterMCMA.calculateInitialCaloriesWithActivity();
        assertEquals(2537, calorieCounterMCMA.getTotalCalories());
    }

    @Test
    public void calculateInitialCaloriesWithActivityVeryActiveTest() {
        calorieCounterMDBVA.calculateInitialCaloriesWithSex();
        calorieCounterMDBVA.calculateInitialCaloriesWithActivity();
        assertEquals(2824, calorieCounterMDBVA.getTotalCalories());
    }

    @Test
    public void calculateInitialCaloriesWithActivityExtraActiveTest() {
        calorieCounterMDBEA.calculateInitialCaloriesWithSex();
        calorieCounterMDBEA.calculateInitialCaloriesWithActivity();
        assertEquals(3110, calorieCounterMDBEA.getTotalCalories());
    }

    @Test
    public void calculateInitialCaloriesWithUnacceptableGoalTest() {
        calorieCounterNoActivityNoGoal.calculateInitialCaloriesWithSex();
        calorieCounterNoActivityNoGoal.calculateInitialCaloriesWithGoal();
        assertEquals(1637, calorieCounterNoActivityNoGoal.getTotalCalories());
    }

    @Test
    public void calculateInitialCaloriesWithGoalCutTest() {
        calorieCounterMCMA.calculateInitialCaloriesWithSex();
        calorieCounterMCMA.calculateInitialCaloriesWithActivity();
        calorieCounterMCMA.calculateInitialCaloriesWithGoal();
        assertEquals(2037, calorieCounterMCMA.getTotalCalories());
    }

    @Test
    public void calculateInitialCaloriesWithGoalLoseWeightTest() {
        calorieCounterMLWS.calculateInitialCaloriesWithSex();
        calorieCounterMLWS.calculateInitialCaloriesWithActivity();
        calorieCounterMLWS.calculateInitialCaloriesWithGoal();
        assertEquals(1714, calorieCounterMLWS.getTotalCalories());
    }

    @Test
    public void calculateInitialCaloriesWithGoalLeanBulkTest() {
        calorieCounterMLBLA.calculateInitialCaloriesWithSex();
        calorieCounterMLBLA.calculateInitialCaloriesWithActivity();
        calorieCounterMLBLA.calculateInitialCaloriesWithGoal();
        assertEquals(2501, calorieCounterMLBLA.getTotalCalories());
    }

    @Test
    public void calculateInitialCaloriesWithGoalDirtyBulkTest() {
        calorieCounterMDBVA.calculateInitialCaloriesWithSex();
        calorieCounterMDBVA.calculateInitialCaloriesWithActivity();
        calorieCounterMDBVA.calculateInitialCaloriesWithGoal();
        assertEquals(3324, calorieCounterMDBVA.getTotalCalories());
    }

       @Test
    public void calculateInitialProteinCaloriesTest() {
        calorieCounterMDBVA.calculateInitialCaloriesWithSex();
        calorieCounterMDBVA.calculateInitialCaloriesWithActivity();
        calorieCounterMDBVA.calculateInitialCaloriesWithGoal();
        calorieCounterMDBVA.calculateInitialProteinCalories();
        assertEquals(831, calorieCounterMDBVA.getCaloriesFromProtein());
    }


    @Test
    public void calculateInitialFatCaloriesTest() {
        calorieCounterMDBVA.calculateInitialCaloriesWithSex();
        calorieCounterMDBVA.calculateInitialCaloriesWithActivity();
        calorieCounterMDBVA.calculateInitialCaloriesWithGoal();
        calorieCounterMDBVA.calculateInitialFatCalories();
        assertEquals(665, calorieCounterMDBVA.getCaloriesFromFats());
    }

    @Test
    public void calculateInitialCarbohydrateCaloriesTest() {
        calorieCounterMDBVA.calculateInitialCaloriesWithSex();
        calorieCounterMDBVA.calculateInitialCaloriesWithActivity();
        calorieCounterMDBVA.calculateInitialCaloriesWithGoal();
        calorieCounterMDBVA.calculateInitialCarbohydrateCalories();
        assertEquals(1828, calorieCounterMDBVA.getCaloriesFromCarbohydrates());
    }

    @Test
    public void subtractCaloriesNoCaloriesToSubtractTest() {
        calorieCounterMDBVA.subtractCalories(food1);
        assertEquals(0, calorieCounterMDBVA.getTotalCalories());
    }

    @Test
    public void subtractCaloriesOnceTest() {
        calorieCounterMDBVA.calculateInitialCaloriesWithSex();
        calorieCounterMDBVA.calculateInitialCaloriesWithActivity();
        calorieCounterMDBVA.calculateInitialCaloriesWithGoal();
        calorieCounterMDBVA.calculateInitialCarbohydrateCalories();
        calorieCounterMDBVA.calculateInitialFatCalories();
        calorieCounterMDBVA.calculateInitialProteinCalories();
        calorieCounterMDBVA.subtractCalories(food1);
        assertEquals(3194, calorieCounterMDBVA.getTotalCalories());
        assertEquals(1698, calorieCounterMDBVA.getCaloriesFromCarbohydrates());
        assertEquals(665, calorieCounterMDBVA.getCaloriesFromFats());
        assertEquals(831, calorieCounterMDBVA.getCaloriesFromProtein());
    }

    @Test
    public void subtractCaloriesTillEmptyTest() {
        calorieCounterFCS.calculateInitialCaloriesWithSex();
        calorieCounterFCS.calculateInitialCaloriesWithActivity();
        calorieCounterFCS.calculateInitialCaloriesWithGoal();
        calorieCounterFCS.calculateInitialCarbohydrateCalories();
        calorieCounterFCS.calculateInitialFatCalories();
        calorieCounterFCS.calculateInitialProteinCalories();
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food2);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food2);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food3);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food3);
        assertEquals(0, calorieCounterFCS.getTotalCalories());
        assertEquals(51, calorieCounterFCS.getCaloriesFromCarbohydrates());
        assertEquals(1, calorieCounterFCS.getCaloriesFromFats());
        assertEquals(0, calorieCounterFCS.getCaloriesFromProtein());
    }

    @Test
    public void subtractCaloriesTillProteinCounterIsEmptyTest() {
        calorieCounterFCS.calculateInitialCaloriesWithSex();
        calorieCounterFCS.calculateInitialCaloriesWithActivity();
        calorieCounterFCS.calculateInitialCaloriesWithGoal();
        calorieCounterFCS.calculateInitialCarbohydrateCalories();
        calorieCounterFCS.calculateInitialFatCalories();
        calorieCounterFCS.calculateInitialProteinCalories();
        calorieCounterFCS.subtractCalories(food3);
        calorieCounterFCS.subtractCalories(food3);
        assertEquals(853, calorieCounterFCS.getTotalCalories());
        assertEquals(701, calorieCounterFCS.getCaloriesFromCarbohydrates());
        assertEquals(255, calorieCounterFCS.getCaloriesFromFats());
        assertEquals(0, calorieCounterFCS.getCaloriesFromProtein());
    }

    @Test
    public void subtractCaloriesTillProteinCounterIsNegativeTest() {
        calorieCounterFCS.calculateInitialCaloriesWithSex();
        calorieCounterFCS.calculateInitialCaloriesWithActivity();
        calorieCounterFCS.calculateInitialCaloriesWithGoal();
        calorieCounterFCS.calculateInitialCarbohydrateCalories();
        calorieCounterFCS.calculateInitialFatCalories();
        calorieCounterFCS.calculateInitialProteinCalories();
        calorieCounterFCS.subtractCalories(food3);
        calorieCounterFCS.subtractCalories(food3);
        calorieCounterFCS.subtractCalories(food3);
        assertEquals(642, calorieCounterFCS.getTotalCalories());
        assertEquals(701, calorieCounterFCS.getCaloriesFromCarbohydrates());
        assertEquals(255, calorieCounterFCS.getCaloriesFromFats());
        assertEquals(0, calorieCounterFCS.getCaloriesFromProtein());
    }

    @Test
    public void subtractCaloriesTillFatsCounterIsEmptyTest() {
        calorieCounterFCS.calculateInitialCaloriesWithSex();
        calorieCounterFCS.calculateInitialCaloriesWithActivity();
        calorieCounterFCS.calculateInitialCaloriesWithGoal();
        calorieCounterFCS.calculateInitialCarbohydrateCalories();
        calorieCounterFCS.calculateInitialFatCalories();
        calorieCounterFCS.calculateInitialProteinCalories();
        calorieCounterFCS.subtractCalories(food2);
        calorieCounterFCS.subtractCalories(food2);
        calorieCounterFCS.subtractCalories(food2);
        assertEquals(894, calorieCounterFCS.getTotalCalories());
        assertEquals(701, calorieCounterFCS.getCaloriesFromCarbohydrates());
        assertEquals(0, calorieCounterFCS.getCaloriesFromFats());
        assertEquals(319, calorieCounterFCS.getCaloriesFromProtein());
    }

    @Test
    public void subtractCaloriesTillFatsCounterIsNegativeTest() {
        calorieCounterFCS.calculateInitialCaloriesWithSex();
        calorieCounterFCS.calculateInitialCaloriesWithActivity();
        calorieCounterFCS.calculateInitialCaloriesWithGoal();
        calorieCounterFCS.calculateInitialCarbohydrateCalories();
        calorieCounterFCS.calculateInitialFatCalories();
        calorieCounterFCS.calculateInitialProteinCalories();
        calorieCounterFCS.subtractCalories(food2);
        calorieCounterFCS.subtractCalories(food2);
        calorieCounterFCS.subtractCalories(food2);
        calorieCounterFCS.subtractCalories(food2);
        assertEquals(767, calorieCounterFCS.getTotalCalories());
        assertEquals(701, calorieCounterFCS.getCaloriesFromCarbohydrates());
        assertEquals(0, calorieCounterFCS.getCaloriesFromFats());
        assertEquals(319, calorieCounterFCS.getCaloriesFromProtein());
    }

    @Test
    public void subtractCaloriesTillCarbohydratesCounterIsEmptyTest() {
        calorieCounterFCS.calculateInitialCaloriesWithSex();
        calorieCounterFCS.calculateInitialCaloriesWithActivity();
        calorieCounterFCS.calculateInitialCaloriesWithGoal();
        calorieCounterFCS.calculateInitialCarbohydrateCalories();
        calorieCounterFCS.calculateInitialFatCalories();
        calorieCounterFCS.calculateInitialProteinCalories();
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        assertEquals(495, calorieCounterFCS.getTotalCalories());
        assertEquals(0, calorieCounterFCS.getCaloriesFromCarbohydrates());
        assertEquals(255, calorieCounterFCS.getCaloriesFromFats());
        assertEquals(319, calorieCounterFCS.getCaloriesFromProtein());
    }

    @Test
    public void subtractCaloriesTillCarbohydratesCounterIsNegativeTest() {
        calorieCounterFCS.calculateInitialCaloriesWithSex();
        calorieCounterFCS.calculateInitialCaloriesWithActivity();
        calorieCounterFCS.calculateInitialCaloriesWithGoal();
        calorieCounterFCS.calculateInitialCarbohydrateCalories();
        calorieCounterFCS.calculateInitialFatCalories();
        calorieCounterFCS.calculateInitialProteinCalories();
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        calorieCounterFCS.subtractCalories(food1);
        assertEquals(365, calorieCounterFCS.getTotalCalories());
        assertEquals(0, calorieCounterFCS.getCaloriesFromCarbohydrates());
        assertEquals(255, calorieCounterFCS.getCaloriesFromFats());
        assertEquals(319, calorieCounterFCS.getCaloriesFromProtein());
    }

    @Test
    public void calculateCaloriesTest() {
        calorieCounterMDBVA.calculateCalories();
        assertEquals(3324, calorieCounterMDBVA.getTotalCalories());
        assertEquals(1828, calorieCounterMDBVA.getCaloriesFromCarbohydrates());
        assertEquals(831, calorieCounterMDBVA.getCaloriesFromProtein());
        assertEquals(665, calorieCounterMDBVA.getCaloriesFromFats());
    }

    @Test
    public void removeFoodFromCounterOnceTest() {
        calorieCounterMDBVA.getFoodsEaten().add(food1);
        assertTrue(calorieCounterMDBVA.getFoodsEaten().remove(food1));
        calorieCounterMDBVA.calculateCalories();
        assertEquals(3324, calorieCounterMDBVA.getTotalCalories());
        assertEquals(1828, calorieCounterMDBVA.getCaloriesFromCarbohydrates());
        assertEquals(831, calorieCounterMDBVA.getCaloriesFromProtein());
        assertEquals(665, calorieCounterMDBVA.getCaloriesFromFats());
    }

    @Test
    public void removeFoodFromCounterFailureTest() {
        assertFalse(calorieCounterMDBVA.getFoodsEaten().remove(food1));
        calorieCounterMDBVA.calculateCalories();
        assertEquals(3324, calorieCounterMDBVA.getTotalCalories());
        assertEquals(1828, calorieCounterMDBVA.getCaloriesFromCarbohydrates());
        assertEquals(831, calorieCounterMDBVA.getCaloriesFromProtein());
        assertEquals(665, calorieCounterMDBVA.getCaloriesFromFats());
    }

    @Test
    public void removeFoodFromCounterMultipleTimesTest() {
        calorieCounterMDBVA.addFood(food3);
        calorieCounterMDBVA.addFood(food2);
        calorieCounterMDBVA.addFood(food1);
        calorieCounterMDBVA.addFood(food1);
        assertTrue(calorieCounterMDBVA.getFoodsEaten().remove(food1));
        assertTrue(calorieCounterMDBVA.getFoodsEaten().remove(food2));
        assertTrue(calorieCounterMDBVA.getFoodsEaten().remove(food3));
        calorieCounterMDBVA.calculateCalories();
        assertEquals(3194, calorieCounterMDBVA.getTotalCalories());
        assertEquals(1698, calorieCounterMDBVA.getCaloriesFromCarbohydrates());
        assertEquals(831, calorieCounterMDBVA.getCaloriesFromProtein());
        assertEquals(665, calorieCounterMDBVA.getCaloriesFromFats());
    }

    @Test
    public void removeFoodFromCounterMultipleTimesThenFalseTest() {
        calorieCounterMDBVA.addFood(food3);
        calorieCounterMDBVA.addFood(food2);
        calorieCounterMDBVA.addFood(food1);
        calorieCounterMDBVA.addFood(food1);
        assertTrue(calorieCounterMDBVA.getFoodsEaten().remove(food1));
        assertTrue(calorieCounterMDBVA.getFoodsEaten().remove(food2));
        assertTrue(calorieCounterMDBVA.getFoodsEaten().remove(food3));
        assertFalse(calorieCounterMDBVA.getFoodsEaten().remove(food3));
        calorieCounterMDBVA.calculateCalories();
        assertEquals(3194, calorieCounterMDBVA.getTotalCalories());
        assertEquals(1698, calorieCounterMDBVA.getCaloriesFromCarbohydrates());
        assertEquals(831, calorieCounterMDBVA.getCaloriesFromProtein());
        assertEquals(665, calorieCounterMDBVA.getCaloriesFromFats());
    }

    @Test
    public void removeFoodOneFoodRemovedTest() {
        calorieCounterMDBVA.addFood(food1);
        assertTrue(calorieCounterMDBVA.getFoodsEaten().contains(food1));
        assertEquals(1, calorieCounterMDBVA.getFoodsEaten().size());
        calorieCounterMDBVA.removeFood(0);
        assertFalse(calorieCounterMDBVA.getFoodsEaten().contains(food1));
    }

    @Test
    public void removeFoodNoFoodsAddedTest() {
        try {
            calorieCounterMDBVA.removeFood(0);
            fail("There is no food to remove");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(calorieCounterMDBVA.getFoodsEaten().isEmpty());
    }

}
