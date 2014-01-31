package game.renderer;

import game.field.Field;
import game.util.Tile;
import game.values.MaskValue;
import game.values.TileValue;
import java.awt.Graphics;
import data.DataManager;

public class Square extends Renderer
{
	private float	mTileWidth, mTileHeight;
	
	public Square(Field aField)
	{
		super(aField);
	}
	
	@Override
	public void renderTile(int aX, int aY, Graphics g)
	{
		final int xPos = (int) (aX * mTileWidth), yPos = (int) (aY * mTileHeight);
		final TileValue tile = mField.getTileValue(aX, aY);
		final MaskValue mask = mField.getMaskValue(aX, aY);
		g.drawImage(DataManager.getImage(mask.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
		if (mask.isOpen()) g.drawImage(DataManager.getImage(tile.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
	}
	
	@Override
	public void renderSelectedTile(int aX, int aY, Graphics g)
	{
		g.drawImage(DataManager.getImage(mSelectedTile), (int) (aX * mTileWidth), (int) (aY * mTileHeight), (int) mTileWidth + 1, (int) mTileHeight + 1, null);
	}
	
	@Override
	public int getSelectedTile(int aX, int aY)
	{
		final int x = (int) (aX / mTileWidth), y = (int) (aY / mTileHeight);
		return Tile.create(x, y);
	}
	
	@Override
	public void refreshSize(int aWidth, int aHeight)
	{
		mTileWidth = aWidth / (float) mField.getWidth();
		mTileHeight = aHeight / (float) mField.getHeight();
	}
	
	@Override
	protected String getSelectedTile()
	{
		return "octMaskSelected";
	}
	
}
