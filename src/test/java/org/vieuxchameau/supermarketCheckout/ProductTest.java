package org.vieuxchameau.supermarketCheckout;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.APPLE;
import static org.vieuxchameau.supermarketCheckout.InventoryProducts.BUTTER;

public class ProductTest {
	@Test
	public void should_be_reflexive() {
		final boolean isEqual = APPLE.equals(APPLE);

		assertThat(isEqual).isTrue();
	}

	@Test
	public void should_be_null_safe() {
		final boolean isEqual = APPLE.equals(null);

		assertThat(isEqual).isFalse();
	}

	@Test
	public void should_be_symmetric() {
		final Product leftApple = new Product("APPLE", "Granny Smiths", new BigDecimal("1.50"));
		final Product rightApple = new Product("APPLE", "Granny Smiths", new BigDecimal("0.42"));

		final boolean isEqualLhs = leftApple.equals(rightApple);
		final boolean isEqualRhs = rightApple.equals(leftApple);

		assertThat(isEqualLhs).isTrue();
		assertThat(isEqualRhs).isTrue();
	}

	@Test
	public void should_not_be_equals_and_have_different_hashcode() {
		final boolean isEqualLhs = APPLE.equals(BUTTER);

		assertThat(isEqualLhs).isFalse();
		assertThat(APPLE.hashCode()).isNotEqualTo(BUTTER.hashCode());
	}

	@Test
	public void should_have_the_same_hashcode() {
		final Product leftApple = new Product("APPLE", "Granny Smiths", new BigDecimal("1.50"));
		final Product rightApple = new Product("APPLE", "Granny Smiths", new BigDecimal("0.42"));

		final boolean isEquals = leftApple.equals(rightApple);

		assertThat(isEquals).isTrue();

		assertThat(leftApple.hashCode()).isEqualTo(rightApple.hashCode());
	}

	@Test
	public void should_be_transitive() {
		final Product productA = new Product("APPLE", "Granny Smiths", new BigDecimal("1.50"));
		final Product productB = new Product("APPLE", "Granny Smiths", new BigDecimal("0.42"));
		final Product productC = new Product("APPLE", "Granny Smiths", new BigDecimal("0.30"));

		final boolean firstEquals = productA.equals(productB);
		final boolean secondEquals = productB.equals(productC);
		final boolean thirdEquals = productC.equals(productA);

		assertThat(firstEquals).isTrue();
		assertThat(secondEquals).isTrue();
		assertThat(thirdEquals).isTrue();
	}
}