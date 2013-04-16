package fr.mdulac.mower.domain;

import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

@Test
public class PositionTest {

	public void test_positions() {
		Assertions.assertThat(new Position(0, 0).toString()).isEqualTo("[0;0]");
		Assertions.assertThat(new Position(-1, 20).toString()).isEqualTo("[-1;20]");
		Assertions.assertThat(new Position(13, 6).toString()).isEqualTo("[13;6]");
	}
}
