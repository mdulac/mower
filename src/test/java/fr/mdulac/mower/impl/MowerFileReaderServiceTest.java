package fr.mdulac.mower.impl;

import org.testng.annotations.Test;

import fr.mdulac.mower.impl.MowerFileReaderService;
import fr.mdulac.mower.impl.DefaultMowerParser;

@Test
public class MowerFileReaderServiceTest {

	public static final String VALID_FILENAME = "src/main/resources/mower.txt";
	public static final String BAD_FILENAME = "mower.txt";

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Filename argument must not be null.")
	public void test_exception_when_filename_is_null() {
		new MowerFileReaderService(null, new DefaultMowerParser());
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Parser argument must not be null.")
	public void test_exception_when_parser_is_null() {
		new MowerFileReaderService(VALID_FILENAME, null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "File " + BAD_FILENAME
			+ " cannot be opened.")
	public void test_exception_when_file_does_not_exists() {
		new MowerFileReaderService(BAD_FILENAME, new DefaultMowerParser());
	}

}
