package game.analyzer;

import game.field.Field;
import game.util.Tile;
import java.util.HashSet;

public class Analyzer
{
	private final Field						mField;
	
	private final HashSet<HashSet<Tile>>	mAreas;
	
	public Analyzer(Field aField)
	{
		mField = aField;
		mAreas = new HashSet<>();
	}
	
	public void analyze()
	{
		final int width = mField.getWidth(), height = mField.getHeight();
		mAreas.clear();
		HashSet<Tile> emptyTiles = new HashSet<>();
		for (int x = 0; x < width; x++ )
			for (int y = 0; y < height; y++ )
				if (mField.getTileValue(x, y).isEmpty()) emptyTiles.add(new Tile(x, y));
		
		System.out.println("Empty tiles: " + emptyTiles.size());
		for (Tile tile : emptyTiles)
		{
			if (isInsideAreas(tile)) continue;
			addArea(tile);
		}
	}
	
	private void addArea(Tile aTile)
	{
		final byte emptyTile = mField.getTiles().getEmpty().getId();
		HashSet<Tile> area = new HashSet<>(), newBorders = new HashSet<>(), tempBorders = new HashSet<>();
		boolean changed;
		area.add(aTile);
		newBorders.add(aTile);
		do
		{
			changed = false;
			for (Tile tile : newBorders)
				for (Tile newTile : mField.getBorderOf(tile))
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
	
	private boolean isInsideAreas(Tile aTile)
	{
		for (HashSet<Tile> area : mAreas)
			if (area.contains(aTile)) return true;
		return false;
	}
	
	public void open(int aX, int aY)
	{
		final Tile tile = new Tile(aX, aY);
		for (HashSet<Tile> area : mAreas)
			if (area.contains(tile)) openArea(area);
	}
	
	public HashSet<HashSet<Tile>> getAreas()
	{
		HashSet<HashSet<Tile>> areas = new HashSet<>();
		areas.addAll(mAreas);
		return areas;
	}
	
	public void openFirst()
	{
		int i = (int) (Math.random() * mAreas.size());
		for (HashSet<Tile> area : mAreas)
			if (i-- == 0)
			{
				openArea(area);
				break;
			}
	}
	
	private void openArea(HashSet<Tile> aArea)
	{
		for (Tile tile : aArea)
			openTile(tile);
	}
	
	public void openTile(Tile aTile)
	{
		final byte openId = mField.getMasks().getOpen().getId();
		mField.setMask(aTile, openId);
		for (Tile tile : mField.getBorderOf(aTile))
			mField.setMask(tile, openId);
	}
}
