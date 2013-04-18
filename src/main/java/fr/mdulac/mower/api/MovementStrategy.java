package fr.mdulac.mower.api;

import fr.mdulac.mower.domain.Movement;
import fr.mdulac.mower.domain.Orientation;
import fr.mdulac.mower.domain.Position;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          A movement strategy is the moving logic of a movable. It describes
 *          the movement, in terms of position and orientation.
 * 
 */
public interface MovementStrategy {

	/**
	 * Invoke this method to calculate the next position.
	 * 
	 * @param movement
	 *            The type of movement to apply.
	 * @param position
	 *            The current position.
	 * @param orientation
	 *            The current orientation.
	 * @return The next position.
	 */
	Position move(Movement movement, Position position, Orientation orientation);

}
