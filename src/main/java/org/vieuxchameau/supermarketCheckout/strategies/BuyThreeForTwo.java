package org.vieuxchameau.supermarketCheckout.strategies;

import org.vieuxchameau.supermarketCheckout.Basket;
import org.vieuxchameau.supermarketCheckout.InventoryProducts;
import org.vieuxchameau.supermarketCheckout.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * Buy 3 (equals) items and pay only for 2
 */
public class BuyThreeForTwo implements PricingStrategy {
	private static final Set<Product> PRODUCT_SET = new HashSet<>(asList(InventoryProducts.APPLE));

	@Override
	public void apply(final Basket basket) {
		for (final Product product : PRODUCT_SET) {
			if (basket.containsProduct(product)) {
				final long countForProduct = basket.countForProduct(product);
				if (canStrategyApply(countForProduct)) {
					final long numberOfFreeProduct = countForProduct / 3;
					basket.addSaving(createSaving(product, numberOfFreeProduct));
				}
			}
		}
	}

	private Saving createSaving(final Product product, final long numberOfFreeProduct) {
		return new Saving(getReason(numberOfFreeProduct, product), getSavingAmount(numberOfFreeProduct, product));
	}

	private BigDecimal getSavingAmount(final long numberOfFreeProduct, final Product product) {
		return product.getUnitPrice().multiply(BigDecimal.valueOf(numberOfFreeProduct));
	}

	private String getReason(final long numberOfFreeProduct, final Product product) {
		return String.format("Buy 3 %s for 2 (X %d)", product.getDisplayName(), numberOfFreeProduct);
	}

	private boolean canStrategyApply(final long countForProduct) {
		return countForProduct >= 3;
	}
}
