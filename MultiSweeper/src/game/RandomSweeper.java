package game;

import game.field.Field;
import game.field.RandomField;
import game.renderer.Renderer;
import game.renderer.Square;
import view.Viewer;

public class RandomSweeper extends Sweeper
{
	public RandomSweeper(Viewer aViewer)
	{
		super(aViewer);
	}
	
	@Override
	protected Renderer createRenderer()
	{
		return new Square(mField);
	}
	
	@Override
	protected Field createField()
	{
		return new RandomField();
	}
}
