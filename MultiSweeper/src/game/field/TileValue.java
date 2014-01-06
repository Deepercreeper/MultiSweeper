package game.field;

public enum TileValue
{
	EMPTY(0, true);
	
	private final boolean	mIsEmpty;
	
	private final byte		mId;
	
	private TileValue(int aId, boolean aIsEmpty)
	{
		mId = (byte) aId;
		mIsEmpty = aIsEmpty;
	}
	
	public byte getId()
	{
		return mId;
	}
	
	public boolean isEmpty()
	{
		return mIsEmpty;
	}
	
	private static TileValue[]	VALUES;
	
	static void init()
	{
		VALUES = new TileValue[values().length];
		for (TileValue value : values())
			VALUES[value.mId] = value;
	}
	
	static TileValue get(byte aId)
	{
		return VALUES[aId];
	}
}
