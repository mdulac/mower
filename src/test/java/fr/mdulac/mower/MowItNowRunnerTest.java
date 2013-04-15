package fr.mdulac.mower;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

import fr.mdulac.mower.domain.Mower;
import fr.mdulac.mower.domain.Orientation;
import fr.mdulac.mower.domain.Position;
import fr.mdulac.mower.exceptions.MowItNowParseException;
import fr.mdulac.mower.impl.RegularMowerParser;

@Test
public class MowItNowRunnerTest {

	@Test
	public void test_simple_simulation() throws MowItNowParseException {
		String file = "5 5\n1 2 N\nGAGAGAGAA";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		MowItNowConfiguration configuration = new RegularMowerParser().parse(reader);

		List<Mower> result = MowItNowRunner.INSTANCE.runMowItNow(configuration);

		Assertions.assertThat(result).hasSize(1);
		Assertions.assertThat(result.get(0).getPosition()).isEqualTo(new Position(1, 3));
		Assertions.assertThat(result.get(0).getOrientation()).isEqualTo(Orientation.NORTH);
	}

	@Test
	public void test_simulation_when_mower_initial_position_is_outside_the_field() throws MowItNowParseException {
		String file = "5 5\n6 6 N\nGAGAGAGAA";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		MowItNowConfiguration configuration = new RegularMowerParser().parse(reader);

		List<Mower> result = MowItNowRunner.INSTANCE.runMowItNow(configuration);

		Assertions.assertThat(result).hasSize(0);
	}

	@Test
	public void test_simulation_when_mower_is_in_front_of_a_border() throws MowItNowParseException {
		String file = "5 5\n5 0 E\nAAAAAAAA";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		MowItNowConfiguration configuration = new RegularMowerParser().parse(reader);

		List<Mower> result = MowItNowRunner.INSTANCE.runMowItNow(configuration);

		Assertions.assertThat(result).hasSize(1);
		Assertions.assertThat(result.get(0).getPosition()).isEqualTo(new Position(5, 0));
		Assertions.assertThat(result.get(0).getOrientation()).isEqualTo(Orientation.EAST);
	}

	@Test
	public void test_simulation_when_mower_is_surrounded() throws MowItNowParseException {
		// The last mower is the only moving, but can't because of surrounding.
		// Check that its position does not change, but that its orientation
		// just move to left.
		String file = "2 2\n0 0 E\nG\n0 1 E\nG\n0 2 E\nG\n1 2 E\nG\n2 2 E\nG\n2 1 E\nG\n2 0 E\nG\n1 0 E\nG\n1 1 N\nAAAGAAA";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		MowItNowConfiguration configuration = new RegularMowerParser().parse(reader);

		List<Mower> result = MowItNowRunner.INSTANCE.runMowItNow(configuration);

		Assertions.assertThat(result).hasSize(9);
		Assertions.assertThat(result.get(8).getPosition()).isEqualTo(new Position(1, 1));
		Assertions.assertThat(result.get(8).getOrientation()).isEqualTo(Orientation.WEST);
	}

	@Test
	public void test_second_mower_starts_at_the_finish_position_of_first_mower() throws MowItNowParseException {
		// Mower 1 starts at (0,2) and finishes at (1,0).
		// Mower 2 starts at (1,0)
		// So mower 2 can't be integrated in the simulation !
		String file = "2 2\n0 2 N\nDADAA\n1 0 E\nAAAAA";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		MowItNowConfiguration configuration = new RegularMowerParser().parse(reader);

		List<Mower> result = MowItNowRunner.INSTANCE.runMowItNow(configuration);

		Assertions.assertThat(result).hasSize(1);
		Assertions.assertThat(result.get(0).getPosition()).isEqualTo(new Position(1, 0));
		Assertions.assertThat(result.get(0).getOrientation()).isEqualTo(Orientation.SOUTH);
	}

}
