package game.values;

public class TileValue
{
	private final byte	mId, mAddId;
	
	private final boolean	mIsEmpty, mIsBomb;
	
	private final String	mImage;
	
	/**
	 * Creates a new tile representing the bomb.
	 * 
	 * @param aId
	 *            the id for the bomb.
	 */
	public TileValue(int aId, String aImage)
	{
		mId = (byte) aId;
		mAddId = (byte) aId;
		mIsEmpty = false;
		mIsBomb = true;
		mImage = aImage;
	}
	
	/**
	 * Creates a new Tile that is the empty tile or a number tile.
	 * 
	 * @param aId
	 *            the id for this tile.
	 * @param aAddId
	 *            the id for the next higher tile.
	 * @param aIsEmpty
	 *            whether this is the empty tile or not.
	 */
	public TileValue(int aId, int aAddId, boolean aIsEmpty, String aImage)
	{
		mId = (byte) aId;
		mIsEmpty = aIsEmpty;
		mAddId = (byte) aAddId;
		mIsBomb = false;
		mImage = aImage;
	}
	
	public String getImage()
	{
		return mImage;
	}
	
	public byte getId()
	{
		return mId;
	}
	
	public byte getNextId()
	{
		return mAddId;
	}
	
	public boolean isEmpty()
	{
		return mIsEmpty;
	}
	
	public boolean isBomb()
	{
		return mIsBomb;
	}
}
