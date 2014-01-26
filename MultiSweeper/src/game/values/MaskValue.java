package game.values;

import game.values.Masks.MaskState;

public class MaskValue
{
	private final byte		mId;
	
	private final MaskState	mState;
	
	private final String	mImage;
	
	public MaskValue(int aId, MaskState aState, String aImage)
	{
		mId = (byte) aId;
		mState = aState;
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
	
	public boolean isFlag()
	{
		return mState == MaskState.FLAG || mState == MaskState.OPEN_BOMB;
	}
	
	public boolean isNothing()
	{
		return mState == MaskState.NOTHING;
	}
	
	public boolean isQuestion()
	{
		return mState == MaskState.QUESTION;
	}
	
	public boolean isOpen()
	{
		return mState == MaskState.OPEN || mState == MaskState.OPEN_BOMB;
	}
}
