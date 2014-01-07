package game.util;

public class Tile
{
	private static int	WIDTH;
	
	private final int	mX, mY;
	
	public static void initWidth(int aWidth)
	{
		WIDTH = aWidth;
	}
	
	public Tile(int aX, int aY)
	{
		mX = aX;
		mY = aY;
	}
	
	public int getX()
	{
		return mX;
	}
	
	public int getY()
	{
		return mY;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Tile)
		{
			Tile t = (Tile) o;
			return t.mX == mX && t.mY == mY;
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return mX + WIDTH * mY;
	}
	
	@Override
	public String toString()
	{
		return "(" + mX + "," + mY + ")";
	}
}
