package petromas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PetromStation implements Administration {

    private double totalPriceToPay;

    protected static final int REFUEL_WITH_GASOLINE = 0;
    protected static final int REFUEL_WITH_DIESEL = 1;

    protected void refuel(Scanner sc, int refuelType) {
        char clientAnswer = 'Y';
        while (clientAnswer == 'Y') {
            System.out.print("Enter the amount of money you want to spend with refueling: ");
            double clientAmount = sc.nextDouble();
            double priceForLiters;
            if (refuelType == REFUEL_WITH_GASOLINE) {
                priceForLiters = clientAmount / this.getGasolinePrice();
            } else {
                priceForLiters = clientAmount / this.getDieselPrice();
            }
            System.out.println("You will refill with " + String.format("%.2f", priceForLiters) + " Liters");
            totalPriceToPay += clientAmount;
            System.out.print("Do you want to add more? (Y/N): ");
            clientAnswer = getClientAnswer(sc);
        }
        askClientToPay(sc);
    }

    private void askClientToPay(Scanner sc) {
        System.out.println("You have to pay " + totalPriceToPay + " RON");
        boolean isNumber = false;
        while (!isNumber) {
            try {
                System.out.print("Enter the amount of money to pay: ");
                double clientMoney = sc.nextDouble();
                checkPayment(sc, clientMoney);
                sc.nextLine();
                isNumber = true;
            } catch (InputMismatchException ime) {
                System.out.println("Please enter digits only!");
                sc.nextLine();
            }
        }
    }

    private void checkPayment(Scanner sc, double clientEnteredMoney) {
        if (clientEnteredMoney == totalPriceToPay) {
            System.out.println("That is correct! Thank you!");
            totalPriceToPay = 0.0;
        } else if (clientEnteredMoney > totalPriceToPay) {
            System.out.println("Thank you! Here is your change of " + (clientEnteredMoney - totalPriceToPay) + " RON");
            totalPriceToPay = 0.0;
        } else if (clientEnteredMoney < totalPriceToPay) {
            double difference = totalPriceToPay - clientEnteredMoney;
            do {
                System.out.println("The amount is incorrect! Please give me " + String.format("%.2f", difference) + " RON");
                System.out.print("Enter the remaining amount: ");
                double remainingAmount = sc.nextDouble();
                difference = difference - remainingAmount;
                if (difference < 0.0) {
                    System.out.println("Thank you! Here is your change for " + -difference + " RON");
                    totalPriceToPay = 0.0;
                    return;
                } else if (difference == 0.0) {
                    System.out.println("That is correct! Thank you!");
                }
            } while (difference != 0.0);
        }
    }

    protected char getClientAnswer(Scanner sc) {
        char clientAnswer;
        do {
            clientAnswer = sc.next().toUpperCase().charAt(0);
            if (clientAnswer != 'Y' && clientAnswer != 'N') {
                System.out.print("Please answer with Y/N : ");
            }
        } while (clientAnswer != 'Y' && clientAnswer != 'N');
        return clientAnswer;
    }

    protected void showGasolinePrice() {
        System.out.println("Today price for 1L of gasoline is " + this.getGasolinePrice() + " RON");
    }

    protected void showDieselPrice() {
        System.out.println("Today price for 1L of diesel is " + this.getDieselPrice() + " RON");
    }

    @Override
    public double getGasolinePrice() {
        return Administration.gasolinePrice;
    }

    @Override
    public double getDieselPrice() {
        return Administration.dieselPrice;
    }
}
