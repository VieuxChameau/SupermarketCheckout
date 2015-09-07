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
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.BUTTER;

public class BuyTwoForSpecialPriceTest {

	private final PricingStrategy pricingStrategy = new BuyTwoForSpecialPrice();

	@Test
	public void should_get_saving() {
		final Map<Product, Long> countByProduct = new HashMap<>();
		countByProduct.put(APPLE, 7l);
		countByProduct.put(BUTTER, 5l);

		final Basket basket = new Basket(countByProduct);

		pricingStrategy.apply(basket);

		final List<Saving> savings = basket.getSavings();
		assertThat(savings).hasSize(1);
		assertThat(savings).extracting("savingAmount").containsOnly(new BigDecimal("0.40"));
	}

	@Test
	public void should_not_get_saving() {
		final Map<Product, Long> countByProduct = new HashMap<>();
		countByProduct.put(APPLE, 2l);
		countByProduct.put(BUTTER, 1l);

		final Basket basket = new Basket(countByProduct);

		pricingStrategy.apply(basket);

		final List<Saving> savings = basket.getSavings();
		assertThat(savings).isEmpty();
	}
}