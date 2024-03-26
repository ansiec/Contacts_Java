package Records;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ContactBook {
    private final ArrayList<Record> contacts = new ArrayList<>();
    private final Scanner scanner;

    public ContactBook(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addPerson() {
        addThing(new Person(scanner));
    }

    public void addOrganization() {
        addThing(new Organization(scanner));
    }

    private void addThing(Record record){
        record.promptCreate();

        contacts.add(record);
        System.out.printf("%s has been added.\n", record.getName());
    }

    public void remove(int index){
        contacts.remove(index-1);
    }

    public int getCount(){
        return contacts.size();
    }

    @Override
    public String toString() {
        return convertRecordsToPrintableList(contacts);
    }

    private String convertRecordsToPrintableList(List<Record> records){
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < records.size(); i++) {
            list.append(i + 1).append(". ").append(records.get(i).getName()).append("\n");
        }
        return list.toString();
    }

    public Record get(int index) {
        return contacts.get(index-1);
    }

    public List<Record> search() {
        System.out.print("Enter search query: ");
        String query = scanner.nextLine().toLowerCase();

        var matches = contacts.stream()
                .filter(s -> s
                        .checkForSearchMatch(query))
                .collect(Collectors.toList());
        System.out.printf("Found %d results:", matches.size());
        if (!matches.isEmpty()){
            System.out.print(convertRecordsToPrintableList(matches));
        }
        return matches;
    }

    public void delete(Record selectedRecord) {
        contacts.remove(selectedRecord);
    }
}

