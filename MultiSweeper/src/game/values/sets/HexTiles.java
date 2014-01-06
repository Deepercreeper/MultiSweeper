package game.values.sets;

import game.values.TileValue;

public class HexTiles extends Tiles
{
	@Override
	protected TileValue[] createTiles()
	{
		return new TileValue[] { new TileValue(0, 1, true), new TileValue(1, 2, false), new TileValue(2, 3, false), new TileValue(3, 4, false), new TileValue(4, 5, false), new TileValue(5, 6, false),
				new TileValue(6, 6, false), new TileValue(7) };
	}
	
}
