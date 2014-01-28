package game.values.sets;

import game.values.TileValue;
import game.values.Tiles;

public class OctTiles extends Tiles
{
	@Override
	protected TileValue[] createTiles()
	{
		return new TileValue[] { new TileValue(0, 1, true, "octTile0"), new TileValue(1, 2, false, "octTile1"), new TileValue(2, 3, false, "octTile2"), new TileValue(3, 4, false, "octTile3"),
				new TileValue(4, 5, false, "octTile4"), new TileValue(5, 6, false, "octTile5"), new TileValue(6, 7, false, "octTile6"), new TileValue(7, 8, false, "octTile7"),
				new TileValue(8, 8, false, "octTile8"), new TileValue(9, "octTileBomb") };
	}
}
