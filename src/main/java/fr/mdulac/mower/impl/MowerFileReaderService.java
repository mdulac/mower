package fr.mdulac.mower.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mdulac.mower.MowItNowConfiguration;
import fr.mdulac.mower.api.MowerReaderService;
import fr.mdulac.mower.api.MowerParser;
import fr.mdulac.mower.exceptions.MowItNowParseException;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          This service implementation is used to read and process a
 *          configuration file.
 * 
 */
public final class MowerFileReaderService implements MowerReaderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MowerFileReaderService.class);

	private final BufferedReader reader;
	private final MowerParser parser;

	private final String filename;

	/**
	 * The constructor.
	 * 
	 * @param fileName
	 *            The name of the configuration file.
	 * @param parser
	 *            The parser you want to use.
	 */
	public MowerFileReaderService(String fileName, MowerParser parser) {

		if (fileName == null) {
			throw new IllegalArgumentException("Filename argument must not be null.");
		}

		if (parser == null) {
			throw new IllegalArgumentException("Parser argument must not be null.");
		}

		try {
			this.reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			LOGGER.error("{}", e.getMessage());
			throw new IllegalArgumentException("File " + fileName + " cannot be opened.");
		}

		this.filename = fileName;
		this.parser = parser;
	}

	@Override
	public MowItNowConfiguration read() throws MowItNowParseException {

		try {
			return parser.parse(reader);
		} catch (MowItNowParseException e) {
			LOGGER.error(e.getMessage());
			throw new MowItNowParseException("File " + filename + " is malformed.");
		} finally {
			closeSource();
		}

	}

	private void closeSource() {
		try {
			reader.close();
		} catch (IOException e) {
			throw new IllegalStateException("File cannot be close.");
		}
	}

}
