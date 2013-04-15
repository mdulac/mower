package fr.mdulac.mower.domain;

import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

@Test
public class PositionTest {

	@Test
	public void test() {
		Assertions.assertThat(new Position(0, 0).toString()).isEqualTo("[0;0]");
		Assertions.assertThat(new Position(-1, 20).toString()).isEqualTo("[-1;20]");
		Assertions.assertThat(new Position(13, 6).toString()).isEqualTo("[13;6]");
	}
}
