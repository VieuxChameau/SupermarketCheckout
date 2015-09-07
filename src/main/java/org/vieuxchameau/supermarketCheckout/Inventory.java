package org.vieuxchameau.supermarketCheckout;

import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.APPLE;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.BUTTER;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.CUSTARD;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.EGG;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.FLOUR;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.ICE_CREAM;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.SUGAR;

public class Inventory {
	private static final Collection<Product> PRODUCTS = new HashSet<>(asList(APPLE, BUTTER, CUSTARD, EGG, FLOUR, ICE_CREAM, SUGAR));

	public static Product findProductByReference(final String reference) {
		return PRODUCTS.stream().filter(p -> p.getReference().equals(reference)).findFirst().orElseThrow(IllegalArgumentException::new);
	}
}
