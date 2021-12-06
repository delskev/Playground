package be.civadis.gpdoc.solid.ocp;

import java.math.BigDecimal;

public interface TaxCalculator {
    BigDecimal calculateTax(BigDecimal amount);
}
