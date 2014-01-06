package game.field;

import game.util.Tile;
import game.values.MaskValue;
import game.values.Masks;
import game.values.TileValue;
import game.values.Tiles;
import java.util.HashSet;
import log.Log;

public abstract class Field
{
	private final Masks	mMaskSet;
	
	private final Tiles	mTileSet;
	
	private byte[][]	mTiles, mMasks;
	
	private int			mWidth, mHeight;
	
	public Field()
	{
		mMaskSet = createMasks();
		mTileSet = createTiles();
	}
	
	public byte getTile(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return -1;
		return mTiles[aX][aY];
	}
	
	public byte getTile(Tile aTile)
	{
		return getTile(aTile.getX(), aTile.getY());
	}
	
	public byte getMask(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return -1;
		return mMasks[aX][aY];
	}
	
	public byte getMask(Tile aTile)
	{
		return getMask(aTile.getX(), aTile.getY());
	}
	
	public TileValue getTileValue(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return null;
		return mTileSet.get(mTiles[aX][aY]);
	}
	
	public TileValue getTileValue(Tile aTile)
	{
		return getTileValue(aTile.getX(), aTile.getY());
	}
	
	public MaskValue getMaskValue(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return null;
		return mMaskSet.get(mMasks[aX][aY]);
	}
	
	public MaskValue getMaskValue(Tile aTile)
	{
		return getMaskValue(aTile.getX(), aTile.getY());
	}
	
	public void setTile(int aX, int aY, byte aId)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight)
		{
			Log.log("Tried to set Tile: " + aX + " " + aY);
			return;
		}
		mTiles[aX][aY] = aId;
	}
	
	public void setTile(Tile aTile, byte aId)
	{
		setTile(aTile.getX(), aTile.getY(), aId);
	}
	
	public void setMask(int aX, int aY, byte aId)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight)
		{
			Log.log("Tried to set Mask: " + aX + " " + aY);
			return;
		}
		mMasks[aX][aY] = aId;
	}
	
	public void setMask(Tile aTile, byte aId)
	{
		setMask(aTile.getX(), aTile.getY(), aId);
	}
	
	public HashSet<Tile> getBorderOf(Tile aTile)
	{
		return getBorderOf(aTile.getX(), aTile.getY());
	}
	
	public abstract HashSet<Tile> getBorderOf(int aX, int aY);
	
	protected abstract Masks createMasks();
	
	protected abstract Tiles createTiles();
	
	public int getWidth()
	{
		return mWidth;
	}
	
	public int getHeight()
	{
		return mHeight;
	}
	
	public void reload(int aWidth, int aHeight)
	{
		mWidth = aWidth;
		mHeight = aHeight;
		mTiles = new byte[aWidth][aHeight];
		mMasks = new byte[aWidth][aHeight];
	}
}
