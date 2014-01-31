package game;

import game.field.Field;
import game.field.NovemField;
import game.renderer.Renderer;
import game.renderer.Triangle;
import view.Viewer;

public class NovemSweeper extends Sweeper
{
	public NovemSweeper(Viewer aViewer)
	{
		super(aViewer);
	}
	
	@Override
	protected Renderer createRenderer()
	{
		return new Triangle(mField);
	}
	
	@Override
	protected Field createField()
	{
		return new NovemField();
	}
}
