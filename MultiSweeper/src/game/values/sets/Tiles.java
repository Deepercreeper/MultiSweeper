package game.values.sets;

import game.Sweeper;
import game.values.TileValue;
import log.Log;

public abstract class Tiles
{
	private final TileValue[]	mTiles;
	
	private final TileValue		mBomb, mEmpty;
	
	protected Tiles()
	{
		mTiles = createTiles();
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
	
	/**
	 * @return the set of tiles used inside the field.<br>
	 *         {@code TileValue[id].getId() == id} has to be true.
	 */
	protected abstract TileValue[] createTiles();
}
