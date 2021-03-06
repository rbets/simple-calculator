/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.galiosten.calculator.model.LetNode;
import org.galiosten.calculator.model.LiteralNode;
import org.galiosten.calculator.model.Node;
import org.galiosten.calculator.model.OperationNode;
import org.galiosten.calculator.model.VariableNode;
import org.galiosten.calculator.operation.Add;
import org.galiosten.calculator.operation.BinaryOperation;
import org.galiosten.calculator.operation.Div;
import org.galiosten.calculator.operation.Mult;
import org.galiosten.calculator.operation.Sub;

/**
 * The {@code ExpressionParser} is responsible for parsing the input provided by
 * the user and generating a structure that can then be used to evaluate the
 * expression.
 * 
 * @author rbets
 *
 */
public class ExpressionParser {

	private static final Logger LOG = LogManager.getLogger(ExpressionParser.class);

	// this pattern just lets us know we're dealing with an operation
	private static final Pattern OPERATION_MATCHER_PATTERN = Pattern.compile("^[a-zA-Z]+\\(");

	private static final Pattern TOKEN_EXTRACTOR_PATTERN = Pattern.compile("(^[a-zA-Z]+)\\((.*)\\)$");

	private static final String LITERAL_PREFIX = "^(\\d+)";
	private static final String OPERATION_PREFIX = "(^[a-zA-Z]+\\(.*\\))";
	private static final String VARIABLE_PREFIX = "(^[a-zA-Z]+)";

	/*
	 * (non-javadoc)
	 * 
	 * Utility enum, translating parsed operation tokens into their respective class
	 * types.
	 */
	private enum Token {
		ADD(Add.class), SUB(Sub.class), DIV(Div.class), MULT(Mult.class), LET(null);

		private final Class<? extends BinaryOperation> operationClass;

		private Token(final Class<? extends BinaryOperation> operationClass) {
			this.operationClass = operationClass;
		}

		public Class<? extends BinaryOperation> getOperationClass() {
			return this.operationClass;
		}

		public static Token fromString(final String name) {
			try {
				return Token.valueOf(name.toUpperCase());
			} catch (final IllegalArgumentException iae) {
				return null;
			}
		}
	}

	/*
	 * (non-javadoc)
	 * 
	 * This class should never be instantiated since expression parsing is
	 * effectively stateless.
	 */
	private ExpressionParser() {
		// no-op
	}

	/**
	 * Returns a parse tree generated by performing a recursive descent parse
	 * operation on the provided string expression. The resulting tree can then be
	 * evaluated using the {@link Node#evaluate()} method or visually inspected
	 * using the {@link Node#getPrettyPrint()} method.
	 * 
	 * @param expression
	 *            expression to parse
	 * @return a parse tree generated by performing a recursive descent parse
	 *         operation on the provided string expression
	 */
	public static Node parse(final String expression) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Parsing input " + expression);
		}

		Node node = null;

		// let's just try pulling out a token and the params
		if (LOG.isTraceEnabled()) {
			LOG.trace("Matching tokens with parameters");
		}

		// check to see if we match the expected pattern for operations
		final Matcher operationMatcher = OPERATION_MATCHER_PATTERN.matcher(expression);
		if (operationMatcher.find()) {

			final Matcher tokenMatcher = TOKEN_EXTRACTOR_PATTERN.matcher(expression);
			if (tokenMatcher.find()) {

				if (LOG.isTraceEnabled()) {
					LOG.trace("Matched " + tokenMatcher.groupCount() + " groups");
				}

				final String tokenName = tokenMatcher.group(1);
				final String params = tokenMatcher.group(2);

				if (LOG.isDebugEnabled()) {
					LOG.debug("token: " + tokenName + ", params: (" + params + ")");
				}

				// if the token is a token, what kind of token?
				final Token token = Token.fromString(tokenName);
				if (token != null) {
					switch (token) {
					case ADD:
					case SUB:
					case DIV:
					case MULT:
						if (LOG.isTraceEnabled()) {
							LOG.trace("Operation node");
						}

						node = new OperationNode(token.getOperationClass());

						final List<String> operationParams = getParameters(2, params);

						final Node left = parse(operationParams.get(0));
						final Node right = parse(operationParams.get(1));

						node.setLeft(left);
						node.setRight(right);

						break;
					case LET:
						if (LOG.isTraceEnabled()) {
							LOG.trace("Let node");
						}

						node = new LetNode();

						final List<String> letParams = getParameters(3, params);

						// first param is a var
						final VariableNode variable = new VariableNode(letParams.get(0));

						// second param is the variable expression
						final Node variableExpression = parse(letParams.get(1));
						variable.setLeft(variableExpression);

						final Node operation = parse(letParams.get(2));

						node.setLeft(variable);
						node.setRight(operation);
					}
				} else {
					throw new IllegalArgumentException("Invalid operation specified: " + tokenName);
				}
			} else {
				throw new IllegalArgumentException("Malformed operation expression: " + expression);
			}
		} else {
			// Not an operation, so must be either a literal or a variable
			if (Character.isDigit(expression.charAt(0))) {
				if (LOG.isTraceEnabled()) {
					LOG.trace("Literal node");
				}

				node = new LiteralNode(Integer.valueOf(expression));
			} else {
				if (LOG.isTraceEnabled()) {
					LOG.trace("Variable node");
				}
				node = new VariableNode(expression);
			}
		}

		return node;
	}

	/**
	 * Returns a list of string parameters parsed from the provided parameter
	 * string. Parameters are delimited either by a ',' or end-of-string.
	 * Pattern-matching is used to extract parameters from provided string. Only
	 * parameters representing literal values, variables, or expressions are
	 * supported.
	 * 
	 * @param expected
	 *            the number of expected parameters.
	 * @param params
	 *            the string containing the unparsed parameters
	 * @return a list of string parameters parsed from the provided parameter string
	 * @throws IllegalArgumentException
	 *             if an error occurs during parameter parsing
	 */
	public static List<String> getParameters(final int expected, final String params) {

		String toParse = params.replaceAll("\\s+", "");

		if (LOG.isDebugEnabled()) {
			LOG.debug("Parsing operation parameters. Expecting " + expected + " parameters in " + toParse);
		}

		final List<String> parsedParams = new ArrayList<>();

		for (int i = 0; i < expected; i++) {

			String operationRegex = (i == expected - 1) ? OPERATION_PREFIX + "$" : OPERATION_PREFIX + ",";
			Pattern operationPattern = Pattern.compile(operationRegex);

			final Matcher operationMatcher = operationPattern.matcher(toParse);
			if (operationMatcher.find()) {

				if (LOG.isTraceEnabled()) {
					LOG.trace("Parsing operation");
				}

				String opParam = operationMatcher.group(1);
				parsedParams.add(opParam);
				toParse = toParse.replaceFirst(operationRegex, "");

			} else {
				if (LOG.isTraceEnabled()) {
					LOG.trace("Not parsing operation");
				}

				// it's either a literal or a variable
				if (Character.isDigit(toParse.charAt(0))) {
					if (LOG.isTraceEnabled()) {
						LOG.trace("Parsing a literal");
					}

					String literalRegex = (i == expected - 1) ? LITERAL_PREFIX + "$" : LITERAL_PREFIX + ",";

					Pattern literalPattern = Pattern.compile(literalRegex);
					final Matcher literalMatcher = literalPattern.matcher(toParse);
					if (literalMatcher.find()) {
						String parsedLiteral = literalMatcher.group(1);
						parsedParams.add(parsedLiteral);
						toParse = toParse.replaceFirst(literalRegex, "");
					} else {
						throw new IllegalArgumentException("Illegal literal value: '" + toParse + "'");
					}
				} else {
					if (LOG.isTraceEnabled()) {
						LOG.trace("Parsing a variable");
					}

					String variableRegex = (i == expected - 1) ? VARIABLE_PREFIX + "$" : VARIABLE_PREFIX + ",";

					Pattern variablePattern = Pattern.compile(variableRegex);
					final Matcher variableMatcher = variablePattern.matcher(toParse);
					if (variableMatcher.find()) {
						String parsedVariable = variableMatcher.group(1);
						parsedParams.add(parsedVariable);
						toParse = toParse.replaceFirst(variableRegex, "");
					}
				}
			}

		}

		if (parsedParams.size() != expected) {
			throw new IllegalArgumentException("Syntax error in parameters: '" + params + "'");
		}

		return parsedParams;
	}
}
