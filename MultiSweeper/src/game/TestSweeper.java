package game;

import game.field.Field;
import game.field.HexField;
import java.awt.Graphics;
import view.Viewer;

public class TestSweeper extends Sweeper
{
	public TestSweeper(Viewer aViewer)
	{
		super(aViewer);
		System.out.println("Created");
	}
	
	@Override
	public void update()
	{
		System.out.println("Updating");
	}
	
	@Override
	public void render(Graphics g)
	{
		System.out.println("Rendering");
	}
	
	@Override
	protected void stopSweeper()
	{
		System.out.println("Stopped");
	}
	
	@Override
	public void mouseClick(int aX, int aY, boolean aDown, int aButton)
	{
		System.out.println("Mouse clicked: " + aX + " " + aY + " Down: " + aDown + " Button: " + aButton);
	}
	
	@Override
	public void mouseMove(int aX, int aY)
	{
		System.out.println("Mouse moved: " + aX + " " + aY);
	}
	
	@Override
	public void key(int aKey, boolean aDown)
	{
		System.out.println("Key Pressed: " + aKey + " Down: " + aDown);
	}
	
	@Override
	protected Field createField()
	{
		return new HexField();
	}
}
