/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.model;

/**
 * The {@code LetNode} represents a Let operator in the expression tree. The Let
 * operator is specialized to assign values to variables. Evaluating a Let
 * operation will result in variable substitution. The result of the evaluation
 * will be the result of evaluating the expression passed as the third argument
 * when specifying the Let operator. For example, in the following expression:
 * 
 * <pre>
 * let(a, 1, add(a, a))
 * </pre>
 * <ol>
 * <li>The variable {@code a} is created</li>
 * <li>The value {@code 1} is assigned</li>
 * <li>The expression <code>a + a</code> is evaluated</li>
 * <li>The value {@code 2} is returned</li>
 * </ol>
 * Variables are globally scoped once created. In the following expression <br/>
 * <br/>
 * <code>let(a, 5, let(b, mult(a, 10), add(b, a)))</code> <br/>
 * <br/>
 * the value of {@code a} is set to 5, then applied in subsequent nested
 * expressions. The value of {@code b} is set to the result of
 * <code>a * 10</code>, and finally the result of the overall expression is 55.
 * 
 * @author rbets
 *
 */
public class LetNode extends Node {

	/**
	 * Creates an instance of {@code LetNode} representing a Let operation.
	 */
	public LetNode() {
		super(Node.NodeType.LET);
	}

	@Override
	public Integer evaluate() {

		final Node leftNode = getLeft();
		if (leftNode == null || leftNode.getType() != Node.NodeType.VARIABLE) {
			throw new IllegalStateException("Illegal left descendant for LET node");
		}

		final Node rightNode = getRight();

		// the left node will always be a variable that can resolve its own value. The
		// values will subsequently be cached internally in the VariableNode class
		leftNode.evaluate();

		// the right node must be one of LET or OPERATION, although anything will
		// actually work
		return rightNode.evaluate();
	}

}
