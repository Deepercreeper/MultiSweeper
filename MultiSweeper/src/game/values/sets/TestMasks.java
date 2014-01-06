package game.values.sets;

import game.values.MaskValue;

public class TestMasks extends Masks
{
	@Override
	protected MaskValue[] createMasks()
	{
		return new MaskValue[] { new MaskValue(0, false), new MaskValue(1, true) };
	}
}
