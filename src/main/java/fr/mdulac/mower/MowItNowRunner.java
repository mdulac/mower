package fr.mdulac.mower;

import java.util.ArrayList;
import java.util.List;

import fr.mdulac.mower.MowItNowConfiguration.MowerCommands;
import fr.mdulac.mower.domain.Command;
import fr.mdulac.mower.domain.Field;
import fr.mdulac.mower.domain.Mower;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          This runner is used to execute the MowItNow simulation. It executes
 *          the configuration you passed at its creation.
 * 
 */
public enum MowItNowRunner {

	INSTANCE;

	/**
	 * Run the simulation with the configuration passed as a parameter.
	 * 
	 * @param configuration
	 *            The configuration you want to process.
	 * @return The list of mowers at their final position.
	 */
	public List<Mower> runMowItNow(MowItNowConfiguration configuration) {

		List<Mower> result = new ArrayList<Mower>();
		Field field = configuration.getConfiguredField();
		List<MowerCommands> configuredMowerCommands = configuration.getConfiguredMowerCommands();

		for (MowerCommands mowerCommands : configuredMowerCommands) {

			Mower mower = mowerCommands.getMower();

			// We process the mower only if its initial position is valid, and
			// if there is no mower yet.
			if (field.contains(mower.getPosition()) && !field.isThereAlreadyAMowerAt(mower.getPosition())) {

				List<Command> commands = mowerCommands.getCommands();
				mower.linkTo(field);

				for (Command command : commands) {
					Command.execute(mower, command);
				}

				result.add(mower);
			}

		}

		return result;
	}

}
