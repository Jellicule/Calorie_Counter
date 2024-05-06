package persistence;

import model.CalorieCounter;
import model.Food;
import model.MacroType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void writerInvalidFileTest() {
        try {
            CalorieCounter cc = new CalorieCounter(64, 165, 20,
                                                "Male", "Lean Bulk", "Moderately Active");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void writerEmptyCalorieCounterTest() {
        try {
            CalorieCounter cc = new CalorieCounter(64, 165, 20,
                    "Male", "Lean Bulk", "Moderately Active");
            JsonWriter writer = new JsonWriter("./data/writerEmptyCalorieCounterTest.json");
            writer.open();
            writer.write(cc);
            writer.close();

            JsonReader reader = new JsonReader("./data/writerEmptyCalorieCounterTest.json");
            cc = reader.read();
            assertEquals(64, cc.getBodyWeight());
            assertEquals(165, cc.getHeight());
            assertEquals(20, cc.getAge());
            assertEquals("Male", cc.getSex());
            assertEquals("Lean Bulk", cc.getGoal());
            assertEquals("Moderately Active", cc.getActivity());
            assertEquals(0, cc.getFoodsEaten().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void writerGeneralWorkroomTest() {
        try {
            CalorieCounter cc = new CalorieCounter(64, 165, 20,
                    "Male", "Lean Bulk", "Moderately Active");
            List<Food> foodList = cc.getFoodsEaten();
            foodList.add(new Food("Bread", 40, 180, MacroType.CARBOHYDRATE));
            foodList.add(new Food("Chicken Breast", 56, 75, MacroType.PROTEIN));
            JsonWriter writer = new JsonWriter("./data/writerGeneralCalorieCounterTest.json");
            writer.open();
            writer.write(cc);
            writer.close();

            JsonReader reader = new JsonReader("./data/writerGeneralCalorieCounterTest.json");
            cc = reader.read();
            assertEquals(64, cc.getBodyWeight());
            assertEquals(165, cc.getHeight());
            assertEquals(20, cc.getAge());
            assertEquals("Male", cc.getSex());
            assertEquals("Lean Bulk", cc.getGoal());
            assertEquals("Moderately Active", cc.getActivity());
            assertEquals(2, foodList.size());
            checkFood("Bread", 40, 180, MacroType.CARBOHYDRATE, foodList.get(0));
            checkFood("Chicken Breast", 56, 75, MacroType.PROTEIN, foodList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
