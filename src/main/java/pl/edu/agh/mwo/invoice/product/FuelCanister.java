package pl.edu.agh.mwo.invoice.product;
import java.math.BigDecimal;

public class FuelCanister extends Product {
    private static final BigDecimal EXCISE_TAX = new BigDecimal("5.56");

    public FuelCanister(String name, BigDecimal price) {
        super(name, price, BigDecimal.ZERO);
    }

    @Override
    public BigDecimal getPriceWithTax() {

        return super.getPriceWithTax().add(EXCISE_TAX);
    }
}
