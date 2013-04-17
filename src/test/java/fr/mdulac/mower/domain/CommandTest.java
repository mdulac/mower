package fr.mdulac.mower.domain;

import static org.assertj.core.api.Assertions.assertThat;
import fr.mdulac.mower.domain.assertj.OrientationAssert;
import fr.mdulac.mower.domain.assertj.PositionAssert;

import org.testng.annotations.Test;

@Test
public class CommandTest {

	public void test_valid_commands() {
		assertThat(Command.of("A")).isEqualTo(Command.FORWARD);
		assertThat(Command.of("G")).isEqualTo(Command.LEFT);
		assertThat(Command.of("D")).isEqualTo(Command.RIGHT);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Z is not a valid Command value.")
	public void test_exception_when_invalid_commands() {
		Command.of("Z");
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Command value must not be null.")
	public void test_exception_when_null_commands() {
		Command.of(null);
	}

	public void test_value_commands() {
		assertThat(Command.of("A").getCode()).isEqualTo("A");
		assertThat(Command.of("G").getCode()).isEqualTo("G");
		assertThat(Command.of("D").getCode()).isEqualTo("D");
	}

	@Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "There is no field attached to the mower.")
	public void test_cannot_move_mower_when_not_attached_to_field() {
		Position startPosition = new Position(2, 2);
		Mower mower = new Mower(startPosition, Orientation.NORTH);

		Command.execute(mower, Command.FORWARD);
	}

	public void test_execute_one_step_forward_commands_when_mower_is_oriented_to_the_north() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(2, 3);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.NORTH);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	public void test_execute_one_step_forward_commands_when_mower_is_oriented_to_the_south() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(2, 1);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.SOUTH);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	public void test_execute_one_step_forward_commands_when_mower_is_oriented_to_the_east() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(3, 2);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.EAST);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	public void test_execute_one_step_forward_commands_when_mower_is_oriented_to_the_west() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(1, 2);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.WEST);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	public void test_execute_rotation() {
		Position startPosition = new Position(2, 2);
		Mower mower = new Mower(startPosition, Orientation.NORTH);
		Command.execute(mower, Command.LEFT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("W");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.WEST);
		Command.execute(mower, Command.LEFT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("S");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.SOUTH);
		Command.execute(mower, Command.LEFT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("E");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.EAST);
		Command.execute(mower, Command.LEFT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("N");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.NORTH);
		Command.execute(mower, Command.RIGHT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("E");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.EAST);
		Command.execute(mower, Command.RIGHT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("S");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.SOUTH);
		Command.execute(mower, Command.RIGHT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("W");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.WEST);
		Command.execute(mower, Command.RIGHT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("N");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.NORTH);
	}

	public void test_one_step_forward_outside_the_field() {
		Position startPosition = new Position(0, 0);
		Field field = new Field(3, 3);
		Mower mower = new Mower(startPosition, Orientation.WEST);
		mower.linkTo(field);
		Command.execute(mower, Command.FORWARD);
		assertThat(mower.getPosition()).isEqualTo(startPosition);
	}

	public void test_one_step_forward_to_a_non_empty_area() {
		Field field = new Field(3, 3);
		Mower movableMower = new Mower(new Position(0, 0), Orientation.NORTH);
		Mower fixedMower = new Mower(new Position(0, 1), Orientation.WEST);
		movableMower.linkTo(field);
		fixedMower.linkTo(field);

		Command.execute(movableMower, Command.FORWARD);
		PositionAssert.assertThat(movableMower.getPosition()).hasX(0);
		PositionAssert.assertThat(movableMower.getPosition()).hasY(0);
	}

}
