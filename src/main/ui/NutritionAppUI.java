package ui;

import model.CalorieCounter;
import model.EventLog;
import model.Food;
import model.MacroType;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

//EFFECTS: Represents the nutrition application
public class NutritionAppUI extends JFrame {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;

    private static final String JSON_STORE_MALE = "./data/calorieCounterMale.json";

    private CalorieCounter calorieCounter;

    private Food rice;
    private Food porkChop;
    private Food milk;
    private Food peanuts;
    private Food bread;
    private Food chickenBreast;

    private JsonWriter jsonWriterMale;
    private JsonReader jsonReaderMale;

    private JDesktopPane desktop;

    private JInternalFrame optionsPanel;
    private JInternalFrame foodPanel;
    private JInternalFrame seeAndRemoveFoodsPanel;
    private JInternalFrame caloriesPanel;

    private DefaultListModel<String> foods;
    private JList<String> foodJList;

    private JLabel removeFoodLabel;
    private JLabel totalCaloriesLabel;
    private JLabel proteinCaloriesLabel;
    private JLabel fatCaloriesLabel;
    private JLabel carbohydrateCaloriesLabel;

    //EFFECTS: runs the nutrition application
    public NutritionAppUI() {
        initializeFields();
        initializeGraphics();
    }

    // EFFECTS: instantiates a new CalorieCounter, Foods, the JDesktopPane, the graphic panels and JSon fields
    private void initializeFields() {
        calorieCounter = new CalorieCounter(64, 165, 20,
                "Male", "Lean Bulk", "Moderately Active");
        initializeFoods();
        foods = new DefaultListModel<>();
        jsonWriterMale = new JsonWriter(JSON_STORE_MALE);
        jsonReaderMale = new JsonReader(JSON_STORE_MALE);
        initializePanels();
        desktop = new JDesktopPane();
        calorieCounter.calculateCalories();
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where this NutritionApp will operate and creates the options to which the user
    //          can select from
    private void initializeGraphics() {
        initializeSplash();
        initializeDesktop();
        initializeOptionsPanel();
        initializeFoodPanel();
        initializeSeeAndRemoveFoodsPanel();
        initializeCaloriesPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    // EFFECTS: Instantiates all the Foods
    private void initializeFoods() {
        rice = new Food("Rice", 100, 130, MacroType.CARBOHYDRATE);
        milk = new Food("Milk", 250, 127, MacroType.FAT);
        porkChop = new Food("Pork Chop", 100, 211, MacroType.PROTEIN);
        peanuts = new Food("Peanuts", 50, 297, MacroType.FAT);
        bread = new Food("Bread", 40, 108, MacroType.CARBOHYDRATE);
        chickenBreast = new Food("Chicken Breast", 56, 75, MacroType.PROTEIN);
    }

    // EFFECTS: Instantiates all the graphic panels
    private void initializePanels() {
        optionsPanel = new JInternalFrame("Options", false, false, false, false);
        foodPanel = new JInternalFrame("Add Foods", false, false, false, false);
        seeAndRemoveFoodsPanel = new JInternalFrame("See And Remove Foods", false, false, false, false);
        caloriesPanel = new JInternalFrame("Calories", false, false, false, false);
    }

    // MODIFIES: this
    // EFFECTS: Creates the splash image that is displayed upon app start
    private void initializeSplash() {
        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("", new ImageIcon("./images/splashImage.png"), SwingConstants.CENTER));
        window.setBounds(450, 150, 475, 230);
        window.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        window.dispose();
    }

    // MODIFIES: this
    // EFFECTS: sets the desktop pane
    private void initializeDesktop() {
        desktop.setLayout(new BorderLayout());
        setContentPane(desktop);
        setTitle("Nutrition App");
        setSize(WIDTH, HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: sets the optionsPanel onto the desktop
    private void initializeOptionsPanel() {
        createOptions();
        optionsPanel.pack();
        optionsPanel.setVisible(true);
        desktop.add(optionsPanel, BorderLayout.PAGE_START);
    }

    // MODIFIES: this
    // EFFECTS: sets the foodPanel onto the desktop
    private void initializeFoodPanel() {
        createFoodPanel();
        foodPanel.pack();
        foodPanel.setVisible(false);
        desktop.add(foodPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: sets the seeAndRemoveFoodsPanel onto the desktop
    private void initializeSeeAndRemoveFoodsPanel() {
        createSeeAndRemoveFoodsPanel();
        seeAndRemoveFoodsPanel.pack();
        seeAndRemoveFoodsPanel.setVisible(false);
        desktop.add(seeAndRemoveFoodsPanel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: sets the caloriesPanel onto the desktop
    private void initializeCaloriesPanel() {
        createCalorieText();
        caloriesPanel.pack();
        caloriesPanel.setVisible(true);
        desktop.add(caloriesPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Creates the panel that contains buttons for Add Food, See And RemoveFood, Save, Load Up, Exit
    private void createOptions() {
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(1,0));
        options.add(new JButton(new AddFoodAction()));
        options.add(new JButton(new SeeAndRemoveFoodAction()));
        options.add(new JButton(new SaveCalorieCounterAction()));
        options.add(new JButton(new LoadCalorieCounterAction()));
        options.add(new JButton(new ExitAction()));
        optionsPanel.add(options);
    }

    // MODIFIES: this
    // EFFECTS: Creates the panel that contains that buttons for food
    private void createFoodPanel() {
        JPanel foodPanel = new JPanel();
        foodPanel.setLayout(new GridLayout(0,1));
        foodPanel.add(new JButton(new AddRiceAction()));
        foodPanel.add(new JButton(new AddBreadAction()));
        foodPanel.add(new JButton(new AddMilkAction()));
        foodPanel.add(new JButton(new AddPeanutsAction()));
        foodPanel.add(new JButton(new AddPorkChopAction()));
        foodPanel.add(new JButton(new AddChickenBreastAction()));
        this.foodPanel.add(foodPanel);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the panel that displays the foods eaten. Constructs a button that allows you to remove foods
    //          from the display
    private void createSeeAndRemoveFoodsPanel() {
        JPanel removeFoodsPanel = new JPanel();
        this.removeFoodLabel = new JLabel("Remove...");
        removeFoodsPanel.setLayout(new GridLayout(0,1));
        removeFoodsPanel.add(new JButton(new RemoveFoodButtonAction()));
        removeFoodsPanel.add(removeFoodLabel);
        addFoodsToFoodList();
        this.foodJList = new JList<>(foods);
        removeFoodsPanel.add(foodJList);
        removeFoodsPanel.setSize(WIDTH, HEIGHT);
        this.seeAndRemoveFoodsPanel.add(removeFoodsPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds the food's name in calorieCounter's foodList to the foods list
    private void addFoodsToFoodList() {
        for (Food food : calorieCounter.getFoodsEaten()) {
            this.foods.addElement(food.getFoodName());
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates and sets the labels that display the total calories, protein calories, fat calories
    //          and carbohydrate calories
    private void createCalorieText() {
        JPanel caloriesPanel = new JPanel();
        caloriesPanel.setLayout(new GridLayout(0,1));
        totalCaloriesLabel = new JLabel(calorieToString());
        proteinCaloriesLabel = new JLabel(fatCalorieToString());
        fatCaloriesLabel = new JLabel(proteinCalorieToString());
        carbohydrateCaloriesLabel = new JLabel(carbohydrateCalorieToString());
        caloriesPanel.add(totalCaloriesLabel);
        caloriesPanel.add(proteinCaloriesLabel);
        caloriesPanel.add(fatCaloriesLabel);
        caloriesPanel.add(carbohydrateCaloriesLabel);
        this.caloriesPanel.add(caloriesPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Centres the application to the screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: Updates the calorie labels
    private void updateCaloriePanel() {
        totalCaloriesLabel.setText(calorieToString());
        proteinCaloriesLabel.setText(proteinCalorieToString());
        fatCaloriesLabel.setText(fatCalorieToString());
        carbohydrateCaloriesLabel.setText(carbohydrateCalorieToString());
    }

    // MODIFIES: this
    // EFFECTS: Updates the list of foods display on the seeAndRemoveFoodsPanel
    private void updateSeeAndRemoveFoodsPanel() {
        foods.removeAllElements();
        addFoodsToFoodList();
    }

    // EFFECTS: returns a string that indicates how many total calories calorieCounter has
    private String calorieToString() {
        return calorieCounter.getTotalCalories() + " Total Calories";
    }

    // EFFECTS: returns a string that indicates how many fat calories calorieCounter has
    private String fatCalorieToString() {
        return calorieCounter.getCaloriesFromFats() + " Fat Calories";
    }

    // EFFECTS: returns a string that indicates how many protein calories calorieCounter has
    private String proteinCalorieToString() {
        return calorieCounter.getCaloriesFromProtein() + " Protein Calories";
    }

    // EFFECTS: returns a string that indicates how many carbohydrate calories calorieCounter has
    private String carbohydrateCalorieToString() {
        return calorieCounter.getCaloriesFromCarbohydrates() + " Carbohydrate Calories";
    }

    // A class that represents the Add Food button
    private class AddFoodAction extends AbstractAction {

        AddFoodAction() {
            super("Add Food");
        }

        // MODIFIES: this
        // EFFECTS: Will set the foodPanel visibility to true. If it is already true, set it to false
        @Override
        public void actionPerformed(ActionEvent e) {
            if (foodPanel.isVisible()) {
                foodPanel.setVisible(false);
            } else {
                foodPanel.setVisible(true);
            }
        }
    }

    // A class that represents the See and Remove Food button
    private class SeeAndRemoveFoodAction extends AbstractAction {
        SeeAndRemoveFoodAction() {
            super("See and Remove Food");
        }

        // MODIFIES: this
        // EFFECTS: Will set the seeAndRemoveFoodPanel visibility to true. If it is already true, set it to false
        @Override
        public void actionPerformed(ActionEvent e) {
            if (seeAndRemoveFoodsPanel.isVisible()) {
                seeAndRemoveFoodsPanel.setVisible(false);
            } else {
                seeAndRemoveFoodsPanel.setVisible(true);
            }
        }
    }

    // A class that represents the Save button
    private class SaveCalorieCounterAction extends AbstractAction {
        SaveCalorieCounterAction() {
            super("Save");
        }

        // EFFECTS: On button press, will save the current calorieCounter with all the foods eaten to a JSon file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriterMale.open();
                jsonWriterMale.write(calorieCounter);
                jsonWriterMale.close();
            } catch (FileNotFoundException exp) {
                System.out.println("Unable to write to file: " + JSON_STORE_MALE);
            }
        }
    }

    // A class that represents the Load Up button
    private class LoadCalorieCounterAction extends AbstractAction {
        LoadCalorieCounterAction() {
            super("Load Up");
        }

        // MODIFIES: this
        // EFFECTS: On button press, it will change the current calorieCounter to a previously saved calorieCounter file
        //          It also updates the calorie labels and remove foods panel
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                calorieCounter = jsonReaderMale.read();
                calorieCounter.calculateCalories();
                updateCaloriePanel();
                updateSeeAndRemoveFoodsPanel();
            } catch (IOException exp) {
                System.out.println("Unable to read from file: " + JSON_STORE_MALE);
            }
        }
    }

    // A class that represents the Remove button
    private class RemoveFoodButtonAction extends AbstractAction {
        public RemoveFoodButtonAction() {
            super("Click Here To Remove");
        }

        // MODIFIES: this
        // EFFECTS: On button press, will change the removeFoodLabel to say "You removed: (food selected)". Will also
        //          remove the selected item from the foods eaten list and update the caloriesPanel
        @Override
        public void actionPerformed(ActionEvent e) {
            if (foodJList.getSelectedIndex() != -1) {
                removeFoodLabel.setText("You removed: " + foodJList.getSelectedValue());
                calorieCounter.removeFood(foodJList.getSelectedIndex());
                updateSeeAndRemoveFoodsPanel();
                calorieCounter.calculateCalories();
                updateCaloriePanel();
            }
        }
    }

    // A class that represents the Exit button
    private class ExitAction extends AbstractAction {
        public ExitAction() {
            super("Exit");
        }

        // EFFECTS: On button press, it will print the log of foods added and removed to the console and will terminate
        //          the program
        @Override
        public void actionPerformed(ActionEvent e) {
            EventLog el = EventLog.getInstance();
            for (Event next : el) {
                System.out.println(next.getDescription());
            }
            System.exit(0);
        }
    }

    // A class that represents the Rice button
    private class AddRiceAction extends AbstractAction {
        AddRiceAction() {
            super("Rice: 100g, 130 calories");
        }

        // MODIFIES: this
        // EFFECTS: on button press, it will add rice to the calorieCounter and will update the caloriesPanel
        @Override
        public void actionPerformed(ActionEvent e) {
            calorieCounter.addFood(rice);
            calorieCounter.calculateCalories();
            updateCaloriePanel();
            updateSeeAndRemoveFoodsPanel();
        }
    }

    // A class that represents the Bread button
    private class AddBreadAction extends AbstractAction {
        AddBreadAction() {
            super("Bread: 40g, 180 calories");
        }

        // MODIFIES: this
        // EFFECTS: on button press, it will add bread to the calorieCounter and will update the caloriesPanel
        @Override
        public void actionPerformed(ActionEvent e) {
            calorieCounter.addFood(bread);
            calorieCounter.calculateCalories();
            updateCaloriePanel();
            updateSeeAndRemoveFoodsPanel();
        }
    }

    // A class that represents the Milk button
    private class AddMilkAction extends AbstractAction {
        AddMilkAction() {
            super("Milk: 250ml, 127 calories");
        }

        // MODIFIES: this
        // EFFECTS: on button press, it will add milk to the calorieCounter and will update the caloriesPanel
        @Override
        public void actionPerformed(ActionEvent e) {
            calorieCounter.addFood(milk);
            calorieCounter.calculateCalories();
            updateCaloriePanel();
            updateSeeAndRemoveFoodsPanel();
        }
    }

    // A class that represents the Peanuts button
    private class AddPeanutsAction extends AbstractAction {
        AddPeanutsAction() {
            super("Peanuts: 50g, 297 calories");
        }

        // MODIFIES: this
        // EFFECTS: on button press, it will add peanuts to the calorieCounter and will update the caloriesPanel
        @Override
        public void actionPerformed(ActionEvent e) {
            calorieCounter.addFood(peanuts);
            calorieCounter.calculateCalories();
            updateCaloriePanel();
            updateSeeAndRemoveFoodsPanel();
        }
    }

    // A class that represents the Pork Chop button
    private class AddPorkChopAction extends AbstractAction {
        AddPorkChopAction() {
            super("Pork Chop: 100g, 211 calories");
        }

        // MODIFIES: this
        // EFFECTS: on button press, it will add porkChop to the calorieCounter and will update the caloriesPanel
        @Override
        public void actionPerformed(ActionEvent e) {
            calorieCounter.addFood(porkChop);
            calorieCounter.calculateCalories();
            updateCaloriePanel();
            updateSeeAndRemoveFoodsPanel();
        }
    }

    // A class that represents the Chicken Breast button
    private class AddChickenBreastAction extends AbstractAction {
        AddChickenBreastAction() {
            super("Chicken Breast: 56g, 75 calories");
        }

        // MODIFIES: this
        // EFFECTS: on button press, it will add chickenBreast to the calorieCounter and will update the caloriesPanel
        @Override
        public void actionPerformed(ActionEvent e) {
            calorieCounter.addFood(chickenBreast);
            calorieCounter.calculateCalories();
            updateCaloriePanel();
            updateSeeAndRemoveFoodsPanel();
        }
    }

    // Runs the NutritionAppUI
    public static void main(String[] args) {
        new NutritionAppUI();
    }
}

