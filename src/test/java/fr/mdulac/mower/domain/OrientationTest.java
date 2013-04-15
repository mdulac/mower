package fr.mdulac.mower.domain;

import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

import fr.mdulac.mower.domain.Orientation;

@Test
public class OrientationTest {

	private static final String INCORRECT_VALUE = "A";

	@Test
	public void test_valid_orientations() {

		Assertions.assertThat(Orientation.values()).containsOnly(Orientation.EAST, Orientation.SOUTH, Orientation.WEST,
				Orientation.NORTH);

		Assertions.assertThat(Orientation.of("E")).isEqualTo(Orientation.EAST);
		Assertions.assertThat(Orientation.of("S")).isEqualTo(Orientation.SOUTH);
		Assertions.assertThat(Orientation.of("W")).isEqualTo(Orientation.WEST);
		Assertions.assertThat(Orientation.of("N")).isEqualTo(Orientation.NORTH);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = INCORRECT_VALUE
			+ " is not a valid Orientation value.")
	public void test_exception_when_invalid_orientations() {
		Orientation.of(INCORRECT_VALUE);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Orientation value must not be null.")
	public void test_exception_when_null_orientations() {
		Orientation.of(null);
	}

	@Test
	public void test_value_orientations() {
		Assertions.assertThat(Orientation.of("E").getCode()).isEqualTo("E");
		Assertions.assertThat(Orientation.of("S").getCode()).isEqualTo("S");
		Assertions.assertThat(Orientation.of("W").getCode()).isEqualTo("W");
		Assertions.assertThat(Orientation.of("N").getCode()).isEqualTo("N");
	}
}
