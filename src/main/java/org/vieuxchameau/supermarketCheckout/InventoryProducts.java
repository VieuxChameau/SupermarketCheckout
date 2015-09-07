package org.vieuxchameau.supermarketCheckout;

import java.math.BigDecimal;

public class InventoryProducts {
	public static final Product APPLE = new Product("APPLE", "Granny Smiths", new BigDecimal("1.50"));
	public static final Product BUTTER = new Product("BUTTER", "Unsalted butter", new BigDecimal("0.88"));
	public static final Product CUSTARD = new Product("CUSTARD", "Custard", new BigDecimal("1.43"));
	public static final Product EGG = new Product("EGG", "Eggs", new BigDecimal("0.89"));
	public static final Product FLOUR = new Product("FLOUR", "Plain flour", new BigDecimal("0.45"));
	public static final Product ICE_CREAM = new Product("ICE_CREAM", "Vanilla ice cream", new BigDecimal("2.00"));
	public static final Product SUGAR = new Product("SUGAR", "Brown Sugar", new BigDecimal("1.50"));
}
