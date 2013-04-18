package fr.mdulac.mower.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import fr.mdulac.mower.api.Factory;
import fr.mdulac.mower.api.PositionController;
import fr.mdulac.mower.domain.assertj.MowerAssert;

@Test
public class MowerTest {

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Position must not be null.")
	public void test_mower_cannot_have_null_position() {
		new Mower(null, Orientation.of("N"));
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Orientation must not be null.")
	public void test_mower_cannot_have_null_orientation() {
		new Mower(new Position(0, 0), null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Movement strategy must not be null.")
	public void test_mower_cannot_have_null_orientation_strategy() {
		new Mower(new Position(0, 0), Orientation.of("N"), null);
	}

	public void test_mower_equality() {
		MowerAssert.assertThat(Factory.newMower(Factory.newPosition(2, 3), Orientation.NORTH)).isEqualTo(
				Factory.newMower(Factory.newPosition(2, 3), Orientation.NORTH));
		MowerAssert.assertThat(Factory.newMower(Factory.newPosition(12, 5), Orientation.WEST)).isEqualTo(
				Factory.newMower(Factory.newPosition(12, 5), Orientation.WEST));
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Cannot link the mower to a null field.")
	public void test_mower_cannot_be_linked_to_null_controller() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.linkTo(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Cannot unlink the mower from a null field.")
	public void test_mower_cannot_be_unlink_from_null_controller() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.linkTo(Factory.newField(1, 1));
		mower.unlinkFrom(null);
	}

	@Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "This mower is already linked. You must unlink it before.")
	public void test_mower_cannot_be_link_two_times() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.linkTo(Factory.newField(1, 1));
		mower.linkTo(Factory.newField(1, 1));
	}

	@Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "This mower is not linked. You must link it before.")
	public void test_mower_cannot_be_unlink_if_not_link_before() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.unlinkFrom(Factory.newField(1, 1));
	}

	public void test_link_then_unlink_mower_from_position_controller() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		PositionController field = Factory.newField(1, 1);
		mower.linkTo(field);
		mower.unlinkFrom(field);
		assertThat(mower.getPositionController()).isNull();
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Target position must not be null.")
	public void test_cannot_change_position_to_null_position() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.changeMyPositionTo(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Target orientation must not be null.")
	public void test_cannot_change_orientation_to_null_orientation() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.changeMyOrientationTo(null);
	}

}
