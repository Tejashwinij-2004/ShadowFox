import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Contact class
class Contact {
    String name;
    String phone;
    String email;

    Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    void display(int index) {
        System.out.println((index + 1) + ". Name: " + name +
                ", Phone: " + phone +
                ", Email: " + email);
    }
}

public class ContactManager {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Contact> contacts = new ArrayList<>();

    // ================= INPUT HANDLING =================
    public static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = sc.nextInt();
                sc.nextLine(); // ✅ FIX: clear leftover newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number.");
                sc.next(); // clear wrong input
            }
        }
    }

    public static String getStringInput(String message) {
        System.out.print(message);
        return sc.nextLine(); // ✅ FIX: no extra nextLine()
    }

    // ================= CRUD OPERATIONS =================
    public static void addContact() {
        String name = getStringInput("Enter name: ");
        String phone = getStringInput("Enter phone: ");
        String email = getStringInput("Enter email: ");

        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully!");
    }

    public static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found!");
            return;
        }

        System.out.println("\n--- Contact List ---");
        for (int i = 0; i < contacts.size(); i++) {
            contacts.get(i).display(i);
        }
    }

    public static void updateContact() {
        viewContacts();
        if (contacts.isEmpty()) return;

        int index = getIntInput("Enter contact number to update: ") - 1;

        if (index < 0 || index >= contacts.size()) {
            System.out.println("Invalid contact number!");
            return;
        }

        String name = getStringInput("Enter new name: ");
        String phone = getStringInput("Enter new phone: ");
        String email = getStringInput("Enter new email: ");

        contacts.set(index, new Contact(name, phone, email));
        System.out.println("Contact updated successfully!");
    }

    public static void deleteContact() {
        viewContacts();
        if (contacts.isEmpty()) return;

        int index = getIntInput("Enter contact number to delete: ") - 1;

        if (index < 0 || index >= contacts.size()) {
            System.out.println("Invalid contact number!");
            return;
        }

        contacts.remove(index);
        System.out.println("Contact deleted successfully!");
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====== CONTACT MANAGEMENT SYSTEM ======");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("0. Exit");

            choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addContact();
                    break;

                case 2:
                    viewContacts();
                    break;

                case 3:
                    updateContact();
                    break;

                case 4:
                    deleteContact();
                    break;

                case 0:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}