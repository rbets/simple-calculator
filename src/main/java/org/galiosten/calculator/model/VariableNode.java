/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code VariableNode} represents a variable that will have its value set
 * during the evaluation of a Let expression. Since variables are globally
 * scoped, values for variables are cached once set, but can be overridden in
 * subsequent expressions.
 * 
 * @author rbets
 *
 */
public class VariableNode extends Node {

	/**
	 * Cache of variable values mapped to their respective identifiers.
	 */
	public static final Map<String, Integer> VARIABLE_CACHE = new HashMap<>();

	private final String identifier;

	public VariableNode(final String identifier) {
		super(Node.NodeType.VARIABLE);

		this.identifier = identifier;
	}

	@Override
	public void setRight(final Node right) {
		throw new UnsupportedOperationException("Variables may not have right descendants");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Returns the result of the expression associated with the variable, or the
	 * cached value if present. If there is an expression associated with this
	 * variable, the result of the expression evaluation is cached, overriding any
	 * previously cached value for this variable.
	 * 
	 * @throws IllegalArgumentException
	 *             if this {@code VariableNode} is associated with more than one
	 *             expression.
	 */
	@Override
	public Integer evaluate() {

		final Node leftNode = getLeft();

		final Integer value;
		if (leftNode != null) {
			value = leftNode.evaluate();
			VARIABLE_CACHE.put(this.identifier, value);
		} else if (VARIABLE_CACHE.containsKey(this.identifier)) {
			value = VARIABLE_CACHE.get(this.identifier);
		} else {
			throw new IllegalStateException("Unable to resolve value for variable " + this.identifier);
		}

		return value;
	}

	@Override
	public String toString() {

		return (VARIABLE_CACHE.containsKey(this.identifier))
				? this.identifier + " [" + VARIABLE_CACHE.get(this.identifier) + "]"
				: this.identifier;
	}
}
