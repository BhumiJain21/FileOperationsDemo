import java.io.*;
import java.util.*;

public class FileOperationsPro {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter file name (with .txt): ");
        String fileName = scanner.nextLine();

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Write to File");
            System.out.println("2. Read File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> writeToFile(fileName);
                case 2 -> readFromFile(fileName);
                case 3 -> modifyFile(fileName);
                case 4 -> {
                    System.out.println("Exiting. Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again!");
            }
        }
    }

    public static void writeToFile(String fileName) {
        System.out.println("Enter content to write (type 'end' to finish):");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            String line;
            while (!(line = scanner.nextLine()).equalsIgnoreCase("end")) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readFromFile(String fileName) {
        System.out.println("\nReading File Content:");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void modifyFile(String fileName) {
        System.out.print("Enter word to replace: ");
        String target = scanner.nextLine();
        System.out.print("Enter replacement word: ");
        String replacement = scanner.nextLine();

        List<String> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                fileContent.add(line.replace(target, replacement));
            }
        } catch (IOException e) {
            System.out.println("Error reading during modification: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String modifiedLine : fileContent) {
                writer.write(modifiedLine);
                writer.newLine();
            }
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error writing modified content: " + e.getMessage());
        }
    }
}