package org.galiosten.calculator.operation;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MultTest {

	@Test
	public void testEvaluate() {

		final Operation mult1 = new Mult(new Identity(2), new Identity(3));
		final Operation mult2 = new Mult(2, 3);
		final Operation mult3 = new Mult(2, new Identity(3));
		final Operation mult4 = new Mult(new Identity(2), 3);

		assertThat(mult1.evaluate(), equalTo(6));
		assertThat(mult2.evaluate(), equalTo(6));
		assertThat(mult3.evaluate(), equalTo(6));
		assertThat(mult4.evaluate(), equalTo(6));
	}

	@Test
	public void testEvaluateNestedOperations() {

		final Operation multOperand = new Mult(2, 3);

		final Operation mult = new Mult(2, multOperand);

		assertThat(mult.evaluate(), equalTo(12));

		final Operation mult2 = new Mult(mult, mult);
		assertThat(mult2.evaluate(), equalTo(144));

		// ensure that the original expression still evaluates correctly
		assertThat(mult.evaluate(), equalTo(12));
	}

}
