package game;

import game.field.Field;
import game.field.DodecaField;
import game.renderer.Renderer;
import game.renderer.Triangle;
import view.Viewer;

public class DodecaSweeper extends Sweeper
{
	public DodecaSweeper(Viewer aViewer)
	{
		super(aViewer);
	}
	
	@Override
	protected Field createField()
	{
		return new DodecaField();
	}
	
	@Override
	protected Renderer createRenderer()
	{
		return new Triangle(mField);
	}
}
