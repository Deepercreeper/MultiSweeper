package game.field;

import game.util.Tile;
import game.util.TileSet;
import game.values.Masks;
import game.values.sets.OctMasks;

public class OctField extends Field
{
	@Override
	public TileSet getBorderOf(int aX, int aY)
	{
		TileSet tiles = new TileSet(this);
		for (int x = aX - 1; x <= aX + 1; x++ )
			for (int y = aY - 1; y <= aY + 1; y++ )
				if ((x != aX || y != aY) && getTile(x, y) != -1) tiles.add(Tile.create(x, y));
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
		return 8;
	}
}
