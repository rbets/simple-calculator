/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.model;

/**
 * Base class for expression tree node types.
 * 
 * @author rbets
 *
 */
public abstract class Node {

	/**
	 * Support node types in the expression tree. The expression tree is a binary
	 * tree constructed while parsing the input expression. Each node may have 0, 1,
	 * or 2 child nodes. Nodes are always evaluated left to right.
	 * 
	 * @author rbets
	 *
	 */
	public enum NodeType {
		LET, OPERATION, LITERAL, VARIABLE
	}

	private final NodeType type;

	private Node left;
	private Node right;

	/**
	 * Constructs an instance of {@code Node} for the specified {@link NodeType}.
	 * 
	 * @param type
	 */
	public Node(final NodeType type) {
		this.type = type;
	}

	/**
	 * Returns the left child {@code Node} of this {@code Node}, or {@code null} if
	 * there is no left child.
	 * 
	 * @return the left child {@code Node} of this {@code Node}
	 */
	public Node getLeft() {
		return this.left;
	}

	/**
	 * Sets the left child {@code Node} for this {@code Node}. This node may not
	 * reference itself.
	 * 
	 * @param left
	 *            {@code Node} to set as the left child.
	 * @throws IllegalArgumentException
	 *             if the node refers to itself
	 */
	public void setLeft(final Node left) {

		if (left == this) {
			throw new IllegalArgumentException("Nodes may not refer to themselves");
		}

		this.left = left;
	}

	/**
	 * Returns the right child {@code Node} of this {@code Node}, or {@code null} if
	 * there is no right child.
	 * 
	 * @return the right child {@code Node} of this {@code Node}
	 */
	public Node getRight() {
		return this.right;
	}

	/**
	 * Sets the right child {@code Node} for this {@code Node}. This node may not
	 * reference itself.
	 * 
	 * @param left
	 *            {@code Node} to set as the right child.
	 * @throws IllegalArgumentException
	 *             if the node refers to itself
	 */
	public void setRight(final Node right) {

		if (right == this) {
			throw new IllegalArgumentException("Nodes may not refer to themselves");
		}

		this.right = right;
	}

	/**
	 * Returns the {@link NodeType} represented by this {@code Node}.
	 * 
	 * @return the {@code NodeType} represented by this {@code Node}
	 */
	public NodeType getType() {
		return this.type;
	}

	/**
	 * Returns a string representation of this {@code Node} and all of its
	 * descendants. Levels in the output string are separated by new lines and
	 * indented with a number of '+' characters corresponding to this node's depth
	 * relative to its parent. This method is primarily intended for debugging
	 * purposes.
	 * 
	 * @return a string representation of this {@code Node} and all of its
	 *         descendants
	 */
	public String getPrettyPrint() {
		return getPrettyPrint(1);
	}

	/*
	 * (non-javadoc)
	 * 
	 * Constructs the pretty-printed string representation of this node by
	 * recursively getting the pretty-print representations of each of its
	 * descendants.
	 */
	private String getPrettyPrint(final int level) {
		final StringBuilder sb = new StringBuilder();

		for (int i = 0; i < level; i++) {
			sb.append('+');
		}

		sb.append(' ').append(this.toString());

		if (this.left != null) {
			sb.append(System.lineSeparator()).append(this.left.getPrettyPrint(level + 1));
		}

		if (this.right != null) {
			sb.append(System.lineSeparator()).append(right.getPrettyPrint(level + 1));
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return this.type.name();
	}

	/**
	 * Returns the result of evaluating this {@code Node}.
	 * 
	 * @return the result of evaluating this {@code Node}
	 */
	public abstract Integer evaluate();
}
