package fr.mdulac.mower.domain;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          All the available orientations.
 * 
 */
public enum Orientation {

	NORTH("N") {
		@Override
		public Orientation next() {
			return EAST;
		}

		@Override
		public Orientation previous() {
			return WEST;
		}
	},
	EAST("E") {
		@Override
		public Orientation next() {
			return SOUTH;
		}

		@Override
		public Orientation previous() {
			return NORTH;
		}
	},
	WEST("W") {
		@Override
		public Orientation next() {
			return NORTH;
		}

		@Override
		public Orientation previous() {
			return SOUTH;
		}
	},
	SOUTH("S") {
		@Override
		public Orientation next() {
			return WEST;
		}

		@Override
		public Orientation previous() {
			return EAST;
		}
	};

	// All these orientations must know their "neighbors".

	/**
	 * Get the next orientation.
	 * 
	 * @return The next orientation.
	 */
	public abstract Orientation next();

	/**
	 * Get the previous orientation.
	 * 
	 * @return The previous orientation.
	 */
	public abstract Orientation previous();

	private final String code;

	/**
	 * The constructor.
	 * 
	 * @param code
	 *            The orientation code.
	 */
	private Orientation(String code) {
		this.code = code;
	}

	/**
	 * Convert easily an orientation code to an orientation object with this
	 * method.
	 * 
	 * @param code
	 *            The orientation code from the configuration.
	 * @return The orientation enum associated to this code.
	 */
	public static Orientation of(String code) {

		if (code == null) {
			throw new IllegalArgumentException("Orientation value must not be null.");
		}

		for (Orientation orientation : Orientation.values()) {
			if (orientation.getCode().equals(code)) {
				return orientation;
			}
		}

		throw new IllegalArgumentException(code + " is not a valid Orientation value.");

	}

	/**
	 * Get the orientation code.
	 * 
	 * @return The code of the orientation.
	 */
	public String getCode() {
		return code;
	}

}
