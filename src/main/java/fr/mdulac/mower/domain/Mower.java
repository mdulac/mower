package fr.mdulac.mower.domain;

import fr.mdulac.mower.api.Movable;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          The mower is the principal (and unique) moveable. It is composed by
 *          a position and an orientation.
 */
public class Mower extends Movable {

	/**
	 * The constructor.
	 * 
	 * @param position
	 *            The initial position of the mower.
	 * @param orientation
	 *            The initial orientation of the mower.
	 */
	public Mower(Position position, Orientation orientation) {

		super(position, orientation);

		if (position == null) {
			throw new IllegalArgumentException("Position must not be null.");
		}

		if (orientation == null) {
			throw new IllegalArgumentException("Orientation must not be null.");
		}

	}

	/**
	 * The constructor if you want a mower with a North initial orientation.
	 * 
	 * @param position
	 *            The initial position of the mower.
	 */
	public Mower(Position position) {
		this(position, Orientation.NORTH);
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

	private Field field;

	/**
	 * Create a bidirectional relation between this mower and a field.
	 * 
	 * @param field
	 *            The field you want to link with the mower.
	 */
	public void linkTo(Field field) {
		field.link(this);
		this.field = field;
	}

	/**
	 * Remove a bidirectional relation between this mower and a field.
	 * 
	 * @param field
	 *            The field you want to unlink from the mower.
	 */
	public void unlinkFrom(Field field) {
		field.unlink(this);
		this.field = null;
	}

	@Override
	public void changeMyPositionTo(Position target) {

		if (field == null) {
			throw new IllegalStateException("There is no field attached to the mower.");
		}

		// Target must be a valid position and it must be free (no other mower
		// at this location)
		if (field.contains(target) && !field.isThereAlreadyAMowerAt(target)) {
			setPosition(new Position(target.getX(), target.getY()));
		}
	}

	@Override
	public void changeMyOrientationTo(Orientation target) {
		setOrientation(target);
	}

}
