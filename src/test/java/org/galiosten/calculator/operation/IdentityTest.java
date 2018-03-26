package org.galiosten.calculator.operation;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IdentityTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testEvaluate() {

		final Operation identity = new Identity(1);

		assertThat(identity.evaluate(), equalTo(1));
	}

	@Test
	public void testEvaluateNestedOperation() {

		final Operation nestedIdentity = new Identity(1);
		final Operation identity = new Identity(nestedIdentity);

		assertThat(identity.evaluate(), equalTo(1));
	}

	@Test
	public void testIdentityInvalidValue() {

		this.expectedException.expect(NullPointerException.class);

		new Identity((Operation) null);
	}

}
