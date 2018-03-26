package org.galiosten.calculator.operation;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DivTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testEvaluate() {

		final Operation div1 = new Div(new Identity(4), new Identity(2));
		final Operation div2 = new Div(4, 2);
		final Operation div3 = new Div(4, new Identity(2));
		final Operation div4 = new Div(new Identity(4), 2);

		assertThat(div1.evaluate(), equalTo(2));
		assertThat(div2.evaluate(), equalTo(2));
		assertThat(div3.evaluate(), equalTo(2));
		assertThat(div4.evaluate(), equalTo(2));
	}

	@Test
	public void testEvaluateNestedOperations() {

		final Operation divOperand = new Div(4, 2);

		final Operation div = new Div(4, divOperand);

		assertThat(div.evaluate(), equalTo(2));

		final Operation div2 = new Div(div, div);
		assertThat(div2.evaluate(), equalTo(1));

		// ensure that the original expression still evaluates correctly
		assertThat(div.evaluate(), equalTo(2));
	}

	@Test
	public void testDivideByZero() {

		expectedException.expect(ArithmeticException.class);

		new Div(1, 0).evaluate();
	}

}
