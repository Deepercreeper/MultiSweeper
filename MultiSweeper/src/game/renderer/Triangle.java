package game.renderer;

import game.field.Field;
import game.util.Tile;
import game.values.MaskValue;
import game.values.TileValue;
import java.awt.Graphics;
import java.awt.Polygon;
import data.DataManager;

public class Triangle extends Renderer
{
	private static String	REVERSE	= "rev";
	
	public Triangle(Field aField)
	{
		super(aField);
	}
	
	@Override
	protected String getSelectedTile()
	{
		return "triMaskSelected";
	}
	
	@Override
	public void renderTile(int aX, int aY, Graphics g)
	{
		final int xPos = (int) (aX * mTileWidth / 2), yPos = (int) (aY * mTileHeight);
		final TileValue tile = mField.getTileValue(aX, aY);
		final MaskValue mask = mField.getMaskValue(aX, aY);
		g.drawImage(DataManager.getImage(((aX + aY) % 2 == 1 ? REVERSE : "") + mask.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
		if (mask.isOpen()) g.drawImage(DataManager.getImage(((aX + aY) % 2 == 1 ? REVERSE : "") + tile.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
	}
	
	@Override
	public void renderSelectedTile(int aX, int aY, Graphics g)
	{
		final int xPos = (int) (aX * mTileWidth / 2);
		final int yPos = (int) (aY * mTileHeight);
		g.drawImage(DataManager.getImage(((aX + aY) % 2 == 1 ? REVERSE : "") + mSelectedTile), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
	}
	
	@Override
	public int getSelectedTile(int aX, int aY)
	{
		final int y = (int) (aY / mTileHeight);
		int x = (int) (aX * 2 / mTileWidth);
		
		Polygon triangle;
		if ((x + y) % 2 == 0) triangle = new Polygon(new int[] { 0, (int) mTileWidth / 2, 0 }, new int[] { 0, 0, (int) mTileHeight }, 3);
		else triangle = new Polygon(new int[] { 0, (int) mTileWidth / 2, 0 }, new int[] { 0, (int) mTileHeight, (int) mTileHeight }, 3);
		
		if (triangle.contains((int) (aX % (mTileWidth / 2)), (int) (aY % mTileHeight))) x-- ;
		if (x < 0 || y < 0 || x >= mField.getWidth() || y >= mField.getHeight()) return -1;
		return Tile.create(x, y);
	}
	
	@Override
	public void refreshSize(int aWidth, int aHeight)
	{
		mTileWidth = 2 * aWidth / (float) (mField.getWidth() + 1);
		mTileHeight = aHeight / (float) mField.getHeight();
	}
	
}
