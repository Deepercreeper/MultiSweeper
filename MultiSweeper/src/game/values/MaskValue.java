package game.values;

public class MaskValue
{
	private final byte	mId;
	
	private final boolean	mIsOpen, mIsNothing;
	
	private final String	mImage;
	
	public MaskValue(int aId, boolean aIsOpen, boolean aIsNothing, String aImage)
	{
		mId = (byte) aId;
		mIsOpen = aIsOpen;
		mIsNothing = aIsNothing;
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
	
	public boolean isNothing()
	{
		return mIsNothing;
	}
	
	public boolean isOpen()
	{
		return mIsOpen;
	}
}
