package fr.mdulac.mower.api;

import fr.mdulac.mower.app.MowItNowConfiguration;
import fr.mdulac.mower.exceptions.MowItNowParseException;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          This interface must be implemented by Input Reader (File reader,
 *          Network reader, ...). This service reads the input configuration of
 *          the Mower Application.
 * 
 */
public interface MowerReaderService {

	/**
	 * Read the configuration.
	 * 
	 * @return The configuration object.
	 * @throws MowItNowParseException
	 *             If there is a parse error, an exception is thrown.
	 */
	MowItNowConfiguration read() throws MowItNowParseException;

}
