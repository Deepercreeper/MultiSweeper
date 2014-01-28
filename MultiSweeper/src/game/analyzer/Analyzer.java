package game.analyzer;

import game.Sweeper;
import game.field.Field;
import game.util.Tile;
import game.values.MaskValue;
import game.values.TileValue;
import java.util.HashSet;

public class Analyzer
{
	private final Sweeper					mSweeper;
	
	private final Field						mField;
	
	private final HashSet<HashSet<Integer>>	mAreas;
	
	public Analyzer(Field aField, Sweeper aSweeper)
	{
		mSweeper = aSweeper;
		mField = aField;
		mAreas = new HashSet<>();
	}
	
	public void analyze()
	{
		final int width = mField.getWidth(), height = mField.getHeight();
		mAreas.clear();
		HashSet<Integer> emptyTiles = new HashSet<>();
		for (int x = 0; x < width; x++ )
			for (int y = 0; y < height; y++ )
				if (mField.getTileValue(x, y).isEmpty()) emptyTiles.add(Tile.create(x, y));
		
		for (int tile : emptyTiles)
		{
			if (isInsideAreas(tile)) continue;
			addArea(tile);
		}
	}
	
	private void addArea(int aTile)
	{
		final byte emptyTile = mField.getTiles().getEmpty().getId();
		HashSet<Integer> area = new HashSet<>(), newBorders = new HashSet<>(), tempBorders = new HashSet<>();
		boolean changed;
		area.add(aTile);
		newBorders.add(aTile);
		do
		{
			changed = false;
			for (int tile : newBorders)
				for (int newTile : mField.getBorderOf(tile))
					if (mField.getTile(newTile) == emptyTile && !area.contains(newTile))
					{
						changed = true;
						area.add(newTile);
						tempBorders.add(newTile);
					}
			newBorders.clear();
			newBorders.addAll(tempBorders);
			tempBorders.clear();
		}
		while (changed);
		mAreas.add(area);
	}
	
	private boolean isInsideAreas(int aTile)
	{
		for (HashSet<Integer> area : mAreas)
			if (area.contains(aTile)) return true;
		return false;
	}
	
	private HashSet<Integer> getContainingArea(int aTile)
	{
		for (HashSet<Integer> area : mAreas)
			if (area.contains(aTile)) return area;
		return null;
	}
	
	public HashSet<HashSet<Integer>> getAreas()
	{
		HashSet<HashSet<Integer>> areas = new HashSet<>();
		areas.addAll(mAreas);
		return areas;
	}
	
	public boolean openFirst()
	{
		int i = (int) (Math.random() * mAreas.size());
		for (HashSet<Integer> area : mAreas)
			if (i-- == 0)
			{
				openArea(area);
				return true;
			}
		return false;
	}
	
	private void openArea(HashSet<Integer> aArea)
	{
		for (int tile : aArea)
			openAreaTile(tile);
	}
	
	private void openAreaTile(int aTile)
	{
		final byte openId = mField.getMasks().getOpen().getId();
		final int x = Tile.getX(aTile), y = Tile.getY(aTile);
		mField.setMask(x, y, openId);
		TileValue tileValue;
		for (int tile : mField.getBorderOf(x, y))
		{
			tileValue = mField.getTileValue(tile);
			if ( !tileValue.isEmpty() && !tileValue.isBomb()) mField.setMask(tile, openId);
		}
	}
	
	public void userOpenTile(int aX, int aY)
	{
		MaskValue mask = mField.getMaskValue(aX, aY);
		if (mask.isNothing())
		{
			TileValue tile = mField.getTileValue(aX, aY);
			openTile(aX, aY);
			if (tile.isBomb()) mSweeper.die(aX, aY);
			if (won()) mSweeper.win();
		}
	}
	
	private boolean won()
	{
		if (mSweeper.isDied()) return false;
		final byte open = mField.getMasks().getOpen().getId(), openBomb = mField.getMasks().getOpenBomb().getId(), bomb = mField.getTiles().getBomb().getId();
		for (int x = 0; x < mField.getWidth(); x++ )
			for (int y = 0; y < mField.getHeight(); y++ )
			{
				final byte mask = mField.getMask(x, y);
				if (mask != open && mask != openBomb && mField.getTile(x, y) != bomb) return false;
			}
		return true;
	}
	
	public int getBombs()
	{
		return mSweeper.getBombs();
	}
	
	public void openTile(int aX, int aY)
	{
		final byte openId = mField.getMasks().getOpen().getId(), openBombId = mField.getMasks().getOpenBomb().getId();
		if (mField.getTileValue(aX, aY).isEmpty() && isInsideAreas(Tile.create(aX, aY))) openArea(getContainingArea(Tile.create(aX, aY)));
		else
		{
			if (mField.getTileValue(aX, aY).isBomb()) mField.setMask(aX, aY, openBombId);
			else mField.setMask(aX, aY, openId);
		}
	}
}
