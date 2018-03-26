/**
 * Copyright 2018 R.Bets. All rights reserved.  This source code is provided without warranty or guarantee of any 
 * kind. The author assumes no liability for any damages, be they direct, indirect, incidental, consequential, mental,
 * or emotional.
 */
package org.galiosten.calculator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

/**
 * This class configures the logger with command-line options. If no logging
 * options are specified, the default log level will be used.
 * 
 * @author rbets
 *
 */
public class LogConfigurator {

	/**
	 * Default log level.
	 */
	public static final Level DEFAULT_LOG_LEVEL = Level.INFO;

	private static final LogConfigurator INSTANCE = new LogConfigurator();

	/*
	 * (non-javadoc)
	 * 
	 * Private constructor.
	 */
	private LogConfigurator() {
		// no-op
	}

	/**
	 * Sets the log level for the root logger if specified in the command line
	 * options. If not specified, the level defaults to INFO. If set successfully,
	 * the previous level is returned.
	 * 
	 * @param commandLine
	 *            command line options
	 * @return the previously set log level
	 * @throws ParseException
	 *             if the log level specified in the command line is invalid
	 */
	public static Level initializeLogger(final CommandLine commandLine) throws ParseException {

		Level newLevel = DEFAULT_LOG_LEVEL;

		if (commandLine.hasOption("q")) {
			newLevel = Level.OFF;
		} else if (commandLine.hasOption("l")) {
			final String newLevelName = commandLine.getOptionValue("l");
			newLevel = Level.getLevel(newLevelName);

			if (newLevel == null) {
				throw new ParseException("Unrecognized log level: " + newLevelName);
			}
		}

		return INSTANCE.setLogLevel(newLevel);
	}

	/*
	 * (non-javadoc)
	 * 
	 * Sets the log level to the specified value
	 */
	private Level setLogLevel(final Level newLevel) {

		final LoggerContext loggingContext = (LoggerContext) LogManager.getContext(false);
		final Configuration config = loggingContext.getConfiguration();

		LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);

		final Level previousLevel = loggerConfig.getLevel();

		loggerConfig.setLevel(newLevel);

		loggingContext.updateLoggers();

		return previousLevel;
	}
}
