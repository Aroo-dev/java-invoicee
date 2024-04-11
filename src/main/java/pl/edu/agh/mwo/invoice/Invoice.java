package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;
public class Invoice {
private int number;
public static int lastNumber;

private Map<Product, Integer> products = new HashMap<Product, Integer>();

public Invoice() {
    lastNumber++;
    this.number = lastNumber;
}

public void addProduct(Product product) {
    addProduct(product, 1);
}

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }

public BigDecimal getNetTotal() {
    BigDecimal totalNet = BigDecimal.ZERO;
    for (Product product : products.keySet()) {
        BigDecimal quantity = new BigDecimal(products.get(product));
        totalNet = totalNet.add(product.getPrice().multiply(quantity));
    }
    return totalNet;
}

public BigDecimal getTaxTotal() {
    return getGrossTotal().subtract(getNetTotal());
}

public BigDecimal getGrossTotal() {
    BigDecimal totalGross = BigDecimal.ZERO;
    for (Product product : products.keySet()) {
        BigDecimal quantity = new BigDecimal(products.get(product));
        totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
    }
    return totalGross;
}

public int getNumber() {
    return number;
}

    public String printProductsList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Numer faktury: ").append(getNumber()).append("\n");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            sb.append(product.getName()).append(", ")
                    .append(quantity).append(" szt, cena: ")
                    .append(product.getPrice().toString()).append("\n");
        }
        sb.append("Liczba pozycji: ").append(products.size());
        return sb.toString();
    }

    public Integer getProductQuantity(Product product) {
        return products.getOrDefault(product, 0);
    }
}