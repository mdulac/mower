package fr.mdulac.mower.domain;

import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

@Test
public class CommandTest {

	@Test
	public void test_valid_commands() {
		Assertions.assertThat(Command.of("A")).isEqualTo(Command.FORWARD);
		Assertions.assertThat(Command.of("G")).isEqualTo(Command.LEFT);
		Assertions.assertThat(Command.of("D")).isEqualTo(Command.RIGHT);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Z is not a valid Command value.")
	public void test_exception_when_invalid_commands() {
		Command.of("Z");
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Command value must not be null.")
	public void test_exception_when_null_commands() {
		Command.of(null);
	}

	@Test
	public void test_value_commands() {
		Assertions.assertThat(Command.of("A").getCode()).isEqualTo("A");
		Assertions.assertThat(Command.of("G").getCode()).isEqualTo("G");
		Assertions.assertThat(Command.of("D").getCode()).isEqualTo("D");
	}

	@Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "There is no field attached to the mower.")
	public void test_cannot_move_mower_when_not_attached_to_field() {
		Position startPosition = new Position(2, 2);
		Mower mower = new Mower(startPosition, Orientation.NORTH);

		Command.execute(mower, Command.FORWARD);
	}

	@Test
	public void test_execute_one_step_forward_commands_when_mower_is_oriented_to_the_north() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(2, 3);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.NORTH);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		Assertions.assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	@Test
	public void test_execute_one_step_forward_commands_when_mower_is_oriented_to_the_south() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(2, 1);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.SOUTH);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		Assertions.assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	@Test
	public void test_execute_one_step_forward_commands_when_mower_is_oriented_to_the_east() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(3, 2);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.EAST);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		Assertions.assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	@Test
	public void test_execute_one_step_forward_commands_when_mower_is_oriented_to_the_west() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(1, 2);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.WEST);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		Assertions.assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	@Test
	public void test_execute_rotation() {
		Position startPosition = new Position(2, 2);
		Mower mower = new Mower(startPosition, Orientation.NORTH);
		Command.execute(mower, Command.LEFT);
		Assertions.assertThat(mower.getOrientation()).isEqualTo(Orientation.WEST);
		Command.execute(mower, Command.LEFT);
		Assertions.assertThat(mower.getOrientation()).isEqualTo(Orientation.SOUTH);
		Command.execute(mower, Command.LEFT);
		Assertions.assertThat(mower.getOrientation()).isEqualTo(Orientation.EAST);
		Command.execute(mower, Command.LEFT);
		Assertions.assertThat(mower.getOrientation()).isEqualTo(Orientation.NORTH);
		Command.execute(mower, Command.RIGHT);
		Assertions.assertThat(mower.getOrientation()).isEqualTo(Orientation.EAST);
		Command.execute(mower, Command.RIGHT);
		Assertions.assertThat(mower.getOrientation()).isEqualTo(Orientation.SOUTH);
		Command.execute(mower, Command.RIGHT);
		Assertions.assertThat(mower.getOrientation()).isEqualTo(Orientation.WEST);
		Command.execute(mower, Command.RIGHT);
		Assertions.assertThat(mower.getOrientation()).isEqualTo(Orientation.NORTH);
	}
	
	@Test
	public void test_one_step_forward_outside_the_field() {
		Position startPosition = new Position(0, 0);
		Field field = new Field(3, 3);
		Mower mower = new Mower(startPosition, Orientation.WEST);
		mower.linkTo(field);
		Command.execute(mower, Command.FORWARD);
		Assertions.assertThat(mower.getPosition()).isEqualTo(startPosition);
	}
	
	@Test
	public void test_one_step_forward_to_a_non_empty_area() {
		Field field = new Field(3, 3);
		Mower moveableMower = new Mower(new Position(0, 0), Orientation.NORTH);
		Mower fixedMower = new Mower(new Position(0, 1), Orientation.WEST);
		moveableMower.linkTo(field);
		fixedMower.linkTo(field);
		
		Command.execute(moveableMower, Command.FORWARD);
		Assertions.assertThat(moveableMower.getPosition()).isEqualTo(new Position(0, 0));
	}

}
