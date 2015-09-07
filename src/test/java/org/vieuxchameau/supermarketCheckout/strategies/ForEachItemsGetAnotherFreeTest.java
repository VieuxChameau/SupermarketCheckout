package org.vieuxchameau.supermarketCheckout.strategies;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.vieuxchameau.supermarketCheckout.Basket;
import org.vieuxchameau.supermarketCheckout.InventoryProducts;
import org.vieuxchameau.supermarketCheckout.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.vieuxchameau.supermarketCheckout.InventoryProducts.APPLE;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.ICE_CREAM;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.SUGAR;

public class ForEachItemsGetAnotherFreeTest {
	private final PricingStrategy pricingStrategy = new ForEachItemsGetAnotherFree();

	@Test
	public void should_get_saving() {
		final Map<Product, Long> countByProduct = new HashMap<>();
		countByProduct.put(APPLE, 7l);
		countByProduct.put(ICE_CREAM, 3l);
		countByProduct.put(SUGAR, 6l);

		final Basket basket = new Basket(countByProduct);

		pricingStrategy.apply(basket);

		final List<Saving> savings = basket.getSavings();
		Assertions.assertThat(savings).hasSize(1);
		Assertions.assertThat(savings).extracting("savingAmount").containsOnly(new BigDecimal("4.50"));
	}

	@Test
	public void should_not_get_saving() {
		final Map<Product, Long> countByProduct = new HashMap<>();
		countByProduct.put(APPLE, 2l);
		countByProduct.put(InventoryProducts.BUTTER, 1l);

		final Basket basket = new Basket(countByProduct);

		pricingStrategy.apply(basket);

		final List<Saving> savings = basket.getSavings();
		Assertions.assertThat(savings).isEmpty();
	}
}