/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.operation;

/**
 * The {@code Mult} operation represents the multiplication of its operands.
 * 
 * @author rbets
 *
 */
public class Mult extends BinaryOperation {

	/**
	 * Constructs a {@code Mult} operation with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Mult(final Operation operandA, final Operation operandB) {
		super(operandA, operandB);
	}

	/**
	 * Constructs a {@code Mult} operation with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Mult(final Integer operandA, final Integer operandB) {
		super(new Identity(operandA), new Identity(operandB));
	}

	/**
	 * Constructs a {@code Mult} operation with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Mult(final Integer operandA, final Operation operandB) {
		super(new Identity(operandA), operandB);
	}

	/**
	 * Constructs a {@code Mult} operation with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public Mult(final Operation operandA, final Integer operandB) {
		super(operandA, new Identity(operandB));
	}

	@Override
	protected Integer compute() {
		return getEvaluationStack()[0].compute() * getEvaluationStack()[1].compute();
	}

}
