package game;

import game.field.Field;
import game.field.TriField;
import game.util.Tile;
import game.values.MaskValue;
import game.values.TileValue;
import java.awt.Graphics;
import java.awt.Polygon;
import view.Viewer;
import data.DataManager;

public class TriSweeper extends Sweeper
{
	private static final String	SELECTED_TILE	= "triMaskSelected", REVERSE = "rev";
	
	public TriSweeper(Viewer aViewer)
	{
		super(aViewer);
	}
	
	@Override
	protected void renderTile(int aX, int aY, Graphics g)
	{
		final int xPos = (int) (aX * mTileWidth / 2), yPos = (int) (aY * mTileHeight);
		final TileValue tile = mField.getTileValue(aX, aY);
		final MaskValue mask = mField.getMaskValue(aX, aY);
		g.drawImage(DataManager.getImage(((aX + aY) % 2 == 1 ? REVERSE : "") + mask.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
		if (mask.isOpen()) g.drawImage(DataManager.getImage(((aX + aY) % 2 == 1 ? REVERSE : "") + tile.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
	}
	
	@Override
	protected void renderSelected(Graphics g)
	{
		int xPos, yPos;
		final int mouse = getSelectedTile(mMouseX, mMouseY);
		final int mouseX = Tile.getX(mouse), mouseY = Tile.getY(mouse);
		if (mouseX >= 0 && mouseY >= 0 && mouseX < mWidth && mouseY < mHeight)
		{
			int x, y;
			xPos = (int) (mouseX * mTileWidth / 2);
			yPos = (int) (mouseY * mTileHeight);
			g.drawImage(DataManager.getImage(((mouseX + mouseY) % 2 == 1 ? REVERSE : "") + SELECTED_TILE), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
			for (int tile : mField.getBorderOf(mouseX, mouseY))
			{
				x = Tile.getX(tile);
				y = Tile.getY(tile);
				xPos = (int) (x * mTileWidth / 2);
				yPos = (int) (y * mTileHeight);
				g.drawImage(DataManager.getImage(((x + y) % 2 == 1 ? REVERSE : "") + SELECTED_TILE), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
			}
		}
	}
	
	@Override
	protected int getSelectedTile(int aX, int aY)
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
	protected Field createField()
	{
		return new TriField();
	}
	
	@Override
	protected void refreshSize()
	{
		mTileWidth = 2 * mWindowWidth / (float) (mWidth + 1);
		mTileHeight = mWindowHeight / (float) mHeight;
	}
}
