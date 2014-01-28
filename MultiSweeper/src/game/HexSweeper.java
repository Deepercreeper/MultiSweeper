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
	
	public HexSweeper(Viewer aViewer)
	{
		super(aViewer);
	}
	
	@Override
	protected void renderTile(int aX, int aY, Graphics g)
	{
		final int xPos = (int) (aX * mTileWidth + (aY % 2) * mTileWidth / 2), yPos = (int) (aY * mTileHeight);
		final TileValue tile = mField.getTileValue(aX, aY);
		final MaskValue mask = mField.getMaskValue(aX, aY);
		g.drawImage(DataManager.getImage(mask.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) (mTileHeight * HEX_CONST), null);
		if (mask.isOpen()) g.drawImage(DataManager.getImage(tile.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) (mTileHeight * HEX_CONST), null);
	}
	
	@Override
	protected void renderSelected(Graphics g)
	{
		int xPos, yPos;
		final int mouse = getSelectedTile(mMouseX, mMouseY);
		final int mouseX = Tile.getX(mouse), mouseY = Tile.getY(mouse);
		if (mouseX >= 0 && mouseY >= 0 && mouseX < mWidth && mouseY < mHeight)
		{
			xPos = (int) (mouseX * mTileWidth + (mouseY % 2) * mTileWidth / 2);
			yPos = (int) (mouseY * mTileHeight);
			g.drawImage(DataManager.getImage(SELECTED_TILE), xPos, yPos, (int) mTileWidth + 1, (int) (mTileHeight * HEX_CONST), null);
			for (int tile : mField.getBorderOf(mouseX, mouseY))
			{
				xPos = (int) (Tile.getX(tile) * mTileWidth + (Tile.getY(tile) % 2) * mTileWidth / 2);
				yPos = (int) (Tile.getY(tile) * mTileHeight);
				g.drawImage(DataManager.getImage(SELECTED_TILE), xPos, yPos, (int) mTileWidth + 1, (int) (mTileHeight * HEX_CONST), null);
			}
		}
	}
	
	@Override
	protected Field createField()
	{
		return new HexField();
	}
	
	@Override
	protected int getSelectedTile(int aX, int aY)
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
		return Tile.create(x, y);
	}
	
	@Override
	protected void refreshSize()
	{
		mTileWidth = mWindowWidth / (mWidth + 0.5f);
		mTileHeight = mWindowHeight / mHeight;
	}
}
