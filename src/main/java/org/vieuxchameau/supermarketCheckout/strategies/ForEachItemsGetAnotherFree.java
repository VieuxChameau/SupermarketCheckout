package org.vieuxchameau.supermarketCheckout.strategies;

import org.vieuxchameau.supermarketCheckout.Basket;
import org.vieuxchameau.supermarketCheckout.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.ICE_CREAM;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.SUGAR;

/**
 * for each N (equals) items X, you get K items Y for free
 */
public class ForEachItemsGetAnotherFree implements PricingStrategy {
	private static final Set<Product> PRODUCT_SET = new HashSet<>(asList(ICE_CREAM, SUGAR));

	@Override
	public void apply(final Basket basket) {
		if (!PRODUCT_SET.stream().allMatch(basket::containsProduct)) {
			return;
		}

		final long iceCreamQuantity = basket.countForProduct(ICE_CREAM);
		final long sugarQuantity = basket.countForProduct(SUGAR);

		final long minimalQuantity = Math.min(iceCreamQuantity, sugarQuantity);
		basket.addSaving(createSaving(minimalQuantity));
	}

	private Saving createSaving(final long minimalQuantity) {
		return new Saving(getReason(minimalQuantity), getSavingAmount(minimalQuantity));
	}

	private BigDecimal getSavingAmount(final long minimalQuantity) {
		final BigDecimal unitPrice = SUGAR.getUnitPrice();
		return unitPrice.multiply(BigDecimal.valueOf(minimalQuantity));
	}

	private String getReason(final long minimalQuantity) {
		return String.format("For each N (equals) items X, you get K items Y for free (X %d)", minimalQuantity);
	}
}
