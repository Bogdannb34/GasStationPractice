package petromas;

import java.util.Scanner;

import static petromas.PetromStation.REFUEL_WITH_DIESEL;
import static petromas.PetromStation.REFUEL_WITH_GASOLINE;

public class Menu {

    public void startPumping(Scanner sc) {
        chooseMenuOption(sc);
    }

    private void showMenu() {
        System.out.println("\t 1. Show price/L for gasoline");
        System.out.println("\t 2. Show price/L for diesel");
        System.out.println("\t 3. Refuel with gasoline");
        System.out.println("\t 4. Refuel with diesel");
    }

    private void chooseMenuOption(Scanner sc) {
        int optionChosen;
        char clientResponse;
        do {
            do {
                System.out.println("Welcome to PETROMAS");
                showMenu();
                System.out.print("Please choose an option: ");
                optionChosen = sc.nextInt();
                switch (optionChosen) {
                    case 1:
                        new PetromStation().showGasolinePrice();
                        break;
                    case 2:
                        new PetromStation().showDieselPrice();
                        break;
                    case 3:
                        new PetromStation().refuel(sc, REFUEL_WITH_GASOLINE);
                        break;
                    case 4:
                        new PetromStation().refuel(sc, REFUEL_WITH_DIESEL);
                        break;
                    default:
                        System.out.println("Please choose a valid option!");
                }
            } while (optionChosen < 1 || optionChosen > 4);
            System.out.println("Do you want to choose another menu option ? (Y/N)");
            clientResponse = new PetromStation().getClientAnswer(sc);
            if (clientResponse == 'N') {
                sayGoodBye();
            }
        } while (clientResponse == 'Y');
    }

    private void sayGoodBye() {
        System.out.println("Thank you for chosen us! :)");
        System.out.println("GoodBye! See you next time!");
    }
}
