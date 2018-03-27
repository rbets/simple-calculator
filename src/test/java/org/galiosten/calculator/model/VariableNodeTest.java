package org.galiosten.calculator.model;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class VariableNodeTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testEvaluate() {

		final Node node = new VariableNode("b");

		node.setLeft(new LiteralNode(1));

		assertThat(node.evaluate(), equalTo(1));

		final Node node2 = new VariableNode("b");
		assertThat(node2.evaluate(), equalTo(1));
	}

	@Test
	public void testEvaluateNoValue() {

		this.expectedException.expect(IllegalStateException.class);

		final Node node = new VariableNode("c");

		node.evaluate();

		fail("Should not have been able to evaluate a variable with no value");
	}

	@Test
	public void testSetInvalidRightNode() {

		this.expectedException.expect(UnsupportedOperationException.class);

		final Node node = new VariableNode("d");

		node.setRight(new LiteralNode(1));

		fail("Should not have been able to set a right node for a variable");
	}

}
