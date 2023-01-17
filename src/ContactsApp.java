import java.util.ArrayList;
import java.util.Scanner;

public class ContactsApp {
    public static void main(String[] args) {
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5): ");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        ArrayList<String> contacts = new ArrayList<>();
        contacts.add("Jeff");
        System.out.println(contacts);

        switch(input) {
            case 1:
                System.out.println("all the contacts");
                break;
            case 2:
                System.out.println("adding contact");
                break;
            case 3:
                System.out.println("searching...");
                break;
            case 4:
                System.out.println("delete");
                break;
            case 5:
                System.out.println("exiting");
                break;
        }

    }
}
