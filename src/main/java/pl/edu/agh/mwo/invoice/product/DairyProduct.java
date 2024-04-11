package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class DairyProduct extends Product {
    public DairyProduct(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.08"));
        BigDecimal big0 = new BigDecimal(0);
        if (price == null || price.compareTo(big0) < 0) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
    }
}
