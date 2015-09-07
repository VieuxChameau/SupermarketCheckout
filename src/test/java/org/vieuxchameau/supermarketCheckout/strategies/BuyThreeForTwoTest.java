package org.vieuxchameau.supermarketCheckout.strategies;

import org.junit.Test;
import org.vieuxchameau.supermarketCheckout.Basket;
import org.vieuxchameau.supermarketCheckout.InventoryProducts;
import org.vieuxchameau.supermarketCheckout.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.APPLE;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.EGG;

public class BuyThreeForTwoTest {
	final PricingStrategy pricingStrategy = new BuyThreeForTwo();

	@Test
	public void should_get_saving() {
		final Map<Product, Long> countByProduct = new HashMap<>();
		countByProduct.put(APPLE, 7l);
		countByProduct.put(EGG, 1l);

		final Basket basket = new Basket(countByProduct);

		pricingStrategy.apply(basket);

		final List<Saving> savings = basket.getSavings();
		assertThat(savings).hasSize(1);
		assertThat(savings).extracting("savingAmount").containsOnly(new BigDecimal("3.00"));
	}

	@Test
	public void should_not_get_saving() {
		final Map<Product, Long> countByProduct = new HashMap<>();
		countByProduct.put(APPLE, 2l);
		countByProduct.put(EGG, 1l);

		final Basket basket = new Basket(countByProduct);

		pricingStrategy.apply(basket);

		final List<Saving> savings = basket.getSavings();
		assertThat(savings).isEmpty();
	}
}