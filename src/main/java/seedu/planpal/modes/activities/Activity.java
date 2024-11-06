package seedu.planpal.modes.activities;

import seedu.planpal.exceptions.IllegalCommandException;
import seedu.planpal.exceptions.PlanPalExceptions;
import seedu.planpal.utility.Editable;
import seedu.planpal.utility.filemanager.Storeable;

import java.lang.reflect.Field;

/**
 * Represents an activity in the PlanPal application.
 */
public class Activity implements Editable, Storeable {
    private static final String STORAGE_PATH = "./data/activities/activities.txt";
    private static final String CATEGORY_SEPARATOR = "/";
    private static final String CATEGORY_VALUE_SEPARATOR = ":";
    private String commandDescription;
    private String name;
    private String type;

    /**
     * Constructs an Activity object from a command description.
     *
     * @param description The command description containing name and activityType separated by categories.
     * @throws PlanPalExceptions If the description is invalid or incomplete.
     */
    public Activity(String description) throws PlanPalExceptions {
        if (!description.contains("/name:")) {
            throw new PlanPalExceptions("You need a name for an activity.");
        }
        if (!description.contains("/type:")) {
            type = "others";
        }
        setCommandDescription(description);
        String[] categories = description.split(CATEGORY_SEPARATOR);
        if (categories.length <= 1) {
            throw new IllegalCommandException();
        }
        assert categories.length >= 2: "Illegal command executed";
        for (int categoryIndex = 1; categoryIndex < categories.length; categoryIndex++) {
            processEditFunction(categories[categoryIndex]);
        }
    }

    /**
     * Returns a string representation of the activity.
     *

     * @return A string in the format: [activity = name, type = type]
     */
    @Override
    public String toString() {
        return "[activity = " + name + ", type = " + type + "]";
    }

    /**
     * Processes an edit command for a given input, updating the expense's attributes.
     *
     * @param input The input containing a category and its new value.
     * @throws PlanPalExceptions If the input is incomplete or contains an invalid category.
     * @throws IllegalCommandException If the category is not valid
     */
    @Override
    public void processEditFunction(String input) throws PlanPalExceptions {
        if (input.isEmpty()) {
            throw new IllegalCommandException();
        }

        String[] inputParts = input.split(CATEGORY_VALUE_SEPARATOR, 2);
        if (inputParts.length < 2) {
            throw new PlanPalExceptions("The command is incomplete. Please provide a value for " + inputParts[0]);
        }

        assert inputParts.length >= 2 : "Input must contain category and value";

        String category = inputParts[0].trim();
        String valueToEdit = inputParts[1].trim();

        if (category.equals("name")) {
            setName(valueToEdit);
        } else if (category.equals("type")) {
            setActivityType(valueToEdit);
        } else {
            System.out.println(category + "is not a valid category.");
            throw new IllegalCommandException();
        }
        setCommandDescription(category, valueToEdit);
    }

    /**
     * Updates the command description by modifying the value of the specified category.
     * The method splits the current command description into its categories and updates the
     * category with the new value, if it exists.
     *
     * @param category The category whose value needs to be updated (e.g., "name").
     * @param val The new value for the specified category.
     *
     * @throws PlanPalExceptions If the input is incomplete or improperly formatted.
     * @throws IllegalCommandException If the specified category is not recognized.
     */
    // Overloading function
    public void setCommandDescription(String category, String val) throws PlanPalExceptions {
        boolean isCategory = false;
        for (String cat : ActivityManager.INFORMATION_CATEGORIES) {
            if (category.equals(cat)) {
                isCategory = true;
                try {
                    Field field = this.getClass().getDeclaredField(category);
                    field.setAccessible(true);
                    field.set(this, val);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new PlanPalExceptions(e.getMessage());
                }
            }
        }
        if (!isCategory) {
            throw new IllegalCommandException();
        }
        commandDescription = "";
        if (name != null) {
            commandDescription += CATEGORY_SEPARATOR + "name" + CATEGORY_VALUE_SEPARATOR + name + " ";
        }
        if (type != null) {
            commandDescription += CATEGORY_SEPARATOR + "type" + CATEGORY_VALUE_SEPARATOR + type + " ";
        }
    }

    @Override
    public void setCommandDescription(String description) {
        this.commandDescription = description;
    }

    @Override
    public String getCommandDescription() {
        return commandDescription;
    }

    @Override
    public String getStoragePath() {
        return STORAGE_PATH;
    }

    /**
     * Sets the name of the activity.
     *
     * @param name The name of the activity.
     * @throws PlanPalExceptions If the name is null or empty.
     */
    public void setName(String name) throws PlanPalExceptions {
        if (name == null || name.isEmpty()) {
            throw new PlanPalExceptions("Name cannot be blank.");
        }
        this.name = name;
    }

    /**
     * Sets the type of the activity.
     *
     * @param type The type of the activity.
     * @throws PlanPalExceptions If the activityType is null or empty.
     */
    public void setActivityType(String type) throws PlanPalExceptions {
        if (type == null || type.isEmpty()) {
            throw new PlanPalExceptions("Activity type cannot be blank.");
        }
        this.type = type;
    }

    /**
     * Gets the name of the activity
     *
     * @return name The name of the activity
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the activity
     *
     * @return activityType The type of the activity
     */
    public String getActivityType() {
        return type;
    }
}

