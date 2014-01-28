package game.values.sets;

import game.values.TileValue;
import game.values.Tiles;

public class TriTiles extends Tiles
{
	@Override
	protected TileValue[] createTiles()
	{
		return new TileValue[] { new TileValue(0, 1, true, "triTile0"), new TileValue(1, 2, false, "triTile1"), new TileValue(2, 3, false, "triTile2"), new TileValue(3, 4, false, "triTile3"),
				new TileValue(4, 5, false, "triTile4"), new TileValue(5, 6, false, "triTile5"), new TileValue(6, 7, false, "triTile6"), new TileValue(7, 8, false, "triTile7"),
				new TileValue(8, 9, false, "triTile8"), new TileValue(9, 10, false, "triTile9"), new TileValue(10, 11, false, "triTile10"), new TileValue(11, 12, false, "triTile11"),
				new TileValue(12, 12, false, "triTile12"), new TileValue(13, "triTileBomb") };
	}
}
