package fr.mdulac.mower.impl;

import org.testng.annotations.Test;

import static fr.mdulac.mower.api.Factory.newPosition;
import fr.mdulac.mower.domain.Movement;
import fr.mdulac.mower.domain.Orientation;
import fr.mdulac.mower.domain.Position;
import fr.mdulac.mower.domain.assertj.PositionAssert;

public class DefaultMovementStrategyTest {

	@Test
	public void test_default_strategy_when_oriented_to_the_north() {
		DefaultMovementStrategy strategy = new DefaultMovementStrategy();
		Position target = strategy.move(Movement.FORWARD, newPosition(3, 3), Orientation.NORTH);
		PositionAssert.assertThat(target).hasX(3);
		PositionAssert.assertThat(target).hasY(4);
	}

	@Test
	public void test_default_strategy_when_oriented_to_the_east() {
		DefaultMovementStrategy strategy = new DefaultMovementStrategy();
		Position target = strategy.move(Movement.FORWARD, newPosition(3, 3), Orientation.EAST);
		PositionAssert.assertThat(target).hasX(4);
		PositionAssert.assertThat(target).hasY(3);
	}

	@Test
	public void test_default_strategy_when_oriented_to_the_south() {
		DefaultMovementStrategy strategy = new DefaultMovementStrategy();
		Position target = strategy.move(Movement.FORWARD, newPosition(3, 3), Orientation.SOUTH);
		PositionAssert.assertThat(target).hasX(3);
		PositionAssert.assertThat(target).hasY(2);
	}

	@Test
	public void test_default_strategy_when_oriented_to_the_west() {
		DefaultMovementStrategy strategy = new DefaultMovementStrategy();
		Position target = strategy.move(Movement.FORWARD, newPosition(3, 3), Orientation.WEST);
		PositionAssert.assertThat(target).hasX(2);
		PositionAssert.assertThat(target).hasY(3);
	}

}
