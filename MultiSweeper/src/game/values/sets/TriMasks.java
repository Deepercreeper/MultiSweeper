package game.values.sets;

import game.values.MaskValue;
import game.values.Masks;

public class TriMasks extends Masks
{
	@Override
	protected MaskValue[] createMasks()
	{
		return new MaskValue[] { new MaskValue(0, MaskState.OPEN, "triMaskOpen"), new MaskValue(1, MaskState.NOTHING, "triMaskNothing"), new MaskValue(2, MaskState.QUESTION, "triMaskQuestion"),
				new MaskValue(3, MaskState.FLAG, "triMaskFlag"), new MaskValue(4, MaskState.OPEN_BOMB, "triMaskOpenBomb") };
	}
}
