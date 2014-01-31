package game;

import game.field.Field;
import game.field.OctField;
import game.renderer.Renderer;
import game.renderer.Square;
import java.awt.Graphics;
import view.Viewer;

public class OctSweeper extends Sweeper
{
	public OctSweeper(Viewer aViewer)
	{
		super(aViewer);
	}
	
	@Override
	protected void renderTile(int aX, int aY, Graphics g)
	{
		mRenderer.renderTile(aX, aY, g);
	}
	
	@Override
	protected Field createField()
	{
		return new OctField();
	}
	
	@Override
	protected Renderer createRenderer()
	{
		return new Square(mField);
	}
}
