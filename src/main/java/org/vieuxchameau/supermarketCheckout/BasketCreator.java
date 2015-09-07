package org.vieuxchameau.supermarketCheckout;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BasketCreator {
	public static Basket createFrom(final List<String> items) {
		final Map<Product, Long> countByProduct = items.stream().map(Inventory::findProductByReference).collect(Collectors.groupingBy(p -> p, Collectors.counting()));
		return new Basket(countByProduct);
	}
}
