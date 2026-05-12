public abstract class ShipmentOrder implements SummaryPrintable {
    protected String orderNumber;
    protected String customerName;
    protected double distanceKm;
    protected double baseFee;
    protected boolean insured;
    protected double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber, String customerName, double distanceKm, double baseFee, boolean insured) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
    }

    public final void processOrder() {         // Szablon z polecania zadania
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }

    private void validateOrder() {
        if (orderNumber == null || orderNumber.isEmpty() || distanceKm <= 0) {
            throw new IllegalArgumentException("Nieprawidłowe zamówienie.");
        }
    }

    protected void validateSpecificRules() {

    }

    private double applyInsurance(double price) {
        return insured ? price * 1.07 : price;
    }

    protected double applyBusinessDiscount(double price) {
        return price;
    }

    private void printProcessingResult() {
        System.out.println("Zamówienie " + orderNumber + " dla " + customerName);
    }

    @Override
    public String buildSummaryLine() {
        return String.format("Numer zamówienia: " + orderNumber + "Klient: "  + customerName + "Typ: " + getShipmentType() + "Cena: " + lastCalculatedPrice;
    }


    protected abstract double calculateBasePrice();
    protected abstract double calculateAdditionalFee();
    public abstract String getShipmentType();
}