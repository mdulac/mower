package fr.mdulac.mower.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import fr.mdulac.mower.domain.assertj.PositionAssert;

@Test
public class PositionTest {

	public void test_positions() {
		assertThat(new Position(0, 0).toString()).isEqualTo("[0;0]");
		assertThat(new Position(-1, 20).toString()).isEqualTo("[-1;20]");
		assertThat(new Position(13, 6).toString()).isEqualTo("[13;6]");
	}

	public void test_position_equality() {
		PositionAssert.assertThat(new Position(0, 0)).isEqualTo(new Position(0, 0));
		PositionAssert.assertThat(new Position(-1, 20)).isEqualTo(new Position(-1, 20));
		PositionAssert.assertThat(new Position(13, 6)).isEqualTo(new Position(13, 6));
	}
}
