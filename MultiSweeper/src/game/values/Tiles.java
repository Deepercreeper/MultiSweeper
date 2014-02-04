package game.values;

import game.Sweeper;
import log.Log;

public class Tiles
{
	private final TileValue[]	mTiles;
	
	private final TileValue		mBomb, mEmpty;
	
	protected Tiles(TileValue[] aTiles)
	{
		mTiles = aTiles;
		mBomb = findBomb();
		mEmpty = findEmpty();
	}
	
	private TileValue findEmpty()
	{
		for (TileValue tile : mTiles)
			if (tile.isEmpty()) return tile;
		Log.exception("No Empty Tile Defined!");
		Sweeper.forceStop();
		return null;
	}
	
	private TileValue findBomb()
	{
		for (TileValue tile : mTiles)
			if (tile.isBomb()) return tile;
		Log.exception("No Bomb Tile Defined!");
		Sweeper.forceStop();
		return null;
	}
	
	public TileValue getEmpty()
	{
		return mEmpty;
	}
	
	public TileValue getBomb()
	{
		return mBomb;
	}
	
	public TileValue get(byte aId)
	{
		return mTiles[aId];
	}
	
	public static Tiles create(String aPrefix, int aTilesCount)
	{
		TileValue[] tiles = new TileValue[aTilesCount + 2];
		for (int i = 0; i < aTilesCount; i++ )
			tiles[i] = new TileValue(i, i + 1, i == 0, aPrefix + "Tile" + i);
		tiles[aTilesCount] = new TileValue(aTilesCount, aTilesCount, false, aPrefix + "Tile" + (aTilesCount));
		tiles[aTilesCount + 1] = new TileValue(aTilesCount + 1, aPrefix + "TileBomb");
		return new Tiles(tiles);
	}
}
