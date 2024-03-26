package Records;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

class Person extends Record {
    private String name;
    private String surname;
    private LocalDateTime birthday;
    private String gender;

    protected Person(Scanner scanner) {
        super(scanner);
    }

    public void setBirthday(String birthday){
        try{
            this.birthday = LocalDateTime.parse(birthday);
        } catch(DateTimeParseException e){
            System.out.println("Bad birthday!");
        }
    }

    public void setGender(String gender){
        if (gender.matches("^[MF]$")){
            this.gender = gender;
        } else {
            this.gender = noData;
            System.out.println("Bad gender!");
        }
    }

    @Override
    public void promptCreate() {
        promptSetName();
        promptSetSurname();
        promptSetBirthday();
        promptSetGender();
        super.promptCreate();
    }

    private void promptSetName() {
        System.out.print("Enter the name: ");
        name = scanner.nextLine();
    }

    private void promptSetSurname() {
        System.out.print("Enter the surname: ");
        surname = scanner.nextLine();
    }

    private void promptSetBirthday() {
        System.out.print("Enter the birthday: ");
        String birthday = scanner.nextLine();
        setBirthday(birthday);
    }

    private void promptSetGender() {
        System.out.print("Enter the gender: ");
        String gender = scanner.nextLine();
        setGender(gender);
    }

    public void promptEdit(){
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = scanner.nextLine();
        System.out.printf("Enter %s: ", field);
        switch (field){
            case "name" -> promptSetName();
            case "surname" -> promptSetSurname();
            case "birth" -> promptSetBirthday();
            case "gender" -> promptSetGender();
            case "number" -> promptSetNumber();
            default -> throw new IllegalStateException(field);
        }
    }

    @Override
    public void printInfo() {
        System.out.printf(
                """
                        Name: %s
                        Surname: %s
                        Birth date: %s
                        Gender: %s
                        """,
                name, surname, noDataOrBirthday(), noDataOrGender()
        );
        super.printInfo();
    }

    private String noDataOrBirthday() {
        if (birthday == null){
            return noData;
        } else{
            return birthday.toString();
        }
    }

    private String noDataOrGender() {
        return Objects.requireNonNullElse(gender, noData);
    }

    @Override
    public String getName() {
        return name + " " + surname;
    }

    @Override
    public boolean checkForSearchMatch(String query) {
        String queryLowerCase = query.toLowerCase();
        return name.toLowerCase().contains(queryLowerCase)
               || surname.toLowerCase().contains(queryLowerCase)
               || number.contains(queryLowerCase);
    }
}
