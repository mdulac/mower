package fr.mdulac.mower.api;

import fr.mdulac.mower.app.PositionChangedEvent;
import fr.mdulac.mower.domain.Position;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          A position controller is an entity that manages the position of a
 *          {@link Mower}
 */
public interface PositionController {

	/**
	 * Request if a position is a valid position.
	 * 
	 * @param position
	 *            The requested position.
	 * @return True if it is valid, false otherwise.
	 */
	boolean contains(Position position);

	/**
	 * Request if a position is empty or not.
	 * 
	 * @param position
	 *            The requested position.
	 * @return True if it is empty, false otherwise.
	 */
	boolean isPositionEmpty(Position position);

	/**
	 * Occupy a position.
	 * 
	 * @param position
	 *            The position where to take place.
	 */
	void takePlaceAt(Position position);

	/**
	 * Send an event when there is a position changing.
	 * 
	 * @param event
	 *            The event.
	 */
	void positionChanged(PositionChangedEvent event);

}
