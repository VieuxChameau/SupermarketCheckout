package org.vieuxchameau.supermarketCheckout;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class InventoryTest {

	@Test(expected = IllegalArgumentException.class)
	public void should_reject_unknown_product() {
		Inventory.findProductByReference("UNKNOWN_REFERENCE");
	}

	@Test
	public void should_return_product_when_reference_is_known() {
		final Product product = Inventory.findProductByReference("APPLE");

		assertThat(product).isNotNull();
		assertThat(product.getReference()).isEqualTo("APPLE");
	}
}