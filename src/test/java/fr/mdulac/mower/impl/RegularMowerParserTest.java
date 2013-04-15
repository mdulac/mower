package fr.mdulac.mower.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

import fr.mdulac.mower.exceptions.MowItNowParseException;
import fr.mdulac.mower.impl.RegularMowerParser;

/**
 * File parsing Unit Tests.
 */
@Test
public class RegularMowerParserTest {

	@Test(expectedExceptions = MowItNowParseException.class, expectedExceptionsMessageRegExp = "Cannot parse the reader content at line 1.")
	public void test_exceptions_when_field_coordinates_are_malformed() throws MowItNowParseException {
		String file = "-5 5\n1 2 N\nGAGAGAGAA";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new RegularMowerParser().parse(reader);
	}

	@Test(expectedExceptions = MowItNowParseException.class, expectedExceptionsMessageRegExp = "Cannot parse the reader content at line 2.")
	public void test_exceptions_when_mower_coordinates_are_malformed() throws MowItNowParseException {
		String file = "5 5\n-1 2 N\nGAGAGAGAA";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new RegularMowerParser().parse(reader);
	}

	@Test(expectedExceptions = MowItNowParseException.class, expectedExceptionsMessageRegExp = "Cannot parse the reader content at line 2.")
	public void test_exceptions_when_mower_orientation_is_malformed() throws MowItNowParseException {
		String file = "5 5\n1 2 U\nGAGAGAGAA";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new RegularMowerParser().parse(reader);
	}

	@Test(expectedExceptions = MowItNowParseException.class, expectedExceptionsMessageRegExp = "Cannot parse the reader content at line 3.")
	public void test_exceptions_when_mower_commands_are_malformed() throws MowItNowParseException {
		String file = "5 5\n1 2 N\nGAGAGVGAA";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new RegularMowerParser().parse(reader);
	}

	@Test(expectedExceptions = MowItNowParseException.class, expectedExceptionsMessageRegExp = "Cannot parse the reader content at line 5.")
	public void test_exceptions_when_mower_information_are_partial() throws MowItNowParseException {
		String file = "5 5\n1 2 N\nGAGAGAGAA\n3 3 E";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new RegularMowerParser().parse(reader);
	}

	@Test
	public void test_parse_regular_file() throws MowItNowParseException {
		String file = "5 5\n1 2 N\nGAGAGAGAA";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new RegularMowerParser().parse(reader);
	}

}
