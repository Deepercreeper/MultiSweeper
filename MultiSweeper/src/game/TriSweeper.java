package game;

import game.field.Field;
import game.field.TriField;
import game.renderer.Renderer;
import game.renderer.Triangle;
import view.Viewer;

public class TriSweeper extends Sweeper
{
	public TriSweeper(Viewer aViewer)
	{
		super(aViewer);
	}
	
	@Override
	protected Field createField()
	{
		return new TriField();
	}
	
	@Override
	protected Renderer createRenderer()
	{
		return new Triangle(mField);
	}
}
