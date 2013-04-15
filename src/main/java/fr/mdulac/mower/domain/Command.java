package fr.mdulac.mower.domain;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          All the available commands a user can indicate in the configuration.
 * 
 */
public enum Command {

	// At the moment, only these commands are available.
	LEFT("G"), RIGHT("D"), FORWARD("A");

	private final String code;

	/**
	 * The constructor.
	 * 
	 * @param code
	 *            The command code (from a configuration).
	 */
	private Command(String code) {
		this.code = code;
	}

	/**
	 * Convert easily a command code to a command object with this method.
	 * 
	 * @param code
	 *            The command code from the configuration.
	 * @return The command enum associated to this code.
	 */
	public static Command of(String code) {

		if (code == null) {
			throw new IllegalArgumentException("Command value must not be null.");
		}

		for (Command command : Command.values()) {
			if (command.getCode().equals(code)) {
				return command;
			}
		}

		throw new IllegalArgumentException(code + " is not a valid Command value.");
	}

	/**
	 * Get the command code.
	 * 
	 * @return The code of the command.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Execute a command on a {@link Mower}.
	 * 
	 * @param mower
	 *            The {@link Mower} you want to execute the command on.
	 * @param command
	 *            The command you want to execute.
	 */
	public static void execute(Mower mower, Command command) {

		switch (command) {
		case LEFT:
			mower.rotate(Rotation.LEFT);
			break;
		case RIGHT:
			mower.rotate(Rotation.RIGHT);
			break;
		case FORWARD:
			mower.move(Movement.FORWARD);
		}
	}

}
