package org.galiosten.calculator.model;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.galiosten.calculator.operation.Add;
import org.galiosten.calculator.operation.Div;
import org.galiosten.calculator.operation.Identity;
import org.galiosten.calculator.operation.Mult;
import org.galiosten.calculator.operation.Sub;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OperationNodeTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testSetInvalidNullLeft() {

		this.expectedException.expect(NullPointerException.class);

		final OperationNode node = new OperationNode(Identity.class);
		node.setLeft(null);
	}

	@Test
	public void testSetInvalidNullRight() {

		this.expectedException.expect(NullPointerException.class);

		final OperationNode node = new OperationNode(Identity.class);
		node.setRight(null);
	}

	@Test
	public void testSetInvalidSelfLeft() {

		this.expectedException.expect(IllegalArgumentException.class);

		final OperationNode node = new OperationNode(Identity.class);
		node.setLeft(node);
	}

	@Test
	public void testSetInvalidSelfRight() {

		this.expectedException.expect(IllegalArgumentException.class);

		final OperationNode node = new OperationNode(Identity.class);
		node.setRight(node);
	}

	@Test
	public void testEvaluate() {

		final OperationNode addNode = new OperationNode(Add.class);
		addNode.setLeft(new LiteralNode(1));
		addNode.setRight(new LiteralNode(1));

		assertThat(addNode.evaluate(), equalTo(2));

		final OperationNode subNode = new OperationNode(Sub.class);
		subNode.setLeft(new LiteralNode(2));
		subNode.setRight(new LiteralNode(1));

		assertThat(subNode.evaluate(), equalTo(1));

		final OperationNode multNode = new OperationNode(Mult.class);
		multNode.setLeft(new LiteralNode(2));
		multNode.setRight(new LiteralNode(3));

		assertThat(multNode.evaluate(), equalTo(6));

		final OperationNode divNode = new OperationNode(Div.class);
		divNode.setLeft(new LiteralNode(6));
		divNode.setRight(new LiteralNode(3));

		assertThat(divNode.evaluate(), equalTo(2));

	}
	
	@Test
	public void testToString() {
		
		final OperationNode addNode = new OperationNode(Add.class);
		
		assertThat(addNode.toString(), equalTo("Add"));
	}

	@Test
	public void testEvaluateInvalidLeft() {

		this.expectedException.expect(NullPointerException.class);

		final OperationNode addNode = new OperationNode(Add.class);
		addNode.setRight(new LiteralNode(1));

		addNode.evaluate();
	}

	@Test
	public void testEvaluateInvalidRight() {

		this.expectedException.expect(NullPointerException.class);

		final OperationNode addNode = new OperationNode(Add.class);
		addNode.setLeft(new LiteralNode(1));

		addNode.evaluate();
	}

}
