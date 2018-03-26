/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import org.galiosten.calculator.operation.Add;
import org.galiosten.calculator.operation.Div;
import org.galiosten.calculator.operation.Mult;
import org.galiosten.calculator.operation.Operation;
import org.galiosten.calculator.operation.Sub;

/**
 * The {@code OperationNode} represents an operation in the expression tree.
 * Supported operations include
 * <ul>
 * <li>ADD</li>
 * <li>SUB</li>
 * <li>MULT</li>
 * <li>DIV</li>
 * </ul>
 * The <{@code Identity} operation is a special case, represented by the
 * {@link LiteralNode} class.
 * 
 * @author rbets
 *
 */
public class OperationNode extends Node {

	final Class<? extends Operation> operationClass;

	/**
	 * Constructs an {@code OperationNode} representing the specified operation
	 * type.
	 * 
	 * @param operationClass
	 *            class type for the operation represented by this node
	 * @see Add
	 * @see Sub
	 * @see Mult
	 * @see Div
	 * @see Identity
	 */
	public OperationNode(final Class<? extends Operation> operationClass) {
		super(Node.NodeType.OPERATION);

		this.operationClass = operationClass;
	}

	@Override
	public void setLeft(final Node left) {

		Objects.requireNonNull(left);

		super.setLeft(left);
	}

	@Override
	public void setRight(final Node right) {

		Objects.requireNonNull(right);

		super.setRight(right);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Operation nodes are evaluated by evaluating their child nodes left to right.
	 * 
	 * @throws IllegalStateException
	 *             if the operation represented by this node could not be
	 *             instantiated
	 */
	@Override
	public Integer evaluate() {

		Objects.requireNonNull(getLeft());
		Objects.requireNonNull(getRight());

		final Integer operand1 = getLeft().evaluate();
		final Integer operand2 = getRight().evaluate();

		try {
			final Operation operation = this.operationClass.getConstructor(Integer.class, Integer.class)
					.newInstance(operand1, operand2);

			return operation.evaluate();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new IllegalStateException("Unable to instantiate operation of type " + this.operationClass.getName(),
					e);
		}
	}

	@Override
	public String toString() {
		return this.operationClass.getSimpleName();
	}

}
