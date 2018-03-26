package org.galiosten.calculator.model;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LiteralNodeTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testEvaluate() {

		final LiteralNode node = new LiteralNode(1);

		assertThat(node.evaluate(), equalTo(1));
	}

	@Test
	public void testInvaldLeftDescendent() throws Exception {

		this.expectedException.expect(UnsupportedOperationException.class);

		final LiteralNode node = new LiteralNode(0);

		node.setLeft(new LiteralNode(0));
	}

	@Test
	public void testInvaldRightDescendent() throws Exception {

		this.expectedException.expect(UnsupportedOperationException.class);

		final LiteralNode node = new LiteralNode(0);

		node.setRight(new LiteralNode(0));
	}

}
