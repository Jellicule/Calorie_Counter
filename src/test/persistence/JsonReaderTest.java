package persistence;

import model.CalorieCounter;
import model.Food;
import model.MacroType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CalorieCounter cc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/readerEmptyCalorieCounterTest.json");
        try {
            CalorieCounter cc = reader.read();
            assertEquals(64, cc.getBodyWeight());
            assertEquals(165, cc.getHeight());
            assertEquals(20, cc.getAge());
            assertEquals("Male", cc.getSex());
            assertEquals("Lean Bulk", cc.getGoal());
            assertEquals("Moderately Active", cc.getActivity());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCalorieCounter() {
        JsonReader reader = new JsonReader("./data/readerGeneralCalorieCounterTest.json");
        try {
            CalorieCounter cc = reader.read();
            assertEquals(64, cc.getBodyWeight());
            assertEquals(165, cc.getHeight());
            assertEquals(20, cc.getAge());
            assertEquals("Male", cc.getSex());
            assertEquals("Lean Bulk", cc.getGoal());
            assertEquals("Moderately Active", cc.getActivity());
            List<Food> foods = cc.getFoodsEaten();
            assertEquals(2, foods.size());
            checkFood("Rice", 100, 130, MacroType.CARBOHYDRATE, foods.get(0));
            checkFood("Milk", 250, 127, MacroType.FAT, foods.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
