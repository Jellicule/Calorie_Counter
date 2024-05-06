package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads calorieCounter from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads calorieCounter from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CalorieCounter read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCalorieCounter(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private CalorieCounter parseCalorieCounter(JSONObject jsonObject) {
        Integer bodyWeight = Integer.valueOf(jsonObject.getString("bodyWeight"));
        Integer height = Integer.valueOf(jsonObject.getString("height"));
        Integer age = Integer.valueOf(jsonObject.getString("age"));
        String sex = jsonObject.getString("sex");
        String goal = jsonObject.getString("goal");
        String activity = jsonObject.getString("activity");
        CalorieCounter cc = new CalorieCounter(bodyWeight, height, age, sex, goal, activity);
        addFoods(cc, jsonObject);
        return cc;
    }

    // MODIFIES: cc
    // EFFECTS: parses food from JSON object and adds them to calorieCounter
    private void addFoods(CalorieCounter cc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("food");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(cc, nextFood);
        }
    }

    // MODIFIES: cc
    // EFFECTS: parses food from JSON object and adds it to calorieCounter
    private void addFood(CalorieCounter cc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer amount = Integer.valueOf(jsonObject.getString("amount"));
        Integer calories = Integer.valueOf(jsonObject.getString("calories"));
        MacroType macroType = MacroType.valueOf(jsonObject.getString("macro"));
        Food food = new Food(name, amount, calories, macroType);
        cc.addFood(food);
    }
}
