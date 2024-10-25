package seedu.planpal.utility;

import seedu.planpal.contacts.Contact;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides user interface functionalities for the PlanPal application.
 */
public class Ui {
    protected static final String LINE_SEPARATOR = "_________________________________________________________";
    /**
     * Prints a series of messages enclosed between line separators.
     * This method is useful for clear and structured display of information in the console.
     *
     * @param messages an array of messages to be printed; each message is printed on a new line
     */
    public static void print(String... messages){
        System.out.println(LINE_SEPARATOR);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a welcome message to the user at the start of the application.
     */
    public static void printWelcomeMessage() {
        print(
                "Hello! I'm PlanPal.",
                "How can I be of service?"
        );
    }

    public static void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    public static void printAvailableModes(){
        print(
                "Currently, I am able to manage the following:",
                "   1. Contacts",
                "   2. Expenses",
                "   3. Activities",
                "What would you like me to manage?",
                "To select, type in the index! (Eg. To manage Contacts, type 1)"
        );
    }

    /**
     * Displays a goodbye message to the user upon exiting the application.
     */
    public static void printByeMessage() {
        print("Bye. Hope to see you again!");
    }

    public static void printCreateStorage(String pathToStorage) {
        print("Created a new storage at ", pathToStorage);
    }

    /**
     * Displays functions of category
     */
    public static String getSetCategory() {
        System.out.println("Category options : ## currently do not support loading and saving ##");
        System.out.println("1. add Category type (e.g. add close friend)");
        System.out.println("2. remove Category type (e.g. remove emergency)");
        System.out.println("3. edit Category of Contact (e.g. edit 1 friend/family, to delete all category: edit 1 /)");
        System.out.println("4. view Category lists (e.g. view)");
        System.out.println("5. quit");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Displays list of categories
     */
    public static void printCategoryList(ArrayList<String> categoryList) {
        System.out.println(LINE_SEPARATOR);
        for (String category : categoryList) {
            System.out.println(category);
        }
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays contacts of particular type of category
     *
     * @param cat category to search
     * @param listByCat 2d ArrayList of Contact where i-th element in listByCat is the contacts in category of index i
     * @param catList ArrayList of Categories and their corresponding position in this array is their index
     */
    public static void printCat(String cat, ArrayList<ArrayList<Contact>> listByCat, ArrayList<String> catList) {
        System.out.println("Contacts in category: " + cat);
        if (listByCat.get(catList.indexOf(cat)).isEmpty()) {
            System.out.println("There is no contact in " + cat);
            System.out.println(LINE_SEPARATOR);
        } else {
            for (Contact contact : listByCat.get(catList.indexOf(cat))) {
                System.out.println(contact);
            }
            System.out.println(LINE_SEPARATOR);
        }
    }

    /**
     * Displays when category is not found
     */
    public static void printCategoryNotFound() {
        System.out.println("Category not found.");
        System.out.println(LINE_SEPARATOR);
    }
}
