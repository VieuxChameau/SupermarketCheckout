package org.vieuxchameau.supermarketCheckout;

import org.vieuxchameau.supermarketCheckout.strategies.BuyThreeCheapestFree;
import org.vieuxchameau.supermarketCheckout.strategies.BuyThreeForTwo;
import org.vieuxchameau.supermarketCheckout.strategies.BuyTwoForSpecialPrice;
import org.vieuxchameau.supermarketCheckout.strategies.ForEachItemsGetAnotherFree;
import org.vieuxchameau.supermarketCheckout.strategies.PricingStrategy;
import org.vieuxchameau.supermarketCheckout.strategies.Saving;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SuperMarketCheckout {
	private final List<PricingStrategy> pricingStrategies = new ArrayList<>();
	private Basket basket = null;

	public SuperMarketCheckout() {
		pricingStrategies.add(new BuyThreeCheapestFree());
		pricingStrategies.add(new BuyThreeForTwo());
		pricingStrategies.add(new BuyTwoForSpecialPrice());
		pricingStrategies.add(new ForEachItemsGetAnotherFree());
	}

	public void scan(final List<String> items) {
		basket = BasketCreator.createFrom(items);

		pricingStrategies.stream().forEach(strategy -> strategy.apply(basket));
	}

	public void printReceipt() {
		if (basket == null) {
			throw new IllegalStateException("No basket");
		}

		final BigDecimal total = printProducts();
		final BigDecimal totalSavings = printSavings();
		printTotalToPay(total, totalSavings);
	}

	private void printTotalToPay(final BigDecimal total, final BigDecimal totalSavings) {
		System.out.println("\nTotal to pay £" + (total.subtract(totalSavings)));
	}

	private BigDecimal printSavings() {
		final List<Saving> savings = basket.getSavings();
		BigDecimal totalSavings = BigDecimal.ZERO;
		if (savings.isEmpty()) {
			return totalSavings;
		}

		System.out.println("\nSavings :");
		for (final Saving saving : savings) {
			final BigDecimal savingAmount = saving.getSavingAmount();
			System.out.println(String.format("%s -%s", saving.getReason(), savingAmount));
			totalSavings = totalSavings.add(savingAmount);
		}
		return totalSavings;
	}

	private BigDecimal printProducts() {
		BigDecimal total = BigDecimal.ZERO;
		for (final Map.Entry<Product, Long> countByProduct : basket.getCountByProduct().entrySet()) {
			final long quantity = countByProduct.getValue();
			final Product product = countByProduct.getKey();
			if (quantity > 1) {
				final BigDecimal unitPrice = product.getUnitPrice();
				final BigDecimal totalForProduct = unitPrice.multiply(BigDecimal.valueOf(quantity));
				System.out.println(String.format("%d %s @ £ %s --> %s", quantity, product.getDisplayName(), unitPrice, totalForProduct));
				total = total.add(totalForProduct);
			} else {
				System.out.println(String.format("%s £%s", product.getDisplayName(), product.getUnitPrice()));
				total = total.add(product.getUnitPrice());
			}
		}
		System.out.println("Sub total = £" + total);
		return total;
	}
}
