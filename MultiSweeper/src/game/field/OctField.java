package game.field;

import game.util.Tile;
import game.values.Masks;
import game.values.Tiles;
import game.values.sets.OctMasks;
import game.values.sets.OctTiles;
import java.util.HashSet;

public class OctField extends Field
{
	
	@Override
	public HashSet<Integer> getBorderOf(int aX, int aY)
	{
		HashSet<Integer> tiles = new HashSet<>();
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
	protected Tiles createTiles()
	{
		return new OctTiles();
	}
	
}
