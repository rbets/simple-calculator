package org.galiosten.calculator.operation;

import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BinaryOperatorTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testInvalidConstructorFirstParamNull() {

		expectedException.expect(NullPointerException.class);
		new BinaryOperation((Operation) null, 1) {

			@Override
			protected Integer compute() {
				// TODO Auto-generated method stub
				return 0;
			}

		};

		fail("Should not have initialized an invalid operation");
	}

	@Test
	public void testInvalidConstructorSecondParamNull() {

		expectedException.expect(NullPointerException.class);
		new BinaryOperation(1, (Operation) null) {

			@Override
			protected Integer compute() {
				// TODO Auto-generated method stub
				return 0;
			}

		};

		fail("Should not have initialized an invalid operation");
	}

}
