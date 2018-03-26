/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.operation;

import java.util.Objects;

/**
 * The {@code Identity} operation is a special case operation that models single
 * {@code Integer} values in a way that can be evaluated as any other model.
 * Unlike binary or ternary operations, the {@code Identity} operation stores
 * the value directly, although it can be constructed from the results of
 * evaluating other operations.
 * 
 * @author rbets
 *
 */
public class Identity extends Operation {

	private final Integer value;

	/**
	 * Constructs an {@code Identity} operation representing the specified value.
	 * 
	 * @param value
	 *            value to represent in this {@code Identity} operation
	 */
	public Identity(final Integer value) {
		this.value = value;
	}

	/**
	 * Constructs an {@code Identity} operation representing the result of the
	 * specified operation.
	 * 
	 * @param operation
	 *            the operation to evaluate in order to generate the value for this
	 *            {@code Identity} operation
	 */
	public Identity(final Operation operation) {
		Objects.requireNonNull(operation);
		this.value = operation.evaluate();
	}

	@Override
	protected Integer compute() {
		return this.value;
	}

}
