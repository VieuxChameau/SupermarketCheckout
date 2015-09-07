package org.vieuxchameau.supermarketCheckout;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.APPLE;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.BUTTER;

public class BasketCreatorTest {
	@Test
	public void should_create_the_basket_by_grouping_product() {
		final List<String> items = new ArrayList<>();
		items.add(APPLE.getReference());
		items.add(APPLE.getReference());
		items.add(BUTTER.getReference());

		final Basket basket = BasketCreator.createFrom(items);

		assertThat(basket).isNotNull();
		assertThat(basket.countForProduct(APPLE)).isEqualTo(2);
		assertThat(basket.countForProduct(BUTTER)).isEqualTo(1);
	}
}