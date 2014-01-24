package game.util;

public class Tile
{
	private static int	WIDTH;
	
	private Tile()
	{}
	
	public static void initWidth(int aWidth)
	{
		WIDTH = aWidth;
	}
	
	public static int create(int aX, int aY)
	{
		return aX + WIDTH * aY;
	}
	
	public static int getX(int aTile)
	{
		return aTile % WIDTH;
	}
	
	public static int getY(int aTile)
	{
		return (aTile - getX(aTile)) / WIDTH;
	}
}
