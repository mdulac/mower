package fr.mdulac.mower.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mdulac.mower.api.Movable;
import fr.mdulac.mower.api.MovementStrategy;
import fr.mdulac.mower.api.PositionController;
import fr.mdulac.mower.app.PositionChangedEvent;
import fr.mdulac.mower.impl.DefaultMovementStrategy;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          The mower is the principal (and unique) moveable. It is composed by
 *          a position and an orientation.
 */
public class Mower extends Movable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Mower.class);
	private PositionController positionController;

	/**
	 * The constructor.
	 * 
	 * @param position
	 *            The initial position of the mower.
	 * @param orientation
	 *            The initial orientation of the mower.
	 * @param movementStrategy
	 *            The movement strategy to apply.
	 */
	public Mower(Position position, Orientation orientation, MovementStrategy movementStrategy) {
		super(position, orientation, movementStrategy);
	}

	/**
	 * The constructor.
	 * 
	 * @param position
	 *            The initial position of the mower.
	 * @param orientation
	 *            The initial orientation of the mower.
	 */
	public Mower(Position position, Orientation orientation) {
		super(position, orientation, new DefaultMovementStrategy());
	}

	@Override
	public String toString() {
		return new StringBuilder("Mower @ [").append(this.getPosition().getX()).append(";")
				.append(this.getPosition().getY()).append("] oriented to ").append(this.getOrientation()).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getOrientation() == null) ? 0 : this.getOrientation().hashCode());
		result = prime * result + ((this.getPosition() == null) ? 0 : this.getPosition().hashCode());
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
		Mower other = (Mower) obj;
		if (this.getOrientation() != other.getOrientation()) {
			return false;
		}
		if (this.getPosition() == null) {
			if (other.getPosition() != null) {
				return false;
			}
		} else if (!this.getPosition().equals(other.getPosition())) {
			return false;
		}
		return true;
	}

	/**
	 * Create a bidirectional relation between this mower and a field.
	 * 
	 * @param controller
	 *            The field you want to link with the mower.
	 */
	public void linkTo(PositionController controller) {

		if (controller == null) {
			throw new IllegalArgumentException("Cannot link the mower to a null field.");
		}

		if (this.positionController != null) {
			throw new IllegalStateException("This mower is already linked. You must unlink it before.");
		}

		this.positionController = controller;
		this.positionController.takePlaceAt(this.getPosition());
	}

	/**
	 * Remove a bidirectional relation between this mower and a field.
	 * 
	 * @param controller
	 *            The field you want to unlink from the mower.
	 */
	public void unlinkFrom(PositionController controller) {

		if (controller == null) {
			throw new IllegalArgumentException("Cannot unlink the mower from a null field.");
		}

		if (this.positionController == null) {
			throw new IllegalStateException("This mower is not linked. You must link it before.");
		}

		this.positionController = null;
	}

	@Override
	public void changeMyPositionTo(Position target) {

		if (target == null) {
			throw new IllegalArgumentException("Target position must not be null.");
		}

		if (positionController == null) {
			throw new IllegalStateException("There is no field attached to the mower.");
		}

		// Target must be a valid position and it must be free (no other mower
		// at this location)
		if (!positionController.contains(target)) {
			LOGGER.info("Cannot move mower from {} to {} - outside the fied", this.getPosition(), target);
		} else if (!positionController.isPositionEmpty(target)) {
			LOGGER.info("Cannot move mower from {} to {} - another mower is here", this.getPosition(), target);
		} else {
			Position oldPosition = this.getPosition();
			setPosition(new Position(target.getX(), target.getY()));
			Position newPosition = this.getPosition();
			positionController.positionChanged(new PositionChangedEvent(this, oldPosition, newPosition));
		}
	}

	@Override
	public void changeMyOrientationTo(Orientation target) {

		if (target == null) {
			throw new IllegalArgumentException("Target orientation must not be null.");
		}

		LOGGER.info("Mower rotate from {} to {}", this.getOrientation(), target);
		setOrientation(target);
	}

	/**
	 * Get the position controller.
	 * 
	 * @return The position controller.
	 */
	public PositionController getPositionController() {
		return positionController;
	}

}
