/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.operation;

import java.util.Objects;

/**
 * Base class for binary operations. The evaluated result will be based on two
 * operands.
 * 
 * @author rbets
 *
 */
public abstract class BinaryOperation extends Operation {

	private final Operation[] evaluationStack = new Operation[2];

	/**
	 * Constructs a {@code BinaryOperation} with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public BinaryOperation(final Operation operandA, final Operation operandB) {

		Objects.requireNonNull(operandA);
		Objects.requireNonNull(operandB);

		this.evaluationStack[0] = operandA;
		this.evaluationStack[1] = operandB;

	}

	/**
	 * Constructs a {@code BinaryOperation} with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public BinaryOperation(final Integer operandA, final Integer operandB) {
		this(new Identity(operandA), new Identity(operandB));
	}

	/**
	 * Constructs a {@code BinaryOperation} with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public BinaryOperation(final Integer operandA, final Operation operandB) {
		this(new Identity(operandA), operandB);
	}

	/**
	 * Constructs a {@code BinaryOperation} with the specified operands.
	 * 
	 * @param operandA
	 *            left operand
	 * @param operandB
	 *            right operand
	 * @throws NullPointerException
	 *             if either operand is not provided
	 */
	public BinaryOperation(final Operation operandA, final Integer operandB) {
		this(operandA, new Identity(operandB));
	}

	/**
	 * Returns an array containing the operands for this {@code BinaryOperation}.
	 * 
	 * @return an array containing the operands for this {@code BinaryOperation}
	 */
	protected Operation[] getEvaluationStack() {
		return this.evaluationStack;
	}
}
