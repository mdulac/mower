package fr.mdulac.mower.domain;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          This is an immutable two dimensions position.
 * 
 */
public final class Position {

	private final int x;
	private final int y;

	/**
	 * The constructor.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinates.
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the x coordinate.
	 * 
	 * @return The x coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y coordinate.
	 * 
	 * @return The y coordinate.
	 */
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "[" + x + ";" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Position other = (Position) obj;
		return x == other.x && y == other.y;
	}

}
