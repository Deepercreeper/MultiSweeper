package game.field;

public enum MaskValue
{
	EMPTY(0, true);
	
	private final boolean	mIsEmpty;
	
	private final byte		mId;
	
	private MaskValue(int aId, boolean aIsEmpty)
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
	
	private static MaskValue[]	VALUES;
	
	static void init()
	{
		VALUES = new MaskValue[values().length];
		for (MaskValue value : values())
			VALUES[value.mId] = value;
	}
	
	static MaskValue get(byte aId)
	{
		return VALUES[aId];
	}
}
