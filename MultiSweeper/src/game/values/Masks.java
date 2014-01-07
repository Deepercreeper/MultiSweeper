package game.values;

import game.Sweeper;
import log.Log;

public abstract class Masks
{
	private final MaskValue[]	mMasks;
	
	private final MaskValue		mOpen, mNothing;
	
	protected Masks()
	{
		mMasks = createMasks();
		mOpen = findOpen();
		mNothing = findNothing();
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
