package org.vieuxchameau.supermarketCheckout;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
	private final String reference;
	private final String displayName;
	private final BigDecimal unitPrice;

	public Product(final String reference, final String displayName, final BigDecimal unitPrice) {
		this.reference = reference;
		this.displayName = displayName;
		this.unitPrice = unitPrice;
	}

	public String getReference() {
		return reference;
	}

	public String getDisplayName() {
		return displayName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Product product = (Product) o;
		return Objects.equals(reference, product.reference) &&
				Objects.equals(displayName, product.displayName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference, displayName);
	}
}
