package game.field;

import game.util.Tile;
import game.values.Masks;
import game.values.Tiles;
import game.values.sets.HexMasks;
import game.values.sets.HexTiles;
import java.util.HashSet;

public class HexField extends Field
{
	@Override
	public HashSet<Integer> getBorderOf(int aX, int aY)
	{
		HashSet<Integer> tiles = new HashSet<>();
		if (getTile(aX - 1, aY) != -1) tiles.add(Tile.create(aX - 1, aY));
		if (getTile(aX + 1, aY) != -1) tiles.add(Tile.create(aX + 1, aY));
		
		if (getTile(aX, aY - 1) != -1) tiles.add(Tile.create(aX, aY - 1));
		if (getTile(aX, aY + 1) != -1) tiles.add(Tile.create(aX, aY + 1));
		
		if (aY % 2 == 0)
		{
			if (getTile(aX - 1, aY - 1) != -1) tiles.add(Tile.create(aX - 1, aY - 1));
			if (getTile(aX - 1, aY + 1) != -1) tiles.add(Tile.create(aX - 1, aY + 1));
		}
		else
		{
			if (getTile(aX + 1, aY - 1) != -1) tiles.add(Tile.create(aX + 1, aY - 1));
			if (getTile(aX + 1, aY + 1) != -1) tiles.add(Tile.create(aX + 1, aY + 1));
		}
		
		return tiles;
	}
	
	@Override
	protected Masks createMasks()
	{
		return new HexMasks();
	}
	
	@Override
	protected Tiles createTiles()
	{
		return new HexTiles();
	}
}
