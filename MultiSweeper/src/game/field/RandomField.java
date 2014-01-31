package game.field;

import game.util.Tile;
import game.values.Masks;
import game.values.Tiles;
import game.values.sets.OctMasks;
import game.values.sets.OctTiles;
import java.util.HashMap;
import java.util.HashSet;
import log.Log;

public class RandomField extends Field
{
	private static final int				SIZE		= 8;
	
	private final HashMap<Integer, int[]>	mBorders	= new HashMap<>();
	
	@Override
	public void reload(int aWidth, int aHeight)
	{
		super.reload(aWidth, aHeight);
		resetBorders();
	}
	
	private void resetBorders()
	{
		HashSet<Integer> allTiles = new HashSet<>(), leftTiles = new HashSet<>(), doneTiles = new HashSet<>();
		for (int x = 0; x < getWidth(); x++ )
			for (int y = 0; y < getHeight(); y++ )
				allTiles.add(Tile.create(x, y));
		leftTiles.addAll(allTiles);
		for (int tile : allTiles)
		{
			addConnection(tile, getRandomOf(doneTiles));
			doneTiles.add(tile);
			leftTiles.remove(tile);
		}
		
		// TODO fill and save borders
	}
	
	private void addConnection(int aFirstTile, int aSecondTile)
	{	
		
	}
	
	private int getRandomOf(HashSet<Integer> aTiles)
	{
		int i = (int) (Math.random() * aTiles.size());
		for (int tile : aTiles)
			if (i-- <= 0) return tile;
		Log.exception("Could not find a random tile!");
		return -1;
	}
	
	@Override
	public HashSet<Integer> getBorderOf(int aX, int aY)
	{
		HashSet<Integer> tiles = new HashSet<>();
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
	protected Tiles createTiles()
	{
		return new OctTiles();
	}
	
}
