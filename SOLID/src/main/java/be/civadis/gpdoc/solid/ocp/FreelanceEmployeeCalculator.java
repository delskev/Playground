package be.civadis.gpdoc.solid.ocp;

import java.math.BigDecimal;

public class FreelanceEmployeeCalculator implements TaxCalculator {
    @Override
    public BigDecimal calculateTax(BigDecimal amount) {
        return amount.multiply(new BigDecimal("0,30"));
    }
}
