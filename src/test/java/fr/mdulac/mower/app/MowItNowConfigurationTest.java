package fr.mdulac.mower.app;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

import fr.mdulac.mower.api.Grid;
import fr.mdulac.mower.app.MowItNowConfiguration;
import fr.mdulac.mower.app.MowItNowConfiguration.MowerCommands;
import fr.mdulac.mower.domain.Command;
import fr.mdulac.mower.domain.Orientation;
import fr.mdulac.mower.domain.Position;
import fr.mdulac.mower.exceptions.MowItNowParseException;
import fr.mdulac.mower.impl.RegularMowerParser;

@Test
public class MowItNowConfigurationTest {

	private static final String REGULAR_CONFIGURATION = "5 5\n1 2 N\nGAGAGAGAA";

	public void test_field_properties_for_a_regular_configuration() throws MowItNowParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(
				REGULAR_CONFIGURATION.getBytes())));
		MowItNowConfiguration configuration = new RegularMowerParser().parse(reader);

		Grid configuredField = configuration.getConfiguredField();
		Assertions.assertThat(configuredField.getTopRightPosition()).isEqualTo(new Position(5, 5));
	}

	public void test_mowers_properties_for_a_regular_configuration() throws MowItNowParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(
				REGULAR_CONFIGURATION.getBytes())));
		MowItNowConfiguration configuration = new RegularMowerParser().parse(reader);

		List<MowerCommands> configuredMowerCommands = configuration.getConfiguredMowerCommands();
		MowerCommands mowerCommands = configuredMowerCommands.get(0);
		Assertions.assertThat(configuredMowerCommands).hasSize(1);
		Assertions.assertThat(mowerCommands.getMower().getPosition()).isEqualTo(new Position(1, 2));
		Assertions.assertThat(mowerCommands.getMower().getOrientation()).isEqualTo(Orientation.of("N"));
	}

	public void test_commands_properties_for_a_regular_configuration() throws MowItNowParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(
				REGULAR_CONFIGURATION.getBytes())));
		MowItNowConfiguration configuration = new RegularMowerParser().parse(reader);

		List<MowerCommands> configuredMowerCommands = configuration.getConfiguredMowerCommands();
		MowerCommands mowerCommands = configuredMowerCommands.get(0);
		Assertions.assertThat(mowerCommands.getCommands()).hasSize(9);
		Assertions.assertThat(mowerCommands.getCommands()).containsExactly(Command.of("G"), Command.of("A"),
				Command.of("G"), Command.of("A"), Command.of("G"), Command.of("A"), Command.of("G"), Command.of("A"),
				Command.of("A"));
	}
}
