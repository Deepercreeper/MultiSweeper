package game.values.sets;

import game.values.MaskValue;
import game.values.Masks;

public class HexMasks extends Masks
{
	@Override
	protected MaskValue[] createMasks()
	{
		return new MaskValue[] { new MaskValue(0, true, false, "hexMaskOpen"), new MaskValue(1, false, true, "hexMaskNothing") };
	}
}
