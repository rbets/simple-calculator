package org.galiosten.calculator.operation;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AddTest {

	@Test
	public void testEvaluate() {

		final Operation add1 = new Add(new Identity(1), new Identity(1));
		final Operation add2 = new Add(1, 1);
		final Operation add3 = new Add(1, new Identity(1));
		final Operation add4 = new Add(new Identity(1), 1);

		assertThat(add1.evaluate(), equalTo(2));
		assertThat(add2.evaluate(), equalTo(2));
		assertThat(add3.evaluate(), equalTo(2));
		assertThat(add4.evaluate(), equalTo(2));
	}

	@Test
	public void testEvaluateNestedOperations() {

		final Operation addOperand = new Add(1, 1);

		final Operation add = new Add(1, addOperand);

		assertThat(add.evaluate(), equalTo(3));

		final Operation add2 = new Add(add, add);
		assertThat(add2.evaluate(), equalTo(2 * add.evaluate()));

		// ensure that the original expression still evaluates correctly
		assertThat(add.evaluate(), equalTo(3));
	}

}
