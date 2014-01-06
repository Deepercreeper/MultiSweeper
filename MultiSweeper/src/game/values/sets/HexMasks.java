package game.values.sets;

import game.values.MaskValue;

public class HexMasks extends Masks
{
	@Override
	protected MaskValue[] createMasks()
	{
		return new MaskValue[] { new MaskValue(0, true), new MaskValue(1, false) };
	}
}