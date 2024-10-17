package seedu.planpal.contacts;

import seedu.planpal.exceptions.IllegalCommandException;
import seedu.planpal.exceptions.PlanPalExceptions;
import seedu.planpal.utility.Editable;
import seedu.planpal.utility.filemanager.Storeable;


/**
 * Represents a contact in the PlanPal application.
 */
public class Contact implements Editable, Storeable {
    private static final String CATEGORY_SEPARATOR = "/";
    private static final String CATEGORY_VALUE_SEPARATOR = ":";
    private static final String STORAGE_PATH = "./data/contacts.txt";
    private String name;
    private String commandDescription;

    /**
     * Default constructor for Contact.
     * Primarily used as an identifier for retrieving the storage path in the FileManager class.
     */
    public Contact() {
        // do nothing (used as an identifier for the fileManager class to get storagePath)
    }

    /**
     * Constructs a new Contact object by parsing the given description string. The description
     * string is split using the CATEGORY_SEPARATOR, and for each category (starting from
     * the second item in the array), it processes the category using the {@link #processEditFunction(String)} method.
     *
     * @param description A string containing the description of the contact, with different
     *                    categories separated by a CATEGORY_SEPARATOR.
     * @throws PlanPalExceptions If an error occurs while processing the categories.
     */
    public Contact(String description) throws PlanPalExceptions {
        setCommandDescription(description);
        String[] categories = description.split(CATEGORY_SEPARATOR);
        if (categories.length == 1) {
            throw new IllegalCommandException();
        }
        assert categories.length >= 2: "Illegal command executed in contact";
        for (int categoryIndex = 1; categoryIndex < categories.length; categoryIndex++) {
            processEditFunction(categories[categoryIndex]);
        }
    }

    /**
     * Checks if this contact's name contains the name of another contact.
     *
     * @param other The other contact to compare with.
     * @return True if this contact's name contains the other contact's name; false otherwise.
     */
    public boolean contains(Contact other) {
        return this.name.toLowerCase().contains(other.getName().toLowerCase());
    }

    /**
     * Processes an edit command for the contact. This method parses the input string
     * to extract the category and the new value, then applies the change to the contact.
     * Currently, only the "name" category is supported.
     *
     * @param input The edit command in the format "category:value", where "category"
     *              specifies the field to edit (e.g., "name") and "value" specifies
     *              the new value for that field.
     * @throws PlanPalExceptions If the input is incomplete or improperly formatted.
     * @throws IllegalCommandException If the specified category is not recognized.
     */
    @Override
    public void processEditFunction(String input) throws PlanPalExceptions {
        String[] inputParts = input.split(CATEGORY_VALUE_SEPARATOR);
        if (inputParts.length < 2) {
            throw new PlanPalExceptions("The command is incomplete. Please provide a value for " + inputParts[0]);
        }
        String category = inputParts[0].trim();
        String valueToEdit = inputParts[1].trim();
        if (category.equals("name")) {
            setName(valueToEdit);
        } else {
            System.out.println(category + " is not a valid category");
            throw new IllegalCommandException();
        }
        setCommandDescription(category, valueToEdit);
    }

    // A string representation of a Contact
    @Override
    public String toString() {
        return "[Name = " + name + "]";
    }

    @Override
    public void setCommandDescription(String description) {
        this.commandDescription = description;
    }

    /**
     * Updates the command description by modifying the value of the specified category.
     * The method splits the current command description into its categories and updates the
     * category with the new value, if it exists.
     *
     * @param categoryToChange The category whose value needs to be updated (e.g., "name").
     * @param newValue The new value for the specified category.
     */
    public void setCommandDescription(String categoryToChange, String newValue) {
        String newCommandDescription = "";
        String[] categoryParts = commandDescription.split(CATEGORY_SEPARATOR);
        for (int i = 1; i < categoryParts.length; i++) {
            if (categoryParts[i].startsWith(categoryToChange)) {
                categoryParts[i] = categoryToChange + CATEGORY_VALUE_SEPARATOR + newValue;
            }
            newCommandDescription += CATEGORY_SEPARATOR + categoryParts[i] + " ";
        }
        setCommandDescription(newCommandDescription);
    }

    @Override
    public String getCommandDescription() {
        return commandDescription;
    }

    @Override
    public String getStoragePath() {
        return STORAGE_PATH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
