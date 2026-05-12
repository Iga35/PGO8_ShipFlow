public final void processOrder() {
    validateOrder();
    validateSpecificRules();

    double price = calculateBasePrice();
    price += calculateAdditionalFee();
    price = applyInsurance(price);
    price = applyBusinessDiscount(price);

    lastCalculatedPrice = price;
    printProcessingResult();
}