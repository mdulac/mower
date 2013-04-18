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
	private MovementStrategy movementStrategy;

	/**
	 * The constructor.
	 * 
	 * @param position
	 *            The initial position of the moveable.
	 * @param orientation
	 *            The initial orientation of the movable.
	 * @param movementStrategy
	 *            The movement strategy to apply.
	 */
	public Movable(Position position, Orientation orientation, MovementStrategy movementStrategy) {

		if (position == null) {
			throw new IllegalArgumentException("Position must not be null.");
		}

		if (orientation == null) {
			throw new IllegalArgumentException("Orientation must not be null.");
		}

		if (movementStrategy == null) {
			throw new IllegalArgumentException("Movement strategy must not be null.");
		}

		this.position = position;
		this.orientation = orientation;
		this.movementStrategy = movementStrategy;
	}

	/**
	 * Get the mower position.
	 * 
	 * @return The position.
	 */
	public Position getPosition() {
		return position;
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
		Position target = movementStrategy.move(movement, this.getPosition(), this.getOrientation());
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
