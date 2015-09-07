package org.vieuxchameau.supermarketCheckout.strategies;

import org.junit.Test;
import org.vieuxchameau.supermarketCheckout.Basket;
import org.vieuxchameau.supermarketCheckout.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.APPLE;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.CUSTARD;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.EGG;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.FLOUR;

public class BuyThreeCheapestFreeTest {
	private final PricingStrategy pricingStrategy = new BuyThreeCheapestFree();

	@Test
	public void should_get_saving() {
		final Map<Product, Long> countByProduct = new HashMap<>();
		countByProduct.put(APPLE, 7l);
		countByProduct.put(CUSTARD, 3l);
		countByProduct.put(EGG, 4l);
		countByProduct.put(FLOUR, 2l);

		final Basket basket = new Basket(countByProduct);

		pricingStrategy.apply(basket);

		final List<Saving> savings = basket.getSavings();
		assertThat(savings).hasSize(1);
		assertThat(savings).extracting("savingAmount").containsOnly(new BigDecimal("0.90"));
	}

	@Test
	public void should_not_get_saving() {
		final Map<Product, Long> countByProduct = new HashMap<>();
		countByProduct.put(APPLE, 2l);
		countByProduct.put(CUSTARD, 3l);
		countByProduct.put(EGG, 4l);

		final Basket basket = new Basket(countByProduct);

		pricingStrategy.apply(basket);

		final List<Saving> savings = basket.getSavings();
		assertThat(savings).isEmpty();
	}
}