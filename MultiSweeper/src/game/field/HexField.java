package game.field;

import game.util.Tile;
import game.values.MaskValue;
import game.values.TileValue;
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
	public void init()
	{
		MaskValue.init();
		TileValue.init();
	}
}
