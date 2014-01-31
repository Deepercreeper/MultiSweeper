package game.values.sets;

import game.values.TileValue;
import game.values.Tiles;

public class TriTiles extends Tiles
{
	@Override
	protected TileValue[] createTiles()
	{
		return new TileValue[] { new TileValue(0, 1, true, "triTile0"), new TileValue(1, 2, false, "triTile1"), new TileValue(2, 3, false, "triTile2"), new TileValue(3, 3, false, "triTile3"),
				new TileValue(4, "triTileBomb") };
	}
}
