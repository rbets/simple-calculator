/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.operation;

/**
 * The {@code Div} operation represents the expression
 * <code>numerator / denominator</code> where the first operand is the numerator
 * and the second operand is the denominator.
 * 
 * @author rbets
 *
 */
public class Div extends BinaryOperation {

	/**
	 * Constructs a {@code Div} operation with the specified operands.
	 * 
	 * @param operandA
	 *            numerator
	 * @param operandB
	 *            denominator
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Div(final Operation operandA, final Operation operandB) {
		super(operandA, operandB);
	}

	/**
	 * Constructs a {@code Div} operation with the specified operands.
	 * 
	 * @param operandA
	 *            numerator
	 * @param operandB
	 *            denominator
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Div(final Integer operandA, final Integer operandB) {
		super(new Identity(operandA), new Identity(operandB));
	}

	/**
	 * Constructs a {@code Div} operation with the specified operands.
	 * 
	 * @param operandA
	 *            numerator
	 * @param operandB
	 *            denominator
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Div(final Integer operandA, final Operation operandB) {
		super(new Identity(operandA), operandB);
	}

	/**
	 * Constructs a {@code Div} operation with the specified operands.
	 * 
	 * @param operandA
	 *            numerator
	 * @param operandB
	 *            denominator
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Div(final Operation operandA, final Integer operandB) {
		super(operandA, new Identity(operandB));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ArithmeticException
	 *             if the second operand for this operation evaluates to 0, i.e. the
	 *             operation attempts to divide by zero
	 */
	@Override
	protected Integer compute() {
		return getEvaluationStack()[0].compute() / getEvaluationStack()[1].compute();
	}

}
