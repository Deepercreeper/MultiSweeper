package game.field;

import java.util.HashSet;
import log.Log;

public class Field
{
	private byte[][]	mTiles, mMasks;
	
	private int			mWidth, mHeight;
	
	byte getTile(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return -1;
		return mTiles[aX][aY];
	}
	
	byte getTile(Tile aTile)
	{
		return getTile(aTile.getX(), aTile.getY());
	}
	
	byte getMask(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return -1;
		return mMasks[aX][aY];
	}
	
	byte getMask(Tile aTile)
	{
		return getMask(aTile.getX(), aTile.getY());
	}
	
	TileValue getTileValue(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return null;
		return TileValue.get(mTiles[aX][aY]);
	}
	
	TileValue getTileValue(Tile aTile)
	{
		return getTileValue(aTile.getX(), aTile.getY());
	}
	
	TileValue getMaskValue(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return null;
		return TileValue.get(mMasks[aX][aY]);
	}
	
	TileValue getMaskValue(Tile aTile)
	{
		return getMaskValue(aTile.getX(), aTile.getY());
	}
	
	void setTile(int aX, int aY, byte aId)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight)
		{
			Log.log("Tried to set Tile: " + aX + " " + aY);
			return;
		}
		mTiles[aX][aY] = aId;
	}
	
	void setTile(Tile aTile, byte aId)
	{
		setTile(aTile.getX(), aTile.getY(), aId);
	}
	
	void setMask(int aX, int aY, byte aId)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight)
		{
			Log.log("Tried to set Mask: " + aX + " " + aY);
			return;
		}
		mMasks[aX][aY] = aId;
	}
	
	void setMask(Tile aTile, byte aId)
	{
		setMask(aTile.getX(), aTile.getY(), aId);
	}
	
	HashSet<Tile> getBorderOf(Tile aTile)
	{
		return getBorderOf(aTile.getX(), aTile.getY());
	}
	
	HashSet<Tile> getBorderOf(int aX, int aY)
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
	
	int getWidth()
	{
		return mWidth;
	}
	
	int getHeight()
	{
		return mHeight;
	}
	
	void reload(int aWidth, int aHeight)
	{
		mWidth = aWidth;
		mHeight = aHeight;
		mTiles = new byte[aWidth][aHeight];
		mMasks = new byte[aWidth][aHeight];
	}
	
}
