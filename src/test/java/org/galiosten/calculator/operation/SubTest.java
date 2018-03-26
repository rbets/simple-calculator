package org.galiosten.calculator.operation;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SubTest {

	@Test
	public void testEvaluate() {

		final Operation sub1 = new Sub(new Identity(1), new Identity(1));
		final Operation sub2 = new Sub(1, 1);
		final Operation sub3 = new Sub(1, new Identity(1));
		final Operation sub4 = new Sub(new Identity(1), 1);

		assertThat(sub1.evaluate(), equalTo(0));
		assertThat(sub2.evaluate(), equalTo(0));
		assertThat(sub3.evaluate(), equalTo(0));
		assertThat(sub4.evaluate(), equalTo(0));
	}

	@Test
	public void testEvaluateNestedOperations() {

		final Operation subOperand = new Sub(1, 1);

		final Operation sub = new Sub(1, subOperand);

		assertThat(sub.evaluate(), equalTo(1));

		final Operation sub2 = new Sub(sub, sub);
		assertThat(sub2.evaluate(), equalTo(0));

		// ensure that the original expression still evaluates correctly
		assertThat(sub.evaluate(), equalTo(1));
	}

}
