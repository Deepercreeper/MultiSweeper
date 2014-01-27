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
	
	public Masks getMasks()
	{
		return mMaskSet;
	}
	
	public Tiles getTiles()
	{
		return mTileSet;
	}
	
	public byte getTile(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return -1;
		return mTiles[aX][aY];
	}
	
	public byte getTile(int aTile)
	{
		return getTile(Tile.getX(aTile), Tile.getY(aTile));
	}
	
	public byte getMask(int aTile)
	{
		return getMask(Tile.getX(aTile), Tile.getY(aTile));
	}
	
	public byte getMask(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return -1;
		return mMasks[aX][aY];
	}
	
	public TileValue getTileValue(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return null;
		return mTileSet.get(mTiles[aX][aY]);
	}
	
	public TileValue getTileValue(int aTile)
	{
		return getTileValue(Tile.getX(aTile), Tile.getY(aTile));
	}
	
	public MaskValue getMaskValue(int aX, int aY)
	{
		if (aX < 0 || aY < 0 || aX >= mWidth || aY >= mHeight) return null;
		return mMaskSet.get(mMasks[aX][aY]);
	}
	
	public MaskValue getMaskValue(int aTile)
	{
		return getMaskValue(Tile.getX(aTile), Tile.getY(aTile));
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
	
	public void setTile(int aTile, byte aId)
	{
		setTile(Tile.getX(aTile), Tile.getY(aTile), aId);
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
	
	public void setMask(int aTile, byte aId)
	{
		setMask(Tile.getX(aTile), Tile.getY(aTile), aId);
	}
	
	public HashSet<Integer> getBorderOf(int aTile)
	{
		return getBorderOf(Tile.getX(aTile), Tile.getY(aTile));
	}
	
	public abstract HashSet<Integer> getBorderOf(int aX, int aY);
	
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
		final byte empty = mTileSet.getEmpty().getId(), nothing = mMaskSet.getNothing().getId();
		mTiles = new byte[mWidth][mHeight];
		mMasks = new byte[mWidth][mHeight];
		for (int x = 0; x < mWidth; x++ )
			for (int y = 0; y < mHeight; y++ )
			{
				mTiles[x][y] = empty;
				mMasks[x][y] = nothing;
			}
	}
}
