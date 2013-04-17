package fr.mdulac.mower.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mdulac.mower.api.Factory;
import fr.mdulac.mower.api.MowerReaderService;
import fr.mdulac.mower.domain.Mower;
import fr.mdulac.mower.exceptions.MowItNowParseException;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          Main class
 * 
 */
public final class MowItNow {

	private static final int ERROR = -1;
	private static final int PARSE_ERROR = -2;

	private static final Logger LOGGER = LoggerFactory.getLogger(MowItNow.class);
	private static final String BAD_ARGUMENT_MESSAGE = "You must enter an input file.";
	
	private MowItNow() {
		// Can't use constructor
	}

	public static void main(String[] args) {

		try {
			checkArgument(args);
			MowerReaderService fileInputService = Factory.newFileReaderService(args[0], Factory.newMowerParser());
			MowItNowConfiguration configuration = fileInputService.read();

			List<Mower> result = MowItNowRunner.INSTANCE.runMowItNow(configuration);

			LOGGER.info("Here is the result: ");
			for (Mower mower : result) {
				LOGGER.info("{} {} {}", mower.getPosition().getX(), mower.getPosition().getY(), mower.getOrientation()
						.getCode());
			}

		} catch (MowItNowParseException mpe) {
			LOGGER.error("Please correct your configuration file: " + mpe.getMessage());
			System.exit(PARSE_ERROR);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			System.exit(ERROR);
		}

	}

	private static void checkArgument(String... args) {
		if (args.length != 1) {
			throw new IllegalArgumentException(BAD_ARGUMENT_MESSAGE);
		}
	}

}
