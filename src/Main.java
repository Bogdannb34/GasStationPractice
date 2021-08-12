import petromas.Menu;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // client
        Menu menu = new Menu();
        menu.startPumping(sc);

    }
}
