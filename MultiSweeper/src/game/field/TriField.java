package game.field;

import game.util.Tile;
import game.values.Masks;
import game.values.Tiles;
import game.values.sets.TriMasks;
import game.values.sets.TriTiles;
import java.util.HashSet;

public class TriField extends Field
{
	
	@Override
	public HashSet<Integer> getBorderOf(int aX, int aY)
	{
		HashSet<Integer> tiles = new HashSet<>();
		if (getTile(aX - 1, aY) != -1) tiles.add(Tile.create(aX - 1, aY));
		if (getTile(aX + 1, aY) != -1) tiles.add(Tile.create(aX + 1, aY));
		
		final int y = (aY + aX) % 2 == 0 ? aY + 1 : aY - 1;
		
		if (getTile(aX, y) != -1) tiles.add(Tile.create(aX, y));
		
		return tiles;
	}
	
	@Override
	protected Masks createMasks()
	{
		return new TriMasks();
	}
	
	@Override
	protected Tiles createTiles()
	{
		return new TriTiles();
	}
	
}