package org.vieuxchameau.supermarketCheckout.strategies;

import org.vieuxchameau.supermarketCheckout.Basket;
import org.vieuxchameau.supermarketCheckout.InventoryProducts;
import org.vieuxchameau.supermarketCheckout.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * buy 2 (equals) items for a special price
 */
public class BuyTwoForSpecialPrice implements PricingStrategy {
	private static final Set<Product> PRODUCT_SET = new HashSet<>(asList(InventoryProducts.BUTTER));
	private static final BigDecimal REDUCTION_FOR_TWO = new BigDecimal("0.20");

	@Override
	public void apply(final Basket basket) {
		for (final Product product : PRODUCT_SET) {
			if (basket.containsProduct(product)) {
				final long countForProduct = basket.countForProduct(product);
				if (canStrategyApply(countForProduct)) {
					final long applyNtimes = countForProduct / 2;
					basket.addSaving(createSaving(product, applyNtimes));
				}
			}
		}
	}

	private Saving createSaving(final Product product, final long applyNtimes) {
		return new Saving(getReason(applyNtimes, product), getSavingAmount(applyNtimes));
	}

	private BigDecimal getSavingAmount(final long applyNtimes) {
		return REDUCTION_FOR_TWO.multiply(BigDecimal.valueOf(applyNtimes));
	}

	private String getReason(final long applyNtimes, final Product product) {
		return String.format("Buy 2 %s for special price (X %d)", product.getDisplayName(), applyNtimes);
	}

	private boolean canStrategyApply(final long countForProduct) {
		return countForProduct >= 2;
	}
}
