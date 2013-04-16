package fr.mdulac.mower.api;

import fr.mdulac.mower.domain.Field;
import fr.mdulac.mower.domain.Mower;
import fr.mdulac.mower.domain.Orientation;
import fr.mdulac.mower.domain.Position;
import fr.mdulac.mower.impl.MowerFileReaderService;
import fr.mdulac.mower.impl.RegularMowerParser;

/**
 * 
 * @author mdulac
 * @version 1.0
 * 
 *          Factory for all the domain objects.
 * 
 */
public final class Factory {

	private Factory() {
		// Can't use constructor.
	}

	/**
	 * Create a position.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @return The newly created position.
	 */
	public static Position newPosition(int x, int y) {
		return new Position(x, y);
	}

	/**
	 * Create a mower.
	 * 
	 * @param position
	 *            The initial position.
	 * @return The newly created mower.
	 */
	public static Mower newMower(Position position) {
		return newMower(position, Orientation.NORTH);
	}

	/**
	 * Create a mower.
	 * 
	 * @param position
	 *            The initial position.
	 * @param orientation
	 *            The initial orientation.
	 * @return The newly created mower.
	 */
	public static Mower newMower(Position position, Orientation orientation) {
		return new Mower(position, orientation);
	}

	/**
	 * Create a parser.
	 * 
	 * @return The newly created parser.
	 */
	public static MowerParser newMowerParser() {
		return new RegularMowerParser();
	}

	/**
	 * Create a file reader service.
	 * 
	 * @param fileName
	 *            The filename of the configuration file.
	 * @param parser
	 *            The parser you want to use.
	 * @return The newly created service.
	 */
	public static MowerReaderService newFileReaderService(String fileName, MowerParser parser) {
		return new MowerFileReaderService(fileName, parser);
	}

	/**
	 * Create a field.
	 * 
	 * @param horizontalSize
	 *            The horizontal size.
	 * @param verticalSize
	 *            The vertical size.
	 * @return The newly created field.
	 */
	public static Field newField(int horizontalSize, int verticalSize) {
		return new Field(horizontalSize, verticalSize);
	}

}
