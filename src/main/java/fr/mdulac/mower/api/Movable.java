package fr.mdulac.mower.api;

import fr.mdulac.mower.domain.Movement;
import fr.mdulac.mower.domain.Orientation;
import fr.mdulac.mower.domain.Position;
import fr.mdulac.mower.domain.Rotation;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          A movable is a mobile you can move and rotate by commands.
 * 
 */
public abstract class Movable {

	private Position position;
	private Orientation orientation;

	public Movable(Position position, Orientation orientation) {

		if (position == null) {
			throw new IllegalArgumentException("Position must not be null.");
		}

		if (orientation == null) {
			throw new IllegalArgumentException("Orientation must not be null.");
		}

		this.position = new Position(position.getX(), position.getY());
		this.orientation = orientation;
	}

	/**
	 * Get the mower position.
	 * 
	 * @return The position.
	 */
	public Position getPosition() {
		return new Position(position.getX(), position.getY());
	}

	protected void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Get the mower orientation.
	 * 
	 * @return The orientation.
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	protected void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * This method must be implemented in the concrete subclasses. It is used to
	 * ask a new position to an environment. For example, in the case of the
	 * {@link Mower}, the environment is the field (it checks constraints about
	 * bounded positions and occupied area).
	 * 
	 * @param target
	 *            The target position.
	 */
	public abstract void changeMyPositionTo(Position target);

	/**
	 * This method must be implemented in the concrete subclasses. It is used to
	 * ask a new orientation to an environment. For example, in the case of the
	 * {@link Mower}, there is no constraint about orientation. A mower always
	 * can change its orientation.
	 * 
	 * @param target
	 *            The target orientation.
	 */
	public abstract void changeMyOrientationTo(Orientation target);

	/**
	 * Move a movable.
	 * 
	 * @param movement
	 *            The movement you want to apply.
	 */
	public void move(Movement movement) {

		Position target;

		switch (movement) {
		case FORWARD:
			switch (orientation) {
			case NORTH:
				target = new Position(position.getX(), position.getY() + movement.getStep());
				break;
			case EAST:
				target = new Position(position.getX() + movement.getStep(), position.getY());
				break;
			case SOUTH:
				target = new Position(position.getX(), position.getY() - movement.getStep());
				break;
			case WEST:
				target = new Position(position.getX() - movement.getStep(), position.getY());
				break;
			default:
				throw new IllegalArgumentException("Your orientation looks weird: " + orientation);
			}
			break;
		default:
			throw new IllegalArgumentException("Illegal command for a movement: " + movement);
		}

		changeMyPositionTo(target);

	}

	/**
	 * Rotate a movable.
	 * 
	 * @param rotation
	 *            The rotation you want to apply.
	 */
	public void rotate(Rotation rotation) {

		switch (rotation) {
		case LEFT:
			changeMyOrientationTo(orientation.previous());
			break;
		case RIGHT:
			changeMyOrientationTo(orientation.next());
			break;
		default:
			throw new IllegalArgumentException("Illegal command for a rotation: " + rotation);
		}
	}

}
