package org.vieuxchameau.supermarketCheckout.strategies;

import org.vieuxchameau.supermarketCheckout.Basket;
import org.vieuxchameau.supermarketCheckout.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.CUSTARD;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.EGG;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.FLOUR;

/**
 * buy 3 (in a set of items) and the cheapest is free
 */
public class BuyThreeCheapestFree implements PricingStrategy {
	private static final Set<Product> PRODUCT_SET = new HashSet<>(asList(CUSTARD, EGG, FLOUR));

	@Override
	public void apply(final Basket basket) {
		long minimalQuantity = Long.MAX_VALUE;
		Product cheapestProduct = null;
		BigDecimal cheapestPrice = null;

		for (final Product product : PRODUCT_SET) {
			if (!basket.containsProduct(product)) {
				return;
			}

			final long countForProduct = basket.countForProduct(product);
			if (countForProduct < minimalQuantity) {
				minimalQuantity = countForProduct;
			}

			if (cheapestPrice == null || isProductPriceLowerThan(product, cheapestPrice)) {
				cheapestProduct = product;
				cheapestPrice = product.getUnitPrice();
			}
		}

		basket.addSaving(createSaving(minimalQuantity, cheapestProduct));
	}

	private boolean isProductPriceLowerThan(final Product product, final BigDecimal cheapestPrice) {
		return product.getUnitPrice().compareTo(cheapestPrice) < 0;
	}

	private Saving createSaving(final long minimalQuantity, final Product cheapestProduct) {
		return new Saving(getReason(minimalQuantity), getSavingAmount(cheapestProduct, minimalQuantity));
	}

	private BigDecimal getSavingAmount(final Product cheapestProduct, final long minimalQuantity) {
		return cheapestProduct.getUnitPrice().multiply(BigDecimal.valueOf(minimalQuantity));
	}

	private String getReason(final long minimalQuantity) {
		return String.format("Buy one of each (%s) - cheapest is free (X %d)", getProductNames(), minimalQuantity);
	}

	public String getProductNames() {
		return PRODUCT_SET.stream().map(Product::getDisplayName).collect(Collectors.joining(", "));
	}
}
