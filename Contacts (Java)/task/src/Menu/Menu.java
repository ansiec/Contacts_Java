package Menu;

import Records.ContactBook;
import Records.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private boolean alive = true;
    private final ContactBook contactBook;
    private List<Record> queriedRecords = new ArrayList<>();
    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
        this.contactBook = new ContactBook(scanner);
    }

    public void start() {
        mainMenu();
    }

    private void mainMenu(){
        while (alive) {
            System.out.print("\nEnter action (add, list, search, count, exit): ");
            switch (scanner.nextLine()) {
                case "add" -> add();
                case "search" -> {
                    search();
                    searchMenu();
                }
                case "remove" -> remove();
                case "count" -> count();
                case "list" -> listMenu();
                case "exit" -> exit();
                default -> System.out.print("Invalid action.");
            }
        }
    }

    private void searchMenu(){
        System.out.print("\n[search] Enter action ([number], back, again): ");
        String input = scanner.nextLine();
        switch (input) {
            case "back" -> {}
            case "again" -> {
                search();
                searchMenu();
            }
            case "exit" -> exit();
            default -> {
                try {
                    Record selectedRecord = queriedRecords.get(Integer.parseInt(input)-1);
                    selectedRecord.printInfo();
                    recordMenu(selectedRecord);
                } catch (NumberFormatException e){
                    System.out.print("Invalid action.");
                }
            }
        }
    }

    private void recordMenu(Record selectedRecord){
        System.out.print("\n[record] Enter action (edit, delete, menu): ");
        String input = scanner.nextLine();
        switch (input) {
            case "edit" -> {
                selectedRecord.edit();
                selectedRecord.printInfo();
            }
            case "delete" -> contactBook.delete(selectedRecord);
            case "menu" -> {}
            case "exit" -> exit();
            default -> System.out.print("Invalid action.");
            }
        }

    private void add() {
        System.out.print("Enter the type (person, organization): ");
        switch(scanner.nextLine()){
            case "person" -> contactBook.addPerson();
            case "organization" -> contactBook.addOrganization();
            default -> System.out.println("Invalid type");
        }
    }

    private void search() {
        queriedRecords = contactBook.search();
    }

    private void remove() {
        if (contactBook.getCount() == 0) {
            System.out.println("No records to remove!");
        } else {
            System.out.println(contactBook);
            System.out.print("Select a record: ");
            int index = scanner.nextInt();
            contactBook.remove(index);
            System.out.println("The record has been removed!");
        }
    }

    private void count() {
        System.out.printf("The Phone Book has %d records.\n", contactBook.getCount());
    }

    private void listMenu() {
        System.out.print(contactBook);

        System.out.print("\n[list] Enter action ([number], back): ");
        String input = scanner.nextLine();
        try {
            Record selectedRecord = contactBook.get(Integer.parseInt(input));
            selectedRecord.printInfo();
            recordMenu(selectedRecord);
        } catch (NumberFormatException e) {
            System.out.print("Invalid index.");
        }
    }

    private void exit() {
        alive = false;
    }
}