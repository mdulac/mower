package fr.mdulac.mower.api;

import fr.mdulac.mower.domain.Position;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          This is a rectangular area, mades of {@link Position}.
 * 
 */
public abstract class Grid {

	private final Position topRightPosition;

	/**
	 * The constructor.
	 * 
	 * @param topRightPosition
	 *            The position at the top right. It is assumed that the bottom
	 *            left position is (0;0)
	 */
	public Grid(Position topRightPosition) {

		if (topRightPosition.getX() < 0) {
			throw new IllegalArgumentException("Horizontal grid size must be positive.");
		}

		if (topRightPosition.getY() < 0) {
			throw new IllegalArgumentException("Vertical grid size must be positive.");
		}

		this.topRightPosition = topRightPosition;
	}

	/**
	 * Get the top right position.
	 * 
	 * @return The top right position.
	 */
	public Position getTopRightPosition() {
		return topRightPosition;
	}

	/**
	 * Does the grid is composed by this position.
	 * 
	 * @param position
	 *            The position you want to know if it is a component of this
	 *            grid.
	 * @return True if the position is in the grid, false otherwise.
	 */
	public boolean contains(Position position) {

		if (position == null) {
			return false;
		}

		// Our grid is only composed by positive coordinates
		if (position.getX() < 0 || position.getY() < 0) {
			return false;
		}

		return this.topRightPosition.getX() - position.getX() > -1
				&& this.topRightPosition.getY() - position.getY() > -1;
	}

}