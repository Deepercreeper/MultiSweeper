package game.field;

public class Tile
{
	private final int	mX, mY;
	
	Tile(int aX, int aY)
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
	public String toString()
	{
		return "(" + mX + "," + mY + ")";
	}
}
