package game.values.sets;

import game.Sweeper;
import game.values.MaskValue;
import log.Log;

public abstract class Masks
{
	private final MaskValue[]	mMasks;
	
	private final MaskValue		mOpen;
	
	protected Masks()
	{
		mMasks = createMasks();
		mOpen = findOpen();
	}
	
	public MaskValue getOpen()
	{
		return mOpen;
	}
	
	public MaskValue get(byte aId)
	{
		return mMasks[aId];
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
