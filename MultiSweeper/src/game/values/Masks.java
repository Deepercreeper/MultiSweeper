package game.values;

import game.Sweeper;
import log.Log;

public abstract class Masks
{
	protected enum MaskState
	{
		NOTHING, OPEN, QUESTION, FLAG;
	}
	
	private final MaskValue[]	mMasks;
	
	private final MaskValue		mOpen, mNothing, mQuestion, mFlag;
	
	protected Masks()
	{
		mMasks = createMasks();
		mOpen = findOpen();
		mNothing = findNothing();
		mQuestion = findQuestion();
		mFlag = findFlag();
	}
	
	public MaskValue getFlag()
	{
		return mFlag;
	}
	
	public MaskValue getQuestion()
	{
		return mQuestion;
	}
	
	public MaskValue getOpen()
	{
		return mOpen;
	}
	
	public MaskValue getNothing()
	{
		return mNothing;
	}
	
	public MaskValue get(byte aId)
	{
		return mMasks[aId];
	}
	
	private MaskValue findFlag()
	{
		for (MaskValue mask : mMasks)
			if (mask.isFlag()) return mask;
		Log.exception("No Flag Mask Defined!");
		Sweeper.forceStop();
		return null;
	}
	
	private MaskValue findQuestion()
	{
		for (MaskValue mask : mMasks)
			if (mask.isQuestion()) return mask;
		Log.exception("No Question Mask Defined!");
		Sweeper.forceStop();
		return null;
	}
	
	private MaskValue findNothing()
	{
		for (MaskValue mask : mMasks)
			if (mask.isNothing()) return mask;
		Log.exception("No Nothing Mask Defined!");
		Sweeper.forceStop();
		return null;
	}
	
	private MaskValue findOpen()
	{
		for (MaskValue mask : mMasks)
			if (mask.isOpen()) return mask;
		Log.exception("No Open Mask Defined!");
		Sweeper.forceStop();
		return null;
	}
	
	/**
	 * @return the set of masks used inside the field.<br>
	 *         {@code MaskValue[id].getId() == id} has to be true.
	 */
	protected abstract MaskValue[] createMasks();
}
