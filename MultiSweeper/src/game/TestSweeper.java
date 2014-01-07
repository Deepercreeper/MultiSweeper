package game;

import game.field.Field;
import game.field.HexField;
import java.awt.Color;
import java.awt.Graphics;
import view.Viewer;
import data.DataManager;
import data.Name;

public class TestSweeper extends Sweeper
{
	private static final float	HEX_CONST	= 1.35f;
	
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
	public void render()
	{
		Graphics g = mImage.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, mWindowWidth, mWindowHeight);
		g.setColor(Color.black);
		final float tileWidth = mWindowWidth / (mWidth + 0.5f), tileHeight = mWindowHeight / mHeight;
		
		int xPos, yPos;
		for (int x = 0; x < mWidth; x++ )
			for (int y = 0; y < mHeight; y++ )
			{
				xPos = (int) (x * tileWidth + (y % 2) * tileWidth / 2);
				yPos = (int) (y * tileHeight);
				g.drawImage(DataManager.getImage(Name.HEX_TILE), xPos, yPos, (int) tileWidth, (int) (tileHeight * HEX_CONST), null);
				g.drawString(x + " " + y, (int) (xPos + tileWidth / 2), (int) (yPos + tileHeight * HEX_CONST / 2));
			}
		g.dispose();
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
