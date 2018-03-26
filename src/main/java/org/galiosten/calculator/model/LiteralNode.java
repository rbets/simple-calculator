/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.model;

import org.galiosten.calculator.operation.Identity;

/**
 * The {@code LiteralNode} represents a literal integer value. A literal node
 * always evaluates to itself. In the structure of the expression tree, literals
 * must always be leaf nodes since they can only evaluate to themselves.
 * 
 * @author rbets
 *
 */
public class LiteralNode extends Node {

	private final Integer value;

	/**
	 * Constructs an instance of {@code LiteralNode} representing the specified
	 * value.
	 * 
	 * @param value
	 *            integer value to represent in this {@code LiteralNode}
	 */
	public LiteralNode(final Integer value) {
		super(Node.NodeType.LITERAL);

		this.value = value;
	}

	@Override
	public void setLeft(final Node left) {
		throw new UnsupportedOperationException("Literal nodes may not have descendant nodes");
	}

	@Override
	public void setRight(final Node right) {
		throw new UnsupportedOperationException("Literal nodes may not have descendent nodes");
	}

	@Override
	public Integer evaluate() {

		return new Identity(this.value).evaluate();
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

}
