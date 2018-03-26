/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.operation;

/**
 * Abstract base class for all operations. An operation is a container for one
 * or more operations. When the operation is evaluated, its result is theThere
 * are three types of operations:
 * <ul>
 * <li>uniary - the Identity operation which returns itself</li>
 * <li>binary - an operation that takes two Operation parameters</li>
 * </li>ternary - an operation that takes three Operation parameters</li>
 * </ul>
 * 
 * @author rbets
 *
 */
public abstract class Operation {

	/**
	 * Returns the result of evaluation of this {@code Operation}.
	 * 
	 * @return the result of evaluation of this {@code Operation}
	 */
	public Integer evaluate() {
		return compute();
	}

	/**
	 * Computes the result of this operation and return the result as an integer
	 * value.
	 * 
	 * @return the result of evaluating this operation
	 */
	protected abstract Integer compute();
}
