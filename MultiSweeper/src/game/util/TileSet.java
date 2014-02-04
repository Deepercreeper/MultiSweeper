package game.util;

import game.field.Field;
import java.util.Iterator;
import java.util.Set;

public class TileSet implements Iterable<Integer>
{
	private boolean[]	mData;
	
	private final int	mWidth, mHeight;
	
	public TileSet(Field aField, Set<Integer> aTiles)
	{
		this(aField);
		for (int tile : aTiles)
			mData[tile] = true;
	}
	
	public TileSet(Field aField)
	{
		mWidth = aField.getWidth();
		mHeight = aField.getHeight();
		mData = new boolean[mWidth * mHeight];
	}
	
	public void addAll(TileSet aTiles)
	{
		for (int tile = 0; tile < mWidth * mHeight; tile++ )
			if (aTiles.contains(tile)) mData[tile] = true;
	}
	
	public int size()
	{
		int i = 0;
		for (int tile = 0; tile < mWidth * mHeight; tile++ )
			if (mData[tile]) i++ ;
		return i;
	}
	
	public void addAll()
	{
		for (int i = 0; i < mWidth * mHeight; i++ )
			mData[i] = true;
	}
	
	public void clear()
	{
		mData = new boolean[mWidth * mHeight];
	}
	
	public boolean contains(int aTile)
	{
		return mData[aTile];
	}
	
	public void add(int aTile)
	{
		if (aTile < 0 || aTile >= mWidth * mHeight) return;
		mData[aTile] = true;
	}
	
	public void remove(int aTile)
	{
		if (aTile < 0 || aTile >= mWidth * mHeight) return;
		mData[aTile] = false;
	}
	
	@Override
	public Iterator<Integer> iterator()
	{
		return new Iterator<Integer>()
		{
			private int	mCurrent	= -1;
			
			@Override
			public boolean hasNext()
			{
				return findNext() != -1;
			}
			
			@Override
			public Integer next()
			{
				mCurrent = findNext();
				return mCurrent;
			}
			
			private int findNext()
			{
				int i = mCurrent;
				do
					i++ ;
				while (i < mWidth * mHeight && !mData[i]);
				if (i == mWidth * mHeight) return -1;
				return i;
			}
			
			@Override
			public void remove()
			{
				mData[mCurrent] = false;
			}
		};
	}
	
	@Override
	public String toString()
	{
		if (size() == 0) return "[]";
		String string = "[";
		boolean first = false;
		for (int tile : this)
		{
			string += (first ? ", " : "") + tile;
			first = true;
		}
		string += "]";
		return string;
	}
}
