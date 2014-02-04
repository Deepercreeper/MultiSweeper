package game.field;

import game.util.Tile;
import game.util.TileSet;
import game.values.Masks;
import game.values.sets.HexMasks;

public class HexField extends Field
{
	@Override
	public TileSet getBorderOf(int aX, int aY)
	{
		TileSet tiles = new TileSet(this);
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
	protected String getPrefix()
	{
		return "hex";
	}
	
	@Override
	protected int getTilesCount()
	{
		return 6;
	}
	
}
