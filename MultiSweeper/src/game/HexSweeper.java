package game;

import game.field.Field;
import game.field.HexField;
import game.util.Tile;
import game.values.MaskValue;
import game.values.TileValue;
import java.awt.Graphics;
import java.awt.Polygon;
import view.Viewer;
import data.DataManager;

public class HexSweeper extends Sweeper
{
	private static final float	HEX_CONST		= 1.35f;
	
	private static final String	SELECTED_TILE	= "hexMaskSelected";
	
	private float				mTileWidth, mTileHeight;
	
	public HexSweeper(Viewer aViewer)
	{
		super(aViewer);
	}
	
	@Override
	protected void stopSweeper()
	{
		// TODOV Auto-generated method stub
		
	}
	
	@Override
	public void update()
	{
		// TODOV Auto-generated method stub
	}
	
	@Override
	protected void renderField(Graphics g)
	{
		int xPos, yPos;
		
		for (int x = 0; x < mWidth; x++ )
		{
			for (int y = 0; y < mHeight; y++ )
			{
				xPos = (int) (x * mTileWidth + (y % 2) * mTileWidth / 2);
				yPos = (int) (y * mTileHeight);
				final TileValue tile = mField.getTileValue(x, y);
				final MaskValue mask = mField.getMaskValue(x, y);
				if (mask.isOpen()) g.drawImage(DataManager.getImage(tile.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) (mTileHeight * HEX_CONST), null);
				g.drawImage(DataManager.getImage(mask.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) (mTileHeight * HEX_CONST), null);
			}
		}
	}
	
	@Override
	protected void renderSelected(Graphics g)
	{
		int xPos, yPos;
		final Tile mouse = getSelectedTile(mMouseX, mMouseY);
		int mouseX = mouse.getX(), mouseY = mouse.getY();
		if (mouseX >= 0 && mouseY >= 0 && mouseX < mWidth && mouseY < mHeight)
		{
			xPos = (int) (mouseX * mTileWidth + (mouseY % 2) * mTileWidth / 2);
			yPos = (int) (mouseY * mTileHeight);
			g.drawImage(DataManager.getImage(SELECTED_TILE), xPos, yPos, (int) mTileWidth + 1, (int) (mTileHeight * HEX_CONST), null);
			for (Tile tile : mField.getBorderOf(mouse))
			{
				xPos = (int) (tile.getX() * mTileWidth + (tile.getY() % 2) * mTileWidth / 2);
				yPos = (int) (tile.getY() * mTileHeight);
				g.drawImage(DataManager.getImage(SELECTED_TILE), xPos, yPos, (int) mTileWidth + 1, (int) (mTileHeight * HEX_CONST), null);
			}
		}
	}
	
	@Override
	protected void leftClick(int aX, int aY)
	{	
		
	}
	
	@Override
	protected void rightClick(int aX, int aY)
	{	
		
	}
	
	@Override
	public void key(int aKey, boolean aDown)
	{
		// TODOV Auto-generated method stub
		
	}
	
	@Override
	protected Field createField()
	{
		return new HexField();
	}
	
	@Override
	protected Tile getSelectedTile(int aX, int aY)
	{
		
		int y = (int) (aY / mTileHeight);
		
		if (y % 2 == 1) aX -= mTileWidth / 2;
		int x = (int) (aX / mTileWidth);
		int xRest = (int) (aX - x * mTileWidth), yRest = (int) (aY - y * mTileHeight);
		if (new Polygon(new int[] { 0, (int) (mTileWidth / 2), 0 }, new int[] { 0, 0, (int) (mTileHeight / 4) }, 3).contains(xRest, yRest))
		{
			if (y % 2 == 0) x-- ;
			y-- ;
		}
		else if (new Polygon(new int[] { 0, (int) (mTileWidth / 2), 0 }, new int[] { 0, 0, (int) (mTileHeight / 4) }, 3).contains(mTileWidth - xRest, yRest))
		{
			if (y % 2 == 1) x++ ;
			y-- ;
		}
		return new Tile(x, y);
	}
	
	@Override
	protected void refreshSize()
	{
		mTileWidth = mWindowWidth / (mWidth + 0.5f);
		mTileHeight = mWindowHeight / mHeight;
	}
}
