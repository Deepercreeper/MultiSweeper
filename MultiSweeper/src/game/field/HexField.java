package game.field;

import game.util.Tile;
import game.values.sets.HexMasks;
import game.values.sets.HexTiles;
import game.values.sets.Masks;
import game.values.sets.Tiles;
import java.util.HashSet;

public class HexField extends Field
{
	@Override
	public HashSet<Tile> getBorderOf(int aX, int aY)
	{
		HashSet<Tile> tiles = new HashSet<>();
		if (getTile(aX - 1, aY) != -1) tiles.add(new Tile(aX - 1, aY));
		if (getTile(aX + 1, aY) != -1) tiles.add(new Tile(aX + 1, aY));
		
		if (getTile(aX, aY - 1) != -1) tiles.add(new Tile(aX, aY - 1));
		if (getTile(aX, aY + 1) != -1) tiles.add(new Tile(aX, aY + 1));
		
		if (aY % 2 == 0)
		{
			if (getTile(aX - 1, aY - 1) != -1) tiles.add(new Tile(aX - 1, aY - 1));
			if (getTile(aX - 1, aY + 1) != -1) tiles.add(new Tile(aX - 1, aY + 1));
		}
		else
		{
			if (getTile(aX + 1, aY - 1) != -1) tiles.add(new Tile(aX + 1, aY - 1));
			if (getTile(aX + 1, aY + 1) != -1) tiles.add(new Tile(aX + 1, aY + 1));
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
