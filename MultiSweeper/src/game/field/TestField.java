package game.field;

import game.util.Tile;
import java.util.HashSet;

public class TestField extends Field
{
	@Override
	public HashSet<Tile> getBorderOf(int aX, int aY)
	{
		return new HashSet<>();
	}
	
	@Override
	public void init()
	{	
		
	}
}
