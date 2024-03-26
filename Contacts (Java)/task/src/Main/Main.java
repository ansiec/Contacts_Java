package Main;

import java.util.Scanner;
import Menu.Menu;

public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            new Menu(scanner).start();
        }
    }
}
