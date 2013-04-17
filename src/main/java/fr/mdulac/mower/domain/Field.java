package fr.mdulac.mower.domain;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mdulac.mower.api.Grid;
import fr.mdulac.mower.api.PositionController;
import fr.mdulac.mower.app.PositionChangedEvent;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          A field is a user understandable grid (horizontal and vertical size
 *          instead of grid position), in which it is posible to link movables.
 */
public final class Field extends Grid implements PositionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(Field.class);
	private final Set<Position> nonEmptyPosition = new HashSet<Position>();

	/**
	 * The constructor.
	 * 
	 * @param horizontalSize
	 *            The horizontal size of the field. It must be at least 1 long.
	 * @param verticalSize
	 *            The vertical size of the field. It must be at least 1 long.
	 */
	public Field(int horizontalSize, int verticalSize) {
		super(new Position(horizontalSize - 1, verticalSize - 1));
	}

	/**
	 * Get the horizontal size.
	 * 
	 * @return The horizontal size.
	 */
	public int getHorizontalSize() {
		return this.getTopRightPosition().getY() + 1;
	}

	/**
	 * Get the vertical size.
	 * 
	 * @return The vertical size.
	 */
	public int getVerticalSize() {
		return this.getTopRightPosition().getX() + 1;
	}

	@Override
	public String toString() {
		return new StringBuilder("Field with ").append(this.getVerticalSize()).append(" x ")
				.append(this.getHorizontalSize()).append(" size").toString();
	}

	@Override
	public void positionChanged(PositionChangedEvent event) {
		nonEmptyPosition.remove(event.getOldPosition());
		nonEmptyPosition.add(event.getNewPosition());
		LOGGER.info("Mower {} moved from {} to {}", event.getSource(), event.getOldPosition(), event.getNewPosition());
	}

	/**
	 * Collision detection method. Use this to know if there is already a linked
	 * mower.
	 * 
	 * @param position
	 *            The position you want to check.
	 * @return True if there is already a linked mower, false otherwise.
	 */
	@Override
	public boolean isPositionEmpty(Position position) {
		return !nonEmptyPosition.contains(position);
	}

	@Override
	public void takePlaceAt(Position position) {
		nonEmptyPosition.add(position);
	}

}
