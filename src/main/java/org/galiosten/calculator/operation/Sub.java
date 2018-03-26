/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.operation;

/**
 * The {@code Sub} operation represents the subtraction of the second operand
 * from the first.
 * 
 * @author rbets
 *
 */
public class Sub extends BinaryOperation {

	/**
	 * Constructs a {@code Sub} operation with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Sub(final Operation operandA, final Operation operandB) {
		super(operandA, operandB);
	}

	/**
	 * Constructs a {@code Sub} operation with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Sub(final Integer operandA, final Integer operandB) {
		super(new Identity(operandA), new Identity(operandB));
	}

	/**
	 * Constructs a {@code Sub} operation with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Sub(final Integer operandA, final Operation operandB) {
		super(new Identity(operandA), operandB);
	}

	/**
	 * Constructs a {@code Sub} operation with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Sub(final Operation operandA, final Integer operandB) {
		super(operandA, new Identity(operandB));
	}

	@Override
	protected Integer compute() {
		return getEvaluationStack()[0].compute() - getEvaluationStack()[1].compute();
	}

}
