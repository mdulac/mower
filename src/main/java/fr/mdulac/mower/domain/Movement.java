package fr.mdulac.mower.domain;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          The available movements. Movements are not rotation. For this moment,
 *          there is only a forward movement.
 * 
 */
public enum Movement {

	// Forward is a movement to the front. The step is one long, so the movements are
	// step by step.
	FORWARD(1);

	/*
	 * We can imagine an other movement RUN(2), a little more faster.
	 */

	private final int step;

	/**
	 * This is the constructor.
	 * 
	 * @param step
	 *            The number of step you want to move.
	 */
	private Movement(int step) {
		this.step = step;
	}

	/**
	 * Get the step value.
	 * 
	 * @return The step you want to move.
	 */
	public int getStep() {
		return step;
	}
}
