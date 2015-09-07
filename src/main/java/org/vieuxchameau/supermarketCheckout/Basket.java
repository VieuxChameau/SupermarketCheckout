package org.vieuxchameau.supermarketCheckout;

import org.vieuxchameau.supermarketCheckout.strategies.Saving;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Basket {
	private final Map<Product, Long> countByProduct;
	private final List<Saving> savings = new LinkedList<>();

	public Basket(final Map<Product, Long> countByProduct) {
		this.countByProduct = countByProduct;
	}

	public boolean containsProduct(final Product product) {
		return countByProduct.containsKey(product);
	}

	public long countForProduct(final Product product) {
		return countByProduct.get(product);
	}

	public void addSaving(final Saving saving) {
		savings.add(saving);
	}

	public List<Saving> getSavings() {
		return savings;
	}

	public Map<Product, Long> getCountByProduct() {
		return countByProduct;
	}
}
