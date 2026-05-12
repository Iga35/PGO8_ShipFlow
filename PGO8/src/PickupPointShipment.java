public class PickupPointShipment extends ShipmentOrder {
    private String lockerSize;
    private boolean fragile;

    public PickupPointShipment(String orderNumber, String customerName, double distanceKm, double baseFee, boolean insured, String lockerSize, boolean fragile) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.lockerSize = lockerSize;
        this.fragile = fragile;
    }

    @Override
    protected void validateSpecificRules() {
        if (!lockerSize.equals("S") && !lockerSize.equals("M") && !lockerSize.equals("L")) {
            throw new IllegalArgumentException("Zły rozmiar.");
        }
    }

    @Override
    protected double calculateBasePrice() {
        return baseFee + (distanceKm * 0.75);
    }

    @Override
    protected double calculateAdditionalFee() {
        double sizeFee = switch (lockerSize) {
            case "S" -> 5.0;
            case "M" -> 10.0;
            case "L" -> 18.0;
            default -> 0.0;
        };
        if (fragile) sizeFee += 12.0;
        return sizeFee;
    }

    @Override
    public String getShipmentType() {
        return "Pickup point";
    }
}