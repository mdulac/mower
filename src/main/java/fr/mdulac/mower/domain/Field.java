package fr.mdulac.mower.domain;

import java.util.HashSet;
import java.util.Set;

import fr.mdulac.mower.api.Grid;
import fr.mdulac.mower.api.Movable;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          A field is a user understandable grid (horizontal and vertical size
 *          instead of grid position), in which it is posible to link movables.
 */
public final class Field extends Grid {

	private final Set<Position> linkedMowers = new HashSet<Position>();

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

		if (horizontalSize < 1) {
			throw new IllegalArgumentException("Horizontal size must be greater than 0.");
		}

		if (verticalSize < 1) {
			throw new IllegalArgumentException("Vertical size must be greater than 0.");
		}

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
		return new StringBuilder("Field with ").append(this.getHorizontalSize()).append(" x ")
				.append(this.getVerticalSize()).append(" size").toString();
	}

	/**
	 * Link this field to a mower. This unidirectional relation should not be
	 * used directly. You must use {@link Mower#linkTo(Field)} to create a
	 * bidirectional relation.
	 * 
	 * @param mower
	 *            The mower you want to link.
	 */
	protected void link(Movable mower) {
		this.linkedMowers.add(mower.getPosition());
	}

	/**
	 * Unlink this field from a mower.
	 * 
	 * @param mower
	 *            The mower you want to unlink.
	 */
	protected void unlink(Movable mower) {
		this.linkedMowers.remove(mower.getPosition());
	}

	/**
	 * Unlink all the mowers.
	 */
	public void unlinkAllMowers() {
		this.linkedMowers.clear();
	}

	/**
	 * Collision detection method. Use this to know if there is already a linked
	 * mower.
	 * 
	 * @param position
	 *            The position you want to check.
	 * @return True if there is already a linked mower, false otherwise.
	 */
	public boolean isThereAlreadyAMowerAt(Position position) {
		return linkedMowers.contains(position);
	}

	/**
	 * This method must be invoke to update the position changing.
	 * 
	 * @param oldPosition
	 *            The position before moving.
	 * @param newPosition
	 *            The position after moving.
	 */
	public void updatePosition(Position oldPosition, Position newPosition) {
		linkedMowers.remove(oldPosition);
		linkedMowers.add(newPosition);
	}

}
