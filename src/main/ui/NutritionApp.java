package ui;

import model.CalorieCounter;
import model.Food;
import model.MacroType;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//EFFECTS: Represents the nutrition application
public class NutritionApp {

    private CalorieCounter calorieCounterMale;
    private CalorieCounter calorieCounterFemale;
    private Food rice;
    private Food porkChop;
    private Food milk;
    private Food peanuts;
    private Food bread;
    private Food chickenBreast;
    private Scanner input;
    private JsonWriter jsonWriterMale;
    private JsonReader jsonReaderMale;
    private JsonWriter jsonWriterFemale;
    private JsonReader jsonReaderFemale;
    private static final String JSON_STORE_MALE = "./data/calorieCounterMale.json";
    private static final String JSON_STORE_FEMALE = "./data/calorieCounterFemale.json";

    //EFFECTS: runs the nutrition application
    public NutritionApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriterMale = new JsonWriter(JSON_STORE_MALE);
        jsonReaderMale = new JsonReader(JSON_STORE_MALE);
        jsonWriterFemale = new JsonWriter(JSON_STORE_FEMALE);
        jsonReaderFemale = new JsonReader(JSON_STORE_FEMALE);
        runNutrition();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runNutrition() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);
        init();
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Powering down");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("d")) {
            seeCalories();
        } else if (command.equals("w")) {
            addFood();
        } else if (command.equals("r")) {
            removeFood();
        } else if (command.equals("t")) {
            seeFoodsEaten();
        } else if (command.equals("s")) {
            saveCalorieCounter();
        } else if (command.equals("l")) {
            loadCalorieCounter();
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes calorie counter
    private void init() {
        calorieCounterMale = new CalorieCounter(64, 165, 20,
                "Male", "Lean Bulk", "Moderately Active");
        calorieCounterFemale = new CalorieCounter(75, 170, 35,
                "Female", "Lose Weight", "Lightly Active");
        rice = new Food("Rice", 100, 130, MacroType.CARBOHYDRATE);
        milk = new Food("Milk", 250, 127, MacroType.FAT);
        porkChop = new Food("Pork Chop", 100, 211, MacroType.PROTEIN);
        peanuts = new Food("Peanuts", 50, 297, MacroType.FAT);
        bread = new Food("Bread", 40, 108, MacroType.CARBOHYDRATE);
        chickenBreast = new Food("Chicken Breast", 56, 75, MacroType.PROTEIN);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from");
        System.out.println("\td -> see calories");
        System.out.println("\tw -> add food");
        System.out.println("\tt -> see foods eaten");
        System.out.println("\tr -> remove food");
        System.out.println("\ts -> save calorieCounter");
        System.out.println("\tl -> load calorieCounter");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: Displays the total amount of calories, protein calories, fat calories and carbohydrate calories for you
    private void seeCalories() {
        CalorieCounter selected = selectUser();
        if (selected == calorieCounterMale) {
            calorieCounterMale.calculateCalories();
            System.out.println("Your total calories for your given goal: " + calorieCounterMale.getTotalCalories());
            System.out.println("Calories that should be gained from proteins: "
                    + calorieCounterMale.getCaloriesFromProtein());
            System.out.println("Calories that should be gained from fats: " + calorieCounterMale.getCaloriesFromFats());
            System.out.println("Calories that should be gained from carbohydrates: "
                    + calorieCounterMale.getCaloriesFromCarbohydrates());
        } else {
            calorieCounterFemale.calculateCalories();
            System.out.println("Your total calories for your given goal: " + calorieCounterFemale.getTotalCalories());
            System.out.println("Calories that should be gained from proteins: "
                    + calorieCounterFemale.getCaloriesFromProtein());
            System.out.println("Calories that should be gained from fats: "
                    + calorieCounterFemale.getCaloriesFromFats());
            System.out.println("Calories that should be gained from carbohydrates: "
                    + calorieCounterFemale.getCaloriesFromCarbohydrates());
        }
    }

    //MODIFIES: this
    //EFFECTS: adds Food to the calorie counter
    private void addFood() {
        boolean keepGoing = true;
        String command = null;
        CalorieCounter selected = selectUser();
        while (keepGoing) {
            displayFoodMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processFoodCommand(command, selected);
            }
        }
    }

    //EFFECTS: displays a list foods to choose from
    private void displayFoodMenu() {
        System.out.println("Select food to add:");
        System.out.println("Select from");
        System.out.println("\tr -> Rice");
        System.out.println("\tb -> Bread");
        System.out.println("\tm -> Milk");
        System.out.println("\tn -> Peanuts");
        System.out.println("\tp -> PorkChop");
        System.out.println("\tc -> ChickenBreast");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processFoodCommand(String command, CalorieCounter selected) {
        if (command.equals("r")) {
            addRice(selected);
        } else if (command.equals("b")) {
            addBread(selected);
        } else if (command.equals("m")) {
            addMilk(selected);
        } else if (command.equals("n")) {
            addPeanut(selected);
        } else if (command.equals("p")) {
            addPorkChop(selected);
        } else if (command.equals("c")) {
            addChickenBreast(selected);
        } else {
            System.out.println("Invalid selection");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds the food Rice to the foodsEaten list
    private void addRice(CalorieCounter selected) {
        if (selected == calorieCounterMale) {
            calorieCounterMale.getFoodsEaten().add(rice);
        } else {
            calorieCounterFemale.getFoodsEaten().add(rice);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds the Bread Rice to the foodList
    private void addBread(CalorieCounter selected) {
        if (selected == calorieCounterMale) {
            calorieCounterMale.getFoodsEaten().add(bread);
        } else {
            calorieCounterFemale.getFoodsEaten().add(bread);
        }

    }

    //MODIFIES: this
    //EFFECTS: adds the food Milk to the foodsEaten list
    private void addMilk(CalorieCounter selected) {
        if (selected == calorieCounterMale) {
            calorieCounterMale.getFoodsEaten().add(milk);
        } else {
            calorieCounterFemale.getFoodsEaten().add(milk);
        }

    }

    //MODIFIES: this
    //EFFECTS: adds the food Peanut to the foodsEaten list
    private void addPeanut(CalorieCounter selected) {
        if (selected == calorieCounterMale) {
            calorieCounterMale.getFoodsEaten().add(peanuts);
        } else {
            calorieCounterFemale.getFoodsEaten().add(peanuts);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds the food PorkChop to the foodsEaten list
    private void addPorkChop(CalorieCounter selected) {
        if (selected == calorieCounterMale) {
            calorieCounterMale.getFoodsEaten().add(porkChop);
        } else {
            calorieCounterFemale.getFoodsEaten().add(porkChop);
        }
    }


    //MODIFIES: this
    //EFFECTS: adds the food ChickenBreast to the foodsEaten list
    private void addChickenBreast(CalorieCounter selected) {
        if (selected == calorieCounterMale) {
            calorieCounterMale.getFoodsEaten().add(chickenBreast);
        } else {
            calorieCounterFemale.getFoodsEaten().add(chickenBreast);
        }
    }

    //REQUIRES: command has to be an integer that is displayed or "q"
    //MODIFIES: this
    //EFFECTS: removes Food from the foodList and adds back calories to the calorieCounter
    private void removeFood() {
        boolean keepGoing = true;
        String command = null;
        CalorieCounter selected = selectUser();
        while (keepGoing) {
            displayFoodsEatenMenu(selected);
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processRemoveFoodCommand(command, selected);
            }
        }
    }

    //EFFECTS: Prints out all the Food's name in the foodList
    private void displayFoodsEatenMenu(CalorieCounter selected) {
        int x = 0;
        System.out.println("Select the number corresponding to the food you wish to remove");
        if (selected == calorieCounterMale) {
            List<Food> foodList = calorieCounterMale.getFoodsEaten();
            for (Food f : foodList) {
                System.out.println(x + " : " + f.getFoodName());
                x++;
            }
        } else {
            List<Food> foodList = calorieCounterFemale.getFoodsEaten();
            for (Food f : foodList) {
                System.out.println(x + " : " + f.getFoodName());
                x++;
            }
        }
        System.out.println("q -> quit");
    }

    //REQUIRES: command has to be an integer that is displayed or "q"
    //MODIFIES: this
    //EFFECTS: removes the food at the given command
    private void processRemoveFoodCommand(String command, CalorieCounter selected) {
        if (selected == calorieCounterMale) {
            calorieCounterMale.getFoodsEaten().remove(Integer.parseInt(command));
        } else if (selected == calorieCounterFemale) {
            calorieCounterFemale.getFoodsEaten().remove(Integer.parseInt(command));
        } else {
            System.out.println("Invalid selection");
        }
    }

    //EFFECTS: Displays a string with all the Food added
    private void seeFoodsEaten() {
        CalorieCounter selected = selectUser();
        if (selected == calorieCounterMale) {
            List<Food> foodList = calorieCounterMale.getFoodsEaten();
            for (Food f : foodList) {
                System.out.print(f.getFoodName() + ",");
            }
        } else {
            List<Food> foodList = calorieCounterFemale.getFoodsEaten();
            for (Food f : foodList) {
                System.out.print(f.getFoodName() + ",");
            }
        }
    }

    //EFFECTS: prompts user to select Male or Female users and returns it
    private CalorieCounter selectUser() {
        String selection = "";
        while (!(selection.equals("m") || selection.equals("f"))) {
            System.out.println("m for Male account");
            System.out.println("f for Female account");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("m")) {
            calorieCounterMale.calculateCalories();
            return calorieCounterMale;
        } else {
            calorieCounterFemale.calculateCalories();
            return calorieCounterFemale;
        }
    }

    //EFFECTS: saves the calorieCounter to file
    private void saveCalorieCounter() {
        CalorieCounter selected = selectUser();
        if (selected.equals(calorieCounterMale)) {
            try {
                jsonWriterMale.open();
                jsonWriterMale.write(selected);
                jsonWriterMale.close();
                System.out.println("Saved " + selected + " to " + JSON_STORE_MALE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE_MALE);
            }
        } else {
            try {
                jsonWriterFemale.open();
                jsonWriterFemale.write(selected);
                jsonWriterFemale.close();
                System.out.println("Saved " + selected + " to " + JSON_STORE_FEMALE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE_FEMALE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads calorieCounter from file
    private void loadCalorieCounter() {
        CalorieCounter selected = selectUser();
        if (selected.equals(calorieCounterMale)) {
            try {
                calorieCounterMale = jsonReaderMale.read();
                System.out.println("Loaded " + selected + " from " + JSON_STORE_MALE);
                selected.calculateCalories();
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE_MALE);
            }
        } else if (selected.equals(calorieCounterFemale)) {
            try {
                calorieCounterFemale = jsonReaderFemale.read();
                System.out.println("Loaded " + selected + " from " + JSON_STORE_FEMALE);
                selected.calculateCalories();
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE_FEMALE);
            }
        }
    }
}
