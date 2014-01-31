package game;

import game.field.Field;
import game.field.HexField;
import game.renderer.Hexagon;
import game.renderer.Renderer;
import view.Viewer;

public class HexSweeper extends Sweeper
{
	public HexSweeper(Viewer aViewer)
	{
		super(aViewer);
	}
	
	@Override
	protected Field createField()
	{
		return new HexField();
	}
	
	@Override
	protected Renderer createRenderer()
	{
		return new Hexagon(mField);
	}
}
