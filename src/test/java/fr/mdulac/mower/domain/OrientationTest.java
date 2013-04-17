package fr.mdulac.mower.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

@Test
public class OrientationTest {

	private static final String INCORRECT_VALUE = "A";

	public void test_valid_orientations() {

		assertThat(Orientation.values()).containsOnly(Orientation.EAST, Orientation.SOUTH, Orientation.WEST,
				Orientation.NORTH);

		assertThat(Orientation.of("E")).isEqualTo(Orientation.EAST);
		assertThat(Orientation.of("S")).isEqualTo(Orientation.SOUTH);
		assertThat(Orientation.of("W")).isEqualTo(Orientation.WEST);
		assertThat(Orientation.of("N")).isEqualTo(Orientation.NORTH);
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

	public void test_value_orientations() {
		assertThat(Orientation.of("E").getCode()).isEqualTo("E");
		assertThat(Orientation.of("S").getCode()).isEqualTo("S");
		assertThat(Orientation.of("W").getCode()).isEqualTo("W");
		assertThat(Orientation.of("N").getCode()).isEqualTo("N");
	}
}
