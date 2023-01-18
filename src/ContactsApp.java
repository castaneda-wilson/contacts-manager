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

        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5): ");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        Contact jeff = new Contact("Jeff", "2101233434");
        Contact sarah = new Contact("Sarah", "1231231233");
        Contact joe = new Contact("Joe", "9996669966");
        ArrayList<Contact> contacts = new ArrayList<>();
        ArrayList<String> newContacts = new ArrayList<>();
        newContacts.add(jeff.getName() + " | " + jeff.getPhoneNumber());
        newContacts.add(sarah.getName() + " | " + sarah.getPhoneNumber());
        newContacts.add(joe.getName() + " | " + joe.getPhoneNumber());
//        System.out.println(contacts);
//        Files.write(filepath, newContacts);


        switch(input) {
            case 1:
                List<String> allContacts = Files.readAllLines(filepath);
                for (String contact : allContacts) {
                    System.out.println(contact);
                }
                break;
            case 2:
                sc.nextLine();
                System.out.println("What is the contact's name?");
                String contactName = sc.nextLine();
                System.out.println("What is the contact's phone number?");
                String contactPhoneNumber = sc.nextLine();
                Files.write(
                        filepath,
                        List.of(contactName + " | " + contactPhoneNumber),
                        StandardOpenOption.APPEND
                );
                break;
            case 3:
                sc.nextLine();
                System.out.println("Search contact by name or phone number");
                String searched = sc.nextLine();
                List<String> searchContacts = Files.readAllLines(filepath);
                for (String contact : searchContacts) {
                    if (contact.contains(searched) || contact.toLowerCase().contains(searched)){
                        System.out.println("Contact is: " + contact);
                    }
                }
                break;

            case 4:
                sc.nextLine();
                System.out.println("Search contact by name or phone number to delete");
                String deleted = sc.nextLine();
                List<String> deletedContacts = Files.readAllLines(filepath);
                for (String contact : deletedContacts) {
                    if (contact.contains(deleted) || contact.toLowerCase().contains(deleted)){
                        System.out.println("Deleted contact is: " + contact);
                        deletedContacts.remove(contact);
                        break;
                    }
                }
                Files.write(filepath, deletedContacts);
                break;
            case 5:
                System.out.println("exiting");
                break;
        }

    }
}
