package game.field;

import game.util.Tile;
import game.util.TileSet;
import game.values.Masks;
import game.values.sets.TriMasks;

public class DodecaField extends Field
{
	@Override
	public TileSet getBorderOf(int aX, int aY)
	{
		TileSet tiles = new TileSet(this);
		if (getTile(aX - 1, aY) != -1) tiles.add(Tile.create(aX - 1, aY));
		if (getTile(aX + 1, aY) != -1) tiles.add(Tile.create(aX + 1, aY));
		if (getTile(aX - 2, aY) != -1) tiles.add(Tile.create(aX - 2, aY));
		if (getTile(aX + 2, aY) != -1) tiles.add(Tile.create(aX + 2, aY));
		
		if (getTile(aX, aY - 1) != -1) tiles.add(Tile.create(aX, aY - 1));
		if (getTile(aX - 1, aY - 1) != -1) tiles.add(Tile.create(aX - 1, aY - 1));
		if (getTile(aX + 1, aY - 1) != -1) tiles.add(Tile.create(aX + 1, aY - 1));
		
		if (getTile(aX, aY + 1) != -1) tiles.add(Tile.create(aX, aY + 1));
		if (getTile(aX - 1, aY + 1) != -1) tiles.add(Tile.create(aX - 1, aY + 1));
		if (getTile(aX + 1, aY + 1) != -1) tiles.add(Tile.create(aX + 1, aY + 1));
		
		final int y = (aY + aX) % 2 == 0 ? aY + 1 : aY - 1;
		
		if (getTile(aX - 2, y) != -1) tiles.add(Tile.create(aX - 2, y));
		if (getTile(aX + 2, y) != -1) tiles.add(Tile.create(aX + 2, y));
		
		return tiles;
	}
	
	@Override
	protected Masks createMasks()
	{
		return new TriMasks();
	}
	
	@Override
	protected String getPrefix()
	{
		return "tri";
	}
	
	@Override
	protected int getTilesCount()
	{
		return 12;
	}
}
