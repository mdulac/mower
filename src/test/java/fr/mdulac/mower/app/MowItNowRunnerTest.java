package fr.mdulac.mower.app;

import static fr.mdulac.mower.api.Factory.newField;
import static fr.mdulac.mower.api.Factory.newMower;
import static fr.mdulac.mower.api.Factory.newPosition;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

import fr.mdulac.mower.app.MowItNowConfiguration;
import fr.mdulac.mower.app.MowItNowRunner;
import fr.mdulac.mower.app.MowItNowConfiguration.MowerCommands;
import fr.mdulac.mower.domain.Command;
import fr.mdulac.mower.domain.Mower;
import fr.mdulac.mower.domain.Orientation;
import fr.mdulac.mower.domain.Position;
import fr.mdulac.mower.exceptions.MowItNowParseException;

@Test
// In these tests, we only want to test the Runner, so configurations are
// mocked.
public class MowItNowRunnerTest {

	public void test_simple_simulation() throws MowItNowParseException {

		MowItNowConfiguration mockConfiguration = mock(MowItNowConfiguration.class);
		when(mockConfiguration.getConfiguredField()).thenReturn(newField(6, 6));
		when(mockConfiguration.getConfiguredMowerCommands()).thenReturn(
				Arrays.asList(new MowerCommands(newMower(newPosition(1, 2)), Arrays.asList(Command.LEFT,
						Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT,
						Command.FORWARD, Command.FORWARD))));

		List<Mower> result = MowItNowRunner.INSTANCE.runMowItNow(mockConfiguration);

		Assertions.assertThat(result).hasSize(1);
		Assertions.assertThat(result.get(0).getPosition()).isEqualTo(new Position(1, 3));
		Assertions.assertThat(result.get(0).getOrientation()).isEqualTo(Orientation.NORTH);
	}

	public void test_simulation_when_mower_initial_position_is_outside_the_field() throws MowItNowParseException {
		MowItNowConfiguration mockConfiguration = mock(MowItNowConfiguration.class);
		when(mockConfiguration.getConfiguredField()).thenReturn(newField(6, 6));
		when(mockConfiguration.getConfiguredMowerCommands()).thenReturn(
				Arrays.asList(new MowerCommands(newMower(newPosition(6, 6)), Arrays.asList(Command.LEFT,
						Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT,
						Command.FORWARD, Command.FORWARD))));

		List<Mower> result = MowItNowRunner.INSTANCE.runMowItNow(mockConfiguration);

		Assertions.assertThat(result).hasSize(0);
	}

	public void test_simulation_when_mower_is_in_front_of_a_border() throws MowItNowParseException {
		MowItNowConfiguration mockConfiguration = mock(MowItNowConfiguration.class);
		when(mockConfiguration.getConfiguredField()).thenReturn(newField(6, 6));
		when(mockConfiguration.getConfiguredMowerCommands()).thenReturn(
				Arrays.asList(new MowerCommands(newMower(newPosition(5, 0), Orientation.EAST), Arrays.asList(
						Command.FORWARD, Command.FORWARD, Command.FORWARD, Command.FORWARD, Command.FORWARD,
						Command.FORWARD, Command.FORWARD, Command.FORWARD, Command.FORWARD))));

		List<Mower> result = MowItNowRunner.INSTANCE.runMowItNow(mockConfiguration);

		Assertions.assertThat(result).hasSize(1);
		Assertions.assertThat(result.get(0).getPosition()).isEqualTo(new Position(5, 0));
		Assertions.assertThat(result.get(0).getOrientation()).isEqualTo(Orientation.EAST);
	}

	public void test_simulation_when_mower_is_surrounded() throws MowItNowParseException {
		// The last mower is the only moving, but can't because of surrounding.
		// Check that its position does not change, but that its orientation
		// just move to left.
		
		Position startPosition = newPosition(1, 1);
		
		MowItNowConfiguration mockConfiguration = mock(MowItNowConfiguration.class);
		when(mockConfiguration.getConfiguredField()).thenReturn(newField(3, 3));
		when(mockConfiguration.getConfiguredMowerCommands()).thenReturn(
				Arrays.asList(
						new MowerCommands(newMower(newPosition(0, 0)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(0, 1)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(0, 2)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(1, 2)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(2, 2)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(2, 1)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(2, 0)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(1, 0)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(startPosition), Arrays.asList(Command.FORWARD, Command.LEFT,
								Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT, Command.FORWARD))));

		List<Mower> result = MowItNowRunner.INSTANCE.runMowItNow(mockConfiguration);

		Assertions.assertThat(result).hasSize(9);
		Assertions.assertThat(result.get(8).getPosition()).isEqualTo(startPosition);
		Assertions.assertThat(result.get(8).getOrientation()).isEqualTo(Orientation.EAST);
	}

	public void test_second_mower_starts_at_the_finish_position_of_first_mower() throws MowItNowParseException {
		// Mower 1 starts at (0,2) and finishes at (1,0).
		// Mower 2 starts at (1,0)
		// So mower 2 can't be integrated in the simulation !
		MowItNowConfiguration mockConfiguration = mock(MowItNowConfiguration.class);
		when(mockConfiguration.getConfiguredField()).thenReturn(newField(3, 3));
		when(mockConfiguration.getConfiguredMowerCommands()).thenReturn(
				Arrays.asList(
						new MowerCommands(newMower(newPosition(0, 2)), Arrays.asList(Command.RIGHT, Command.FORWARD,
								Command.RIGHT, Command.FORWARD, Command.FORWARD)),
						new MowerCommands(newMower(newPosition(1, 0), Orientation.EAST), Arrays.asList(Command.FORWARD,
								Command.FORWARD, Command.FORWARD, Command.FORWARD, Command.FORWARD))));

		List<Mower> result = MowItNowRunner.INSTANCE.runMowItNow(mockConfiguration);

		Assertions.assertThat(result).hasSize(1);
		Assertions.assertThat(result.get(0).getPosition()).isEqualTo(newPosition(1, 0));
		Assertions.assertThat(result.get(0).getOrientation()).isEqualTo(Orientation.SOUTH);
	}

}
