/*
README: FileHandlingUtility.java
---------------------------------

This Java program demonstrates basic file handling operations:
creating a file, writing to it, appending content, and reading the file.

Features:
- Creates a file if it doesn't exist
- Writes initial content (overwrites existing)
- Appends new content to the file
- Reads and displays content from the file

How to Run:
1. Save this file as FileHandlingUtility.java
2. Compile it:
   javac FileHandlingUtility.java
3. Run the program:
   java FileHandlingUtility

Expected Output:
- File creation message (or already exists)
- Write and append confirmation
- Display of file contents before and after appending

*/

import java.io.*;

public class FileHandlingUtility {
    
    private static final String FILE_NAME = "example.txt";

    public static void main(String[] args) {
        createFile();
        writeToFile("This is the initial content in the file by Prashant.\n");
        readFile("Initial file contents:");

        appendToFile("This is the appended line.\n");
        readFile("Updated file contents:");
    }

    // 1. Create the file if it does not exist
    private static void createFile() {
        File file = new File(FILE_NAME);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    // 2. Write to the file (overwrites existing content)
    private static void writeToFile(String content) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // 3. Append to the file
    private static void appendToFile(String content) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(content);
            System.out.println("Content appended successfully.");
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
        }
    }

    // 4. Read and print the file content
    private static void readFile(String heading) {
        System.out.println("\n" + heading);
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("   " + line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
