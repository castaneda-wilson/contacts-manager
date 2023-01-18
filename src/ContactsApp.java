import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class ContactsApp {
    public static void main(String[] args) throws IOException {
        String directory = "data";
        String filename = "contacts.txt";
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);
        Path filepath = Paths.get("data", "contacts.txt");
        Scanner sc = new Scanner(System.in);

        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (! Files.exists(dataFile)) {
            Files.createFile(dataFile);
        }

        boolean response = true;
        while(response) {
            menu();
            String input = sc.next();
            String userInput;
            switch (input) {
                case "1" -> {
                    List<String> allContacts = Files.readAllLines(filepath);
                    System.out.println("Name | Phone Number\n-------------------");
                    for (String contact : allContacts) {
                        System.out.println(contact);
                    }
                    System.out.println("Do you want to continue?\nEnter yes or no: (y/n)");
                    userInput = sc.next();
                    response = userInput.equalsIgnoreCase("y") || userInput.equals("yes");
                }
                case "2" -> {
                    sc.nextLine();
                    System.out.println("What is the contact's name?");
                    String contactName = sc.nextLine();
                    System.out.println("What is the contact's phone number?");
                    String phoneNumber = sc.nextLine();
                    Contact newContact = new Contact(contactName, phoneNumber);
                    Files.write(
                            filepath,
                            List.of(newContact.getName() + " | " + newContact.getPhoneNumber()),
                            StandardOpenOption.APPEND
                    );
                    System.out.println("Do you want to continue?\nEnter yes or no: (y/n)");
                    userInput = sc.next();
                    response = userInput.equalsIgnoreCase("y") || userInput.equals("yes");
                }
                case "3" -> {
                    sc.nextLine();
                    System.out.println("Search contact by name or phone number");
                    String searched = sc.nextLine();
                    List<String> searchContacts = Files.readAllLines(filepath);
                    for (String contact : searchContacts) {
                        if (contact.contains(searched) || contact.toLowerCase().contains(searched)) {
                            System.out.println("Contact is: " + contact);
                        }
                    }
                    System.out.println("\nDo you want to continue?\nEnter yes or no: (y/n)");
                    userInput = sc.next();
                    response = userInput.equalsIgnoreCase("y") || userInput.equals("yes");
                }
                case "4" -> {
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
                    response = userInput.equalsIgnoreCase("y") || userInput.equals("yes");
                }
                case "5" -> {
                    System.out.println("Thanks for stopping by...");
                    response = false;
                }
                default -> System.out.println("Enter a number 1 through 5\n");

            }
    }
    }

    public static void menu() {
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5): ");
    }
}
