package fr.mdulac.mower.domain;

import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

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
	
	public void test_mower_has_north_orientation_by_default() {
		Mower mower = new Mower(new Position(0, 0));
		Assertions.assertThat(mower.getOrientation()).isEqualTo(Orientation.of("N"));
	}

}
