package game.renderer;

import game.field.Field;
import game.util.Tile;
import game.values.MaskValue;
import game.values.TileValue;
import java.awt.Graphics;
import java.awt.Polygon;
import data.DataManager;

public class Hexagon extends Renderer
{
	private static final float	HEX_CONST	= 1.35f;
	
	public Hexagon(Field aField)
	{
		super(aField);
	}
	
	@Override
	protected String getSelectedTile()
	{
		return "hexMaskSelected";
	}
	
	@Override
	public void renderTile(int aX, int aY, Graphics g)
	{
		final int xPos = (int) (aX * mTileWidth + (aY % 2) * mTileWidth / 2), yPos = (int) (aY * mTileHeight);
		final TileValue tile = mField.getTileValue(aX, aY);
		final MaskValue mask = mField.getMaskValue(aX, aY);
		g.drawImage(DataManager.getImage(mask.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) (mTileHeight * HEX_CONST), null);
		if (mask.isOpen()) g.drawImage(DataManager.getImage(tile.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) (mTileHeight * HEX_CONST), null);
	}
	
	@Override
	public void renderSelectedTile(int aX, int aY, Graphics g)
	{
		final int xPos = (int) (aX * mTileWidth + (aY % 2) * mTileWidth / 2);
		final int yPos = (int) (aY * mTileHeight);
		g.drawImage(DataManager.getImage(mSelectedTile), xPos, yPos, (int) mTileWidth + 1, (int) (mTileHeight * HEX_CONST), null);
	}
	
	@Override
	public int getSelectedTile(int aX, int aY)
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
	public void refreshSize(int aWidth, int aHeight)
	{
		if (mField.getWidth() == 0 || mField.getHeight() == 0) return;
		mTileWidth = aWidth / (mField.getWidth() + 0.5f);
		mTileHeight = aHeight / mField.getHeight();
	}
}
