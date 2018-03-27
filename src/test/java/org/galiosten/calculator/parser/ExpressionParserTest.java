package org.galiosten.calculator.parser;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.galiosten.calculator.model.Node;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExpressionParserTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testParse() {

		assertThat(ExpressionParser.parse("10").getType(), equalTo(Node.NodeType.LITERAL));
		assertThat(ExpressionParser.parse("add(1,1)").getType(), equalTo(Node.NodeType.OPERATION));
		assertThat(ExpressionParser.parse("let(a, 10, add(1, a))").getType(), equalTo(Node.NodeType.LET));
	}

	@Test
	public void testParseInvalidOperation() {

		this.expectedException.expect(IllegalArgumentException.class);

		ExpressionParser.parse("foo(1,1)");

		fail("Should not have parsed unsupported operation 'foo'");
	}

	@Test
	public void testParseInvalidSyntax() {

		this.expectedException.expect(IllegalArgumentException.class);

		ExpressionParser.parse("add(1,1");

		fail("Should not have parsed invalid operation");
	}

	@Test
	public void testGetParameters() {

		assertThat(ExpressionParser.getParameters(1, "10").size(), equalTo(1));
		assertThat(ExpressionParser.getParameters(2, "2,2").size(), equalTo(2));
		assertThat(ExpressionParser.getParameters(3, "a,10,add(1,1)").size(), equalTo(3));
	}

	@Test
	public void testGetParametersInvalidCount() {

		this.expectedException.expect(IllegalArgumentException.class);

		ExpressionParser.getParameters(2, "10");

		fail("Should not have parsed invalid number of parameters");
	}

}
