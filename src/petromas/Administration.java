package petromas;

public abstract class Administration {

    // Prices for this week
    private final double gasolinePrice = 6.1;
    private final double dieselPrice = 5.7;

    public double getGasolinePrice() {
        return gasolinePrice;
    }

    public double getDieselPrice() {
        return dieselPrice;
    }
}
