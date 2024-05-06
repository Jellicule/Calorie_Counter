package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.round;

// Represents a calorieCounter that calculates the amount of calories that you should eat in a day with your bodyWeight
// height, age, sex, health goal and physical activity level
public class CalorieCounter implements Writable {

    private static double PERCENTAGE_FROM_FATS = 0.20;                //Could be anywhere from 20-35%
    private static double PERCENTAGE_FROM_PROTEINS = 0.25;            //Could be anywhere from 10-35%
    private static double PERCENTAGE_FROM_CARBOHYDRATES = 0.55;       //Could be anywhere from 45-35%
    private static int CUT = 500;                                //One could set it 500<
    private static int LOSE_WEIGHT = 250;                        //One could set it 100 <= x <= 400
    private static int LEAN_BULK = 250;                          //One could set it 100 <= x <= 400
    private static int DIRTY_BULK = 500;                         //One could set it 500<
    private static double SEDENTARY = 1.2;
    private static double LIGHTLY_ACTIVE = 1.375;
    private static double MODERATELY_ACTIVE = 1.55;
    private static double VERY_ACTIVE = 1.725;
    private static double EXTRA_ACTIVE = 1.9;

    private int bodyWeight;
    private int height;
    private int age;
    private String sex;
    private String goal;
    private String activity;
    private double calorieCounter; //WTF IS DOUBLE
    private double proteinCounter;
    private double carbohydrateCounter;
    private double fatCounter;
    private List<Food> foodList;


    //REQUIRES: bodyWeight > 0 in kg,
    //          height > 0 in centimeters
    //          age > 0
    //          sex is either "Male" or "Female"
    //          goal is a string and has to be one of the following: "Cut", "Lose Weight", "Lean Bulk", "Dirty Bulk"
    //          activity is a string has to be one of the following: "Sedentary", "Lightly Active". "Moderately Active"
    //                                                               "Very Active", "Extra Active"
    //EFFECTS: Constructs a CalorieCounter with starting weight, height and age with sex, one's goal, weekly activity
    //         and an empty list of Food
    public CalorieCounter(int bodyWeight, int height, int age, String sex, String goal, String activity) {
        this.calorieCounter = 0;
        this.proteinCounter = 0;
        this.carbohydrateCounter = 0;
        this.fatCounter = 0;
        this.bodyWeight = bodyWeight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.goal = goal;
        this.activity = activity;
        this.foodList = new ArrayList();
    }

    //MODIFIES: this
    //EFFECTS: Calculates the amount of calories that a person should consume to maintain their current weight
    //         depending on if they are male or female and what their goals are
    public void calculateCalories() {
        calculateInitialCaloriesWithSex();
        calculateInitialCaloriesWithActivity();
        calculateInitialCaloriesWithGoal();
        calculateInitialProteinCalories();
        calculateInitialFatCalories();
        calculateInitialCarbohydrateCalories();
        if (!getFoodsEaten().isEmpty()) {
            for (Food food : foodList) {
                subtractCalories(food);
            }
        }
    }

    //REQUIRES: Sex has to be one of: "Male" or "Female"
    //MODIFIES: this
    //EFFECTS: calculates the initial calories depending on sex
    public void calculateInitialCaloriesWithSex() {
        if (sex.equals("Male")) {
            calorieCounter = 66.5 + (13.75 * bodyWeight) + (5.003 * height) - (6.75 * age);
        } else {
            calorieCounter = 655.1 + (9.563 * bodyWeight) + (1.850 * height) - (4.676 * age);
        }
    }

    //REQUIRES: Activity has to be one of: "Sedentary", "Lightly Active",
    //          "Moderately Active", "Very Active", "Extra Active"
    //MODIFIES: this
    //EFFECTS: calculates the initial calories depending on activity level
    public void calculateInitialCaloriesWithActivity() {
        if (Objects.equals(activity, "Sedentary")) {
            calorieCounter *= SEDENTARY;
        } else if (activity.equals("Lightly Active")) {
            calorieCounter *= LIGHTLY_ACTIVE;
        } else if (activity.equals("Moderately Active")) {
            calorieCounter *= MODERATELY_ACTIVE;
        } else if (activity.equals("Very Active")) {
            calorieCounter *= VERY_ACTIVE;
        } else if (activity.equals("Extra Active")) {
            calorieCounter *= EXTRA_ACTIVE;
        }
    }

    //REQUIRES: Goal has to be one of: "Cut", "Lose Weight", "Lean Bulk", "Dirty Bulk"
    //MODIFIES: this
    //EFFECTS: calculates the initial calories depending on their goal
    public void calculateInitialCaloriesWithGoal() {
        if (goal.equals("Cut")) {
            calorieCounter -= CUT;
        } else if (goal.equals("Lose Weight")) {
            calorieCounter -= LOSE_WEIGHT;
        } else if (goal.equals("Lean Bulk")) {
            calorieCounter += LEAN_BULK;
        } else if (goal.equals("Dirty Bulk")) {
            calorieCounter += DIRTY_BULK;
        }
    }

    //MODIFIES: this
    //EFFECTS: Calculates amount of calories that should be gained from proteins
    public void calculateInitialProteinCalories() {
        this.proteinCounter = calorieCounter * PERCENTAGE_FROM_PROTEINS;
    }

    //MODIFIES: this
    //EFFECTS: Calculates amount of calories that should be gained from carbohydrates
    public void calculateInitialCarbohydrateCalories() {
        this.carbohydrateCounter = calorieCounter * PERCENTAGE_FROM_CARBOHYDRATES;
    }

    //MODIFIES: this
    //EFFECTS: Calculates amount of calories that should be gained from fats
    public void calculateInitialFatCalories() {
        this.fatCounter = calorieCounter * PERCENTAGE_FROM_FATS;
    }

    //MODIFIES: this
    //EFFECTS: subtracts amount of calories and corresponding macro calories with the given food.
    //         If the given food's calories exceeds the amount of the calorie counter and/or any of the protein, fats
    //         and carbohydrate counters, it will set the counters to 0 and print out a line
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void subtractCalories(Food food) {
        if (calorieCounter > 0) {
            calorieCounter -= food.getCalories();
            if (food.getMacro() == MacroType.PROTEIN && proteinCounter > 0) {
                proteinCounter -= food.getCalories();
            } else if (food.getMacro() == MacroType.FAT && fatCounter > 0) {
                fatCounter -= food.getCalories();
            } else if (food.getMacro() == MacroType.CARBOHYDRATE && carbohydrateCounter > 0) {
                carbohydrateCounter -= food.getCalories();
            }
        }
        if (calorieCounter <= 0) {
            calorieCounter = 0;
        }
        if (fatCounter <= 0) {
            fatCounter = 0;
        }
        if (carbohydrateCounter <= 0) {
            carbohydrateCounter = 0;
        }
        if (proteinCounter <= 0) {
            proteinCounter = 0;
        }
    }

    //MODIFIES: this
    //EFFECTS: adds Food to foodList
    public void addFood(Food food) {
        foodList.add(food);
        EventLog.getInstance().logEvent(new Event("Added: " + food.getFoodName()));
    }

    //MODIFIES: this
    //EFFECTS: Removes a Food from the foodList with the given index
    public void removeFood(int x) {
        EventLog.getInstance().logEvent(new Event("Removed: " + foodList.get(x).getFoodName()));
        foodList.remove(x);
    }

    //EFFECTS: Returns number of calories
    public double getTotalCalories() {
        return round(calorieCounter);
    }

    //EFFECTS: Returns number of calories that should be consumed from proteins
    public double getCaloriesFromProtein() {
        return round(proteinCounter);
    }

    //EFFECTS: Returns number of calories that should be consumed from carbohydrates
    public double getCaloriesFromCarbohydrates() {
        return round(carbohydrateCounter);
    }

    //EFFECTS: Returns number of calories that should be consumed from fats
    public double getCaloriesFromFats() {
        return round(fatCounter);
    }

    //EFFECTS: Returns the list of foods eaten
    public List<Food> getFoodsEaten() {
        return foodList;
    }

    //EFFECTS: Returns bodyWeight
    public int getBodyWeight() {
        return bodyWeight;
    }

    //EFFECTS: returns height
    public int getHeight() {
        return height;
    }

    //EFFECTS: returns age
    public int getAge() {
        return age;
    }

    //EFFECTS: returns sex
    public String getSex() {
        return sex;
    }

    //EFFECTS: returns goal
    public String getGoal() {
        return goal;
    }

    //EFFECTS: returns activity
    public String getActivity() {
        return activity;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("bodyWeight", Integer.toString(bodyWeight));
        json.put("height", Integer.toString(height));
        json.put("age", Integer.toString(age));
        json.put("sex", sex);
        json.put("goal", goal);
        json.put("activity", activity);
        json.put("food", foodToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray foodToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Food f : foodList) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }
}
