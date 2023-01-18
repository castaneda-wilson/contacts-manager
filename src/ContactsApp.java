import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsApp {
    public static void main(String[] args) throws IOException {
        String directory = "data";
        String filename = "contacts.txt";

        Path dataDirectory = Paths.get(directory);


        Path dataFile = Paths.get(directory, filename);

        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (! Files.exists(dataFile)) {
            Files.createFile(dataFile);
        }

        Path filepath = Paths.get("data", "contacts.txt");
        Scanner sc = new Scanner(System.in);
        boolean response = true;

        while(response) {
            Contact.menu();
            int input = sc.nextInt();
            String userInput;
            switch (input) {
            case 1:
                List<String> allContacts = Files.readAllLines(filepath);
                System.out.println("Name | Phone Number\n-------------------");
                for (String contact : allContacts) {
                    System.out.println(contact);
                }
                System.out.println("Do you want to continue?\nEnter yes or no: (y/n)");
                userInput = sc.next();
                if (userInput.equalsIgnoreCase("y") || userInput.equals("yes")) {
                    response = true;
                } else {
                    response = false;
                }
                break;
            case 2:
                sc.nextLine();
                System.out.println("What is the contact's name?");
                String contactName = sc.nextLine();
                System.out.println("What is the contact's phone number?");
                String phoneNumber = sc.nextLine();
                phoneNumber = "(" + phoneNumber.substring(0,3) + ")" + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6);
                Files.write(
                        filepath,
                        List.of(contactName + " | " + phoneNumber),
                        StandardOpenOption.APPEND
                );
                System.out.println("Do you want to continue?\nEnter yes or no: (y/n)");
                userInput = sc.next();
                if (userInput.equalsIgnoreCase("y") || userInput.equals("yes")) {
                    response = true;
                } else {
                    response = false;
                }
                break;
            case 3:
                sc.nextLine();
                System.out.println("Search contact by name or phone number");
                String searched = sc.nextLine();
                List<String> searchContacts = Files.readAllLines(filepath);
                for (String contact : searchContacts) {
                    if (contact.contains(searched) || contact.toLowerCase().contains(searched)) {
                        System.out.println("Contact is: " + contact);
                    }
                }
                System.out.println("Do you want to continue?\nEnter yes or no: (y/n)");
                userInput = sc.next();
                if (userInput.equalsIgnoreCase("y") || userInput.equals("yes")) {
                    response = true;
                } else {
                    response = false;
                }
                break;
            case 4:
                sc.nextLine();
                System.out.println("Search contact by name or phone number to delete");
                String deleted = sc.nextLine();
                List<String> deletedContacts = Files.readAllLines(filepath);
                for (String contact : deletedContacts) {
                    if (contact.contains(deleted) || contact.toLowerCase().contains(deleted)) {
                        System.out.println("Deleted contact is: " + contact);
                        deletedContacts.remove(contact);
                        break;
                    }
                }
                Files.write(filepath, deletedContacts);
                System.out.println("Do you want to continue?\nEnter yes or no: (y/n)");
                userInput = sc.next();
                if (userInput.equalsIgnoreCase("y") || userInput.equals("yes")) {
                    response = true;
                } else {
                    response = false;
                }
                break;
            case 5:
                System.out.println("Thanks for stopping by...");
                response = false;
                break;
        }
    }
    }
}
