package pl.edu.agh.mwo.invoice.product;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BottleOfWine extends Product {
    private static final BigDecimal EXCISE_TAX = new BigDecimal("5.56");

    public BottleOfWine(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.23"));
    }

    @Override
    public BigDecimal getPriceWithTax() {
        BigDecimal taxValue = this.getPrice().multiply(this.getTaxPercent());
        taxValue = taxValue.setScale(2, RoundingMode.HALF_UP); //
        BigDecimal priceWithVAT = this.getPrice().add(taxValue);
        return priceWithVAT.add(EXCISE_TAX).setScale(2, RoundingMode.HALF_UP);
    }

}
