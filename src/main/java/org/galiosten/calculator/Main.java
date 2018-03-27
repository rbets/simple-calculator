/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
import org.galiosten.calculator.model.Node;
import org.galiosten.calculator.parser.ExpressionParser;

/**
 * Entry point for the calculator command-line application.
 * 
 * @author rbets
 *
 */
public class Main {

	private static final Options OPTIONS = new Options();

	static {
		OPTIONS.addOption(Option.builder("l").longOpt("log-level").required(false).hasArg().numberOfArgs(1)
				.desc("set log level. One of TRACE|DEBUG|INFO|WARN|ERROR|OFF.").build());

		OPTIONS.addOption(Option.builder("h").longOpt("help").build());

		OPTIONS.addOption(Option.builder("q").longOpt("quiet").desc("disable logging. Shortcut for '-l OFF'.").build());

		// if the project is modified to start using a logging config file, remove this
		// line
		StatusLogger.getLogger().setLevel(Level.OFF);
	}

	private static final Logger LOG = LogManager.getLogger(Main.class);

	/**
	 * The entry point for the command line application. This method parses the
	 * input and evaluates and displays the result on stdout.
	 * 
	 * @param args
	 *            command-line arguments
	 */
	public static void main(final String... args) {

		final CommandLineParser cmdLineParser = new DefaultParser();

		try {
			final CommandLine cmdLine = cmdLineParser.parse(OPTIONS, args);

			// check to see that 'help' wasn't asked for
			if (cmdLine.hasOption("h")) {
				final HelpFormatter formatter = new HelpFormatter();

				formatter.printHelp("java -jar calculator-<version>.jar [-h] [-l <arg>] [-q] <expression>", OPTIONS,
						false);
				System.exit(0);
			}

			// initialize the log configuration from the command line
			LogConfigurator.initializeLogger(cmdLine);

			final List<String> remainingArguments = cmdLine.getArgList();

			if (remainingArguments.isEmpty()) {
				throw new ParseException("Must specify an expression");
			}

			final String input = remainingArguments.get(0);

			LOG.info("Evaluating expression '" + input + "'");

			final Node root = ExpressionParser.parse(input);

			if (LOG.isDebugEnabled()) {
				LOG.debug("Expression tree from [" + input + "]:\n" + root.getPrettyPrint());
			}

			final Integer result = root.evaluate();

			System.out.println(result);
		} catch (final Exception e) {
			System.err.println("ERROR: " + e.getMessage());

			// a little extra help in debugging
			if (LOG.isTraceEnabled()) {
				LOG.trace("Error during expression evaluation: " + e.getMessage(), e);
			}

			System.exit(1);
		}
	}

}
