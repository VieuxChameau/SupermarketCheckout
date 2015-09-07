package org.vieuxchameau.supermarketCheckout.strategies;

import org.vieuxchameau.supermarketCheckout.Basket;

public interface PricingStrategy {
	void apply(final Basket basket);
}
