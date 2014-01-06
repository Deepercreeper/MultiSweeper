package game.values;

public class MaskValue
{
	private final byte		mId;
	
	private final boolean	mIsOpen;
	
	public MaskValue(int aId, boolean aIsOpen)
	{
		mId = (byte) aId;
		mIsOpen = aIsOpen;
	}
	
	public byte getId()
	{
		return mId;
	}
	
	public boolean isOpen()
	{
		return mIsOpen;
	}
}
