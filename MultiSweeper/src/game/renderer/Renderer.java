package game.renderer;

import game.field.Field;
import java.awt.Graphics;

public abstract class Renderer
{
	protected final String	mSelectedTile;
	
	protected Field			mField;
	
	protected float			mTileWidth, mTileHeight;
	
	public Renderer(Field aField)
	{
		mField = aField;
		mSelectedTile = getSelectedTile();
	}
	
	protected abstract String getSelectedTile();
	
	public abstract void renderTile(int aX, int aY, Graphics g);
	
	public abstract void renderSelectedTile(int aX, int aY, Graphics g);
	
	public abstract int getSelectedTile(int aX, int aY);
	
	public abstract void refreshSize(int aWidth, int aHeight);
}
