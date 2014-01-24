package game.values.sets;

import game.values.MaskValue;
import game.values.Masks;

public class HexMasks extends Masks
{
	@Override
	protected MaskValue[] createMasks()
	{
		return new MaskValue[] { new MaskValue(0, MaskState.OPEN, "hexMaskOpen"), new MaskValue(1, MaskState.NOTHING, "hexMaskNothing"), new MaskValue(2, MaskState.QUESTION, "hexMaskQuestion"),
				new MaskValue(3, MaskState.FLAG, "hexMaskFlag") };
	}
}
