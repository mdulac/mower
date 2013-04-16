package fr.mdulac.mower.api;

import java.io.BufferedReader;

import fr.mdulac.mower.app.MowItNowConfiguration;
import fr.mdulac.mower.exceptions.MowItNowParseException;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          A parser is used to transform a configuration stream, read by
 *          {@link MowerReaderService}, into a configuration object. All the
 *          parsers must implement this interface.
 * 
 */
public interface MowerParser {

	/**
	 * Parse a stream.
	 * 
	 * @param reader
	 *            The reader to the configuration stream.
	 * @return The configuration object.
	 * @throws MowItNowParseException
	 *             If there is a parse error.
	 */
	MowItNowConfiguration parse(BufferedReader reader) throws MowItNowParseException;

}
