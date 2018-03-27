package org.galiosten.calculator.model;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.galiosten.calculator.operation.Add;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LetNodeTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testInstantiation() {

		final Node node = new LetNode();

		assertThat(node.getType(), equalTo(Node.NodeType.LET));
	}

	@Test
	public void testToString() {

		final Node node = new LetNode();

		assertThat(node.toString(), equalTo("LET"));
	}

	@Test
	public void testEvaluate() {

		final Node variable = new VariableNode("a");
		variable.setLeft(new LiteralNode(1));

		final Node node = new LetNode();
		node.setLeft(variable);

		final Node add = new OperationNode(Add.class);
		add.setLeft(variable);
		add.setRight(variable);

		node.setRight(add);

		assertThat(node.evaluate(), equalTo(2));
	}

	@Test
	public void testEvaluateInvalidLeftChild() {

		this.expectedException.expect(IllegalArgumentException.class);

		final Node node = new LetNode();

		node.setLeft(new LiteralNode(1));

		fail("Should not have been able to add a non-variable left node to the Let operation");
	}

}
