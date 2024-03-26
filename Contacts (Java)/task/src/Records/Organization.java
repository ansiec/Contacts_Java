package Records;

import java.util.Scanner;

class Organization extends Record {
    private String name;
    private String address;

    protected Organization(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void promptCreate() {
        setName();
        setAddress();
        super.promptCreate();
    }

    private void setName() {
        System.out.print("Enter the name: ");
        name = scanner.nextLine();
    }

    private void setAddress() {
        System.out.print("Enter the address: ");
        address = scanner.nextLine();
    }

    public void promptEdit(){
        System.out.print("Select a field (name, address, number): ");
        String field = scanner.nextLine();
        System.out.printf("Enter %s: ", field);
        switch (field){
            case "name" -> setName();
            case "address" -> setAddress();
            case "number" -> super.promptCreate();
            default -> throw new IllegalStateException(field);
        }
    }

    @Override
    public void printInfo() {
        System.out.printf(
                """
                        Organization name: %s
                        Address: %s
                        """,
                name, address
        );
        super.printInfo();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean checkForSearchMatch(String query) {
        String queryLowerCase = query.toLowerCase();
        return name.toLowerCase().contains(queryLowerCase)
               || address.toLowerCase().contains(queryLowerCase)
               || number.contains(queryLowerCase);
    }
}
