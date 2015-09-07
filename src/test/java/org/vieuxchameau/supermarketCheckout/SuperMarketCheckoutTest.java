package org.vieuxchameau.supermarketCheckout;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.vieuxchameau.supermarketCheckout.InventoryProducts.APPLE;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.BUTTER;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.CUSTARD;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.EGG;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.FLOUR;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.ICE_CREAM;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.SUGAR;

public class SuperMarketCheckoutTest {

	private final SuperMarketCheckout superMarketCheckout = new SuperMarketCheckout();

	@Test
	public void should_scan_items_and_print_receipt() {
		final List<String> items = new ArrayList<>();
		addNItems(items, 7, APPLE);

		addNItems(items, 5, BUTTER);

		addNItems(items, 3, CUSTARD);
		addNItems(items, 4, EGG);
		addNItems(items, 2, FLOUR);

		addNItems(items, 3, ICE_CREAM);
		addNItems(items, 6, SUGAR);

		superMarketCheckout.scan(items);

		superMarketCheckout.printReceipt();
	}

	@Test(expected = IllegalStateException.class)
	public void should_reject_print_without_basket() {
		superMarketCheckout.printReceipt();
	}

	private void addNItems(final List<String> items, final int nTimes, final Product product) {
		for (int i = 0; i < nTimes; i++) {
			items.add(product.getReference());
		}
	}
}