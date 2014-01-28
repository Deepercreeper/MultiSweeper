package game.values.sets;

import game.values.MaskValue;
import game.values.Masks;

public class OctMasks extends Masks
{
	@Override
	protected MaskValue[] createMasks()
	{
		return new MaskValue[] { new MaskValue(0, MaskState.OPEN, "octMaskOpen"), new MaskValue(1, MaskState.NOTHING, "octMaskNothing"), new MaskValue(2, MaskState.QUESTION, "octMaskQuestion"),
				new MaskValue(3, MaskState.FLAG, "octMaskFlag"), new MaskValue(4, MaskState.OPEN_BOMB, "octMaskOpenBomb") };
	}
}
