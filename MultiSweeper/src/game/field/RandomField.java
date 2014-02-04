package game.field;

import game.util.Tile;
import game.util.TileSet;
import game.values.Masks;
import game.values.sets.OctMasks;
import java.util.HashMap;
import log.Log;

public class RandomField extends Field
{
	private static final int				SIZE		= 5;
	
	private final HashMap<Integer, int[]>	mBorders	= new HashMap<>();
	
	private TileSet							mAllTiles;
	
	@Override
	public void reload(int aWidth, int aHeight)
	{
		super.reload(aWidth, aHeight);
		mAllTiles = new TileSet(this);
		resetBorders();
	}
	
	private void resetBorders()
	{
		mBorders.clear();
		final int size = getWidth() * getHeight();
		for (int tile = 0; tile < size; tile++ )
			mBorders.put(tile, createConnections());
		
		mAllTiles.addAll();
		mAllTiles.remove(0);
		
		{
			int current = 0;
			while (mAllTiles.size() > 0)
			{
				connectFirst(current, (current = randomOf(mAllTiles)));
				mAllTiles.remove(current);
			}
			connectFirst(current, 0);
		}
		
		mAllTiles.addAll();
		TileSet withoutTile = new TileSet(this);
		while (mAllTiles.size() > 1)
		{
			int first;
			boolean empty;
			do
			{
				empty = false;
				withoutTile.clear();
				withoutTile.addAll(mAllTiles);
				first = randomOf(mAllTiles);
				withoutTile.remove(first);
				for (int connected : mBorders.get(first))
					withoutTile.remove(connected);
				if (withoutTile.size() == 0)
				{
					empty = true;
					mAllTiles.remove(first);
				}
			}
			while (empty && mAllTiles.size() > 1);
			if (mAllTiles.size() <= 1) break;
			int second = randomOf(withoutTile);
			connect(first, second);
		}
	}
	
	private int[] createConnections()
	{
		int[] connections = new int[SIZE];
		for (int i = 0; i < SIZE; i++ )
			connections[i] = -1;
		return connections;
	}
	
	private void connect(int aFirst, int aSecond)
	{
		int[] first = mBorders.get(aFirst), second = mBorders.get(aSecond);
		int firstEmpty = getEmptyOf(first), secondEmpty = getEmptyOf(second);
		first[firstEmpty] = aSecond;
		second[secondEmpty] = aFirst;
		if (firstEmpty == SIZE - 1) mAllTiles.remove(aFirst);
		if (secondEmpty == SIZE - 1) mAllTiles.remove(aSecond);
	}
	
	private int getEmptyOf(int[] aConnections)
	{
		for (int i = 0; i < SIZE; i++ )
			if (aConnections[i] == -1) return i;
		Log.exception("Used a already full connection set!");
		return -1;
	}
	
	private void connectFirst(int aFirst, int aSecond)
	{
		int[] first = mBorders.get(aFirst), second = mBorders.get(aSecond);
		first[0] = aSecond;
		second[1] = aFirst;
	}
	
	private int randomOf(TileSet aTiles)
	{
		int i = (int) (Math.random() * aTiles.size());
		for (int tile : aTiles)
			if (i-- == 0) return tile;
		return -1;
	}
	
	@Override
	public TileSet getBorderOf(int aX, int aY)
	{
		TileSet tiles = new TileSet(this);
		for (int tile : mBorders.get(Tile.create(aX, aY)))
			tiles.add(tile);
		return tiles;
	}
	
	@Override
	protected Masks createMasks()
	{
		return new OctMasks();
	}
	
	@Override
	protected String getPrefix()
	{
		return "oct";
	}
	
	@Override
	protected int getTilesCount()
	{
		return SIZE;
	}
}
