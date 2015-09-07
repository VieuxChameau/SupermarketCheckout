package org.vieuxchameau.supermarketCheckout.strategies;

import java.math.BigDecimal;

public class Saving {
	private final String reason;
	private final BigDecimal savingAmount;

	public Saving(final String reason, final BigDecimal savingAmount) {
		this.reason = reason;
		this.savingAmount = savingAmount;
	}

	public String getReason() {
		return reason;
	}

	public BigDecimal getSavingAmount() {
		return savingAmount;
	}
}
