package game.solver;

import game.field.Field;
import game.util.TileSet;
import java.util.HashMap;
import java.util.HashSet;

public class Area
{
	private final Field						mField;
	
	private final HashMap<Integer, Boolean>	mTiles;
	
	private int								mBombs;
	
	private final boolean					mProbability;
	
	public Area(Field aField, int aBombs, boolean aProbability)
	{
		mField = aField;
		mProbability = aProbability;
		mTiles = new HashMap<>();
		mBombs = aBombs;
	}
	
	public void addAll(TileSet aTiles)
	{
		if (mProbability)
		{
			final byte nothing = mField.getMasks().getNothing().getId();
			for (int tile : aTiles)
				mTiles.put(tile, mField.getMask(tile) == nothing);
		}
		else for (int tile : aTiles)
			mTiles.put(tile, false);
	}
	
	public boolean isToRemove()
	{
		return mTiles.isEmpty();
	}
	
	public boolean isEmpty()
	{
		return mBombs == 0;
	}
	
	public boolean isFull()
	{
		return mBombs == mTiles.size();
	}
	
	public double getProbability()
	{
		int nothings = 0;
		for (int tile : mTiles.keySet())
			if (mTiles.get(tile)) nothings++ ;
		return nothings / (double) mBombs;
	}
	
	public int getBombs()
	{
		return mBombs;
	}
	
	public TileSet getTiles()
	{
		return new TileSet(mField, mTiles.keySet());
	}
	
	public void compare(HashSet<Area> aAreas)
	{
		for (Area area : aAreas)
		{
			if (area == this) continue;
			if (contains(area))
			{
				for (int tile : area.getTiles())
					mTiles.remove(tile);
				mBombs -= area.mBombs;
			}
		}
	}
	
	private boolean contains(Area aArea)
	{
		for (int tile : aArea.getTiles())
			if ( !mTiles.containsKey(tile)) return false;
		return true;
	}
}
