package org.vieuxchameau.supermarketCheckout;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class BasketTest {

	@Test
	public void should_not_contains_product() {
		final Map<Product, Long> countByProduct = new HashMap<>();
		countByProduct.put(InventoryProducts.CUSTARD, 2l);

		final Basket basket = new Basket(countByProduct);

		assertThat(basket.containsProduct(InventoryProducts.APPLE)).isFalse();
	}

	@Test
	public void should_contains_product_and_get_the_count() {
		final Map<Product, Long> countByProduct = new HashMap<>();
		countByProduct.put(InventoryProducts.CUSTARD, 2l);

		final Basket basket = new Basket(countByProduct);

		assertThat(basket.containsProduct(InventoryProducts.CUSTARD)).isTrue();
		assertThat(basket.countForProduct(InventoryProducts.CUSTARD)).isEqualTo(2);
	}
}