package seedu.planpal.utility.filemanager;

import seedu.planpal.exceptions.PlanPalExceptions;
import seedu.planpal.utility.Parser;
import seedu.planpal.utility.Ui;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the loading and saving of data for contacts or other objects that implement
 * the {@link Storeable} interface within the PlanPal application.
 *
 * @param <T> The type of objects being managed, which must implement the {@link Storeable} interface.
 */
public class FileManager<T> {
    private static final String ADD_COMMAND = "add";
    private String pathToStorage = "error/file.txt";
    /**
     * Constructs a FileManager for managing a specific type of file.
     * If the provided object implements {@link Storeable}, it will use
     * the object's storage path for file operations.
     *
     * @param typeOfFile An object that implements {@link Storeable}, used to determine the file path.
     */
    public FileManager(T typeOfFile) {
        if (typeOfFile instanceof Storeable){
            this.pathToStorage = ((Storeable)typeOfFile).getStoragePath();
        }
    }

    /**
     * Ensures that the directory for the storage path exists.
     * If the directory does not exist, it creates it.
     */
    private void createDirectory(){
        File file = new File(pathToStorage);
        File directory = file.getParentFile();

        if (!directory.exists()){
            if (directory.mkdirs()){
                System.out.println("Directory created");
            } else {
                System.out.println("Directory could not be created");
            }
        }
    }

    /**
     * Saves a list of objects to the file specified by the {@link Storeable#getStoragePath()}.
     * Each object in the list must implement {@link Storeable}. The objects are saved in
     * to the file using their command description.
     *
     * @param list The list of objects to be saved. Each object must implement {@link Storeable}.
     */
    public void saveList(ArrayList<T> list) {
        createDirectory();
        try (FileWriter writer = new FileWriter(pathToStorage)) {
            if (!list.isEmpty() && list.get(0) instanceof Storeable) {
                for (T item : list) {
                    String commandDescription = ((Storeable) item).getCommandDescription();
                    writer.write(ADD_COMMAND + " " + commandDescription + "\n");
                }
            } else {
                writer.write("");
            }
        } catch (IOException e) {
            Ui.print("Error saving data!");
        }
    }

    /**
     * Loads a list of objects from the file specified by {@link Storeable#getStoragePath()}.
     * The file is parsed line by line, and each command is processed using the provided {@link Parser}.
     *
     * @param parser A {@link Parser} object used to process each line of the file.
     */
    public void loadList(Parser parser) {
        File file = new File(pathToStorage);
        PrintStream out = System.out;

        // Redirect System.out to a dummy steam (solution from gpt)
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {}
        }));

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                parser.processCommand(scanner.nextLine());
            }
        } catch (FileNotFoundException | PlanPalExceptions e){
            Ui.print(e.getMessage());
        }

        System.setOut(out);
    }
}
