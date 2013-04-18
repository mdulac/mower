package fr.mdulac.mower.impl;

import fr.mdulac.mower.api.MovementStrategy;
import fr.mdulac.mower.domain.Movement;
import fr.mdulac.mower.domain.Orientation;
import fr.mdulac.mower.domain.Position;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          This default movement strategy is a simple application of a forward
 *          movement.
 */
public class DefaultMovementStrategy implements MovementStrategy {

	@Override
	public Position move(Movement movement, Position position, Orientation orientation) {

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

		return target;

	}

}
