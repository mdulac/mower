package fr.mdulac.mower.domain;

import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

import fr.mdulac.mower.api.Factory;
import fr.mdulac.mower.api.Grid;
import fr.mdulac.mower.domain.Position;

@Test
public class FieldTest {

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Horizontal grid size must be positive.")
	public void test_field_cannot_be_zero_horizontal_sized() {
		Factory.newField(0, 10);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Vertical grid size must be positive.")
	public void test_field_cannot_be_zero_vertical_sized() {
		Factory.newField(10, 0);
	}

	public void test_field_contains_positions() {
		Grid field = Factory.newField(2, 3);
		Assertions.assertThat(field.contains(new Position(0, 0))).isTrue();
		Assertions.assertThat(field.contains(new Position(0, 1))).isTrue();
		Assertions.assertThat(field.contains(new Position(0, 2))).isTrue();
		Assertions.assertThat(field.contains(new Position(1, 0))).isTrue();
		Assertions.assertThat(field.contains(new Position(1, 1))).isTrue();
		Assertions.assertThat(field.contains(new Position(1, 2))).isTrue();
	}

	public void test_field_does_not_contain_position() {
		Grid field = Factory.newField(2, 3);
		Assertions.assertThat(field.contains(new Position(2, 3))).isFalse();
		Assertions.assertThat(field.contains(new Position(4, 2))).isFalse();
		Assertions.assertThat(field.contains(new Position(-1, 0))).isFalse();
		Assertions.assertThat(field.contains(new Position(0, -1))).isFalse();
	}

}
