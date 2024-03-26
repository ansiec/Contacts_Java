package Records;

import java.time.LocalDateTime;
import java.util.Scanner;

public abstract class Record {
    protected String number;
    protected LocalDateTime timeCreated;
    protected LocalDateTime lastTimeEdited;
    protected final String noData = "[no data]";
    protected final Scanner scanner;

    protected Record(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean isNotValidNumber(String number) {
        return !number.matches("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*");
    }

    public void promptCreate() {
        promptSetNumber();

        timeCreated = LocalDateTime.now();
        lastTimeEdited = timeCreated;
    }

    protected void promptSetNumber() {
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();

        if (isNotValidNumber(number)){
            number = "";
            System.out.println("Wrong number format!");
        }
        this.number = number;
    }

    public void printInfo() {
        System.out.printf(
                """
                        Number: %s
                        Time created: %s
                        Time last edit: %s
                        """,
                getNoDataOrNumber(), timeCreated.toString(), lastTimeEdited.toString()
        );
    }

    private String getNoDataOrNumber(){
        return number == null ? noData : number;
    }

    public abstract String getName();

    public abstract boolean checkForSearchMatch(String query);

    public void edit(){
        lastTimeEdited = LocalDateTime.now();
        promptEdit();
        System.out.print("Saved");
    }

    protected abstract void promptEdit();
}
