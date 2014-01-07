package game.values.sets;

import game.values.TileValue;
import game.values.Tiles;

public class HexTiles extends Tiles
{
	@Override
	protected TileValue[] createTiles()
	{
		return new TileValue[] { new TileValue(0, 1, true, "hexTile0"), new TileValue(1, 2, false, "hexTile1"), new TileValue(2, 3, false, "hexTile2"), new TileValue(3, 4, false, "hexTile3"),
				new TileValue(4, 5, false, "hexTile4"), new TileValue(5, 6, false, "hexTile5"), new TileValue(6, 6, false, "hexTile6"), new TileValue(7, "hexTileBomb") };
	}
	
}
