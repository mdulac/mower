package fr.mdulac.mower.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.testng.annotations.Test;

import fr.mdulac.mower.api.Factory;
import fr.mdulac.mower.api.Grid;
import fr.mdulac.mower.app.MowItNowConfiguration.MowerCommands;
import fr.mdulac.mower.domain.Command;
import fr.mdulac.mower.domain.Orientation;
import fr.mdulac.mower.domain.assertj.MowerAssert;
import fr.mdulac.mower.domain.assertj.PositionAssert;
import fr.mdulac.mower.exceptions.MowItNowParseException;
import fr.mdulac.mower.impl.DefaultMowerParser;

@Test
public class MowItNowConfigurationTest {

	private static final String REGULAR_CONFIGURATION = "5 5\n1 2 N\nGAGAGAGAA";

	public void test_field_properties_for_a_regular_configuration() throws MowItNowParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(
				REGULAR_CONFIGURATION.getBytes())));
		MowItNowConfiguration configuration = new DefaultMowerParser().parse(reader);

		Grid configuredField = configuration.getConfiguredField();
		PositionAssert.assertThat(configuredField.getTopRightPosition()).hasX(5);
		PositionAssert.assertThat(configuredField.getTopRightPosition()).hasY(5);
	}

	public void test_mowers_properties_for_a_regular_configuration() throws MowItNowParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(
				REGULAR_CONFIGURATION.getBytes())));
		MowItNowConfiguration configuration = new DefaultMowerParser().parse(reader);

		List<MowerCommands> configuredMowerCommands = configuration.getConfiguredMowerCommands();
		MowerCommands mowerCommands = configuredMowerCommands.get(0);
		assertThat(configuredMowerCommands).hasSize(1);
		MowerAssert.assertThat(mowerCommands.getMower()).hasPosition(Factory.newPosition(1, 2));
		MowerAssert.assertThat(mowerCommands.getMower()).hasOrientation(Orientation.of("N"));
	}

	public void test_commands_properties_for_a_regular_configuration() throws MowItNowParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(
				REGULAR_CONFIGURATION.getBytes())));
		MowItNowConfiguration configuration = new DefaultMowerParser().parse(reader);

		List<MowerCommands> configuredMowerCommands = configuration.getConfiguredMowerCommands();
		MowerCommands mowerCommands = configuredMowerCommands.get(0);
		assertThat(mowerCommands.getCommands()).hasSize(9);
		assertThat(mowerCommands.getCommands()).containsExactly(Command.of("G"), Command.of("A"), Command.of("G"),
				Command.of("A"), Command.of("G"), Command.of("A"), Command.of("G"), Command.of("A"), Command.of("A"));
	}
}
