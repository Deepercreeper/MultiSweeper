package game.field;

import game.util.Tile;
import game.values.Masks;
import game.values.Tiles;
import game.values.sets.TestMasks;
import game.values.sets.TestTiles;
import java.util.HashSet;

public class TestField extends Field
{
	@Override
	public HashSet<Tile> getBorderOf(int aX, int aY)
	{
		return new HashSet<>();
	}
	
	@Override
	protected Masks createMasks()
	{
		return new TestMasks();
	}
	
	@Override
	protected Tiles createTiles()
	{
		return new TestTiles();
	}
}
