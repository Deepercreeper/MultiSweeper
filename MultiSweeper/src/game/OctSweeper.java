package game;

import game.field.Field;
import game.field.OctField;
import game.util.Tile;
import game.values.MaskValue;
import game.values.TileValue;
import java.awt.Graphics;
import view.Viewer;
import data.DataManager;

public class OctSweeper extends Sweeper
{
	private static final String	SELECTED_TILE	= "octMaskSelected";
	
	public OctSweeper(Viewer aViewer)
	{
		super(aViewer);
	}
	
	@Override
	protected void renderField(Graphics g)
	{
		int xPos, yPos;
		TileValue tile;
		MaskValue mask;
		
		for (int x = 0; x < mWidth; x++ )
			for (int y = 0; y < mHeight; y++ )
			{
				xPos = (int) (x * mTileWidth);
				yPos = (int) (y * mTileHeight);
				tile = mField.getTileValue(x, y);
				mask = mField.getMaskValue(x, y);
				g.drawImage(DataManager.getImage(mask.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
				if (mask.isOpen()) g.drawImage(DataManager.getImage(tile.getImage()), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
			}
	}
	
	@Override
	protected void renderSelected(Graphics g)
	{
		int xPos, yPos;
		final int mouse = getSelectedTile(mMouseX, mMouseY);
		final int mouseX = Tile.getX(mouse), mouseY = Tile.getY(mouse);
		if (mouseX >= 0 && mouseY >= 0 && mouseX < mWidth && mouseY < mHeight)
		{
			xPos = (int) (mouseX * mTileWidth);
			yPos = (int) (mouseY * mTileHeight);
			g.drawImage(DataManager.getImage(SELECTED_TILE), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
			for (int tile : mField.getBorderOf(mouseX, mouseY))
			{
				xPos = (int) (Tile.getX(tile) * mTileWidth);
				yPos = (int) (Tile.getY(tile) * mTileHeight);
				g.drawImage(DataManager.getImage(SELECTED_TILE), xPos, yPos, (int) mTileWidth + 1, (int) mTileHeight + 1, null);
			}
		}
	}
	
	@Override
	protected int getSelectedTile(int aX, int aY)
	{
		final int x = (int) (aX / mTileWidth), y = (int) (aY / mTileHeight);
		return Tile.create(x, y);
	}
	
	@Override
	protected Field createField()
	{
		return new OctField();
	}
	
	@Override
	protected void refreshSize()
	{
		mTileWidth = mWindowWidth / (float) mWidth;
		mTileHeight = mWindowHeight / (float) mHeight;
	}
}
