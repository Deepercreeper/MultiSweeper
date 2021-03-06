package game.solver;

import game.analyzer.Analyzer;
import game.field.Field;
import game.util.Tile;
import game.util.TileSet;
import game.values.MaskValue;
import game.values.TileValue;
import java.util.HashMap;
import java.util.HashSet;
import log.Log;

public class Solver
{
	private static final int				MAX_NUMBER_SIZE	= 10;
	
	private static Starter					sStarter;
	
	private final Field						mField;
	
	private final Analyzer					mAnalyzer;
	
	private final HashMap<Integer, TileSet>	mNumbers;
	
	private final HashSet<Area>				mAreas;
	
	private int								mNumberId;
	
	private final HashSet<TileSolver>		mFinishedSolvers;
	
	private boolean							mSolving, mChanged, mDone;
	
	public Solver(Field aField, Analyzer aAnalyzer)
	{
		mDone = false;
		mField = aField;
		mAnalyzer = aAnalyzer;
		mFinishedSolvers = new HashSet<>();
		mNumbers = new HashMap<>();
		mAreas = new HashSet<>();
	}
	
	public void solve()
	{
		if ( !mSolving && sStarter == null)
		{
			sStarter = new Starter();
			sStarter.start();
		}
	}
	
	private void startSolve()
	{
		mSolving = true;
		while (mSolving)
		{
			mChanged = false;
			collectNumbers();
			if ( !mDone && mSolving) normalSolve();
			if ( !mDone && mSolving && !mChanged) areaSolve();
			if ( !mDone && mSolving && !mChanged) randomSolve();
			if (mDone || mSolving && !mChanged) break;
		}
		mSolving = false;
		if ( !mDone) openNothings();
		sStarter = null;
	}
	
	private void openNothings()
	{
		int flags = 0;
		final TileSet nothings = new TileSet(mField);
		final int size = nothings.size();
		for (int tile = 0; tile < size; tile++ )
		{
			final MaskValue mask = mField.getMaskValue(tile);
			if (mask.isFlag()) flags++ ;
			if (mask.isNothing()) nothings.add(tile);
		}
		if (mAnalyzer.getBombs() == flags) for (int tile : nothings)
			mAnalyzer.userOpenTile(Tile.getX(tile), Tile.getY(tile));
	}
	
	private void normalSolve()
	{
		Log.log("NormalSolve <Start>");
		mFinishedSolvers.clear();
		if ( !mNumbers.isEmpty())
		{
			for (int id : mNumbers.keySet())
				new TileSolver(id).start();
			while (mFinishedSolvers.size() < mNumberId + 1)
				try
				{
					Thread.sleep(1);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
		}
		Log.log("NormalSolve <End>");
	}
	
	private void areaSolve()
	{
		Log.log("AreaSolve <Start>");
		collectAreas(false);
		for (Area area : mAreas)
		{
			if (area.isToRemove()) continue;
			if (area.isEmpty())
			{
				openTiles(area.getTiles());
				mChanged = true;
			}
			else if (area.isFull())
			{
				markTiles(area.getTiles());
				mChanged = true;
			}
		}
		Log.log("AreaSolve <End>");
	}
	
	public void setDone(boolean aDone)
	{
		mDone = aDone;
	}
	
	public boolean isSolving()
	{
		return mSolving;
	}
	
	public void stopSolving()
	{
		mSolving = false;
	}
	
	private void openTiles(TileSet aTiles)
	{
		for (int tile : aTiles)
			mAnalyzer.userOpenTile(Tile.getX(tile), Tile.getY(tile));
	}
	
	private void markTiles(TileSet aTiles)
	{
		final byte flag = mField.getMasks().getFlag().getId();
		for (int tile : aTiles)
			mField.setMask(tile, flag);
	}
	
	private void collectAreas(boolean aProbability)
	{
		mAreas.clear();
		TileSet tiles = new TileSet(mField);
		for (TileSet areas : mNumbers.values())
			tiles.addAll(areas);
		for (int tile : tiles)
			mAreas.add(createArea(tile, aProbability));
		for (Area area : mAreas)
			area.compare(mAreas);
	}
	
	private Area createArea(int aTile, boolean aProbability)
	{
		int flags = 0;
		TileSet areaTiles = new TileSet(mField);
		for (int tile : mField.getBorderOf(aTile))
		{
			MaskValue mask = mField.getMaskValue(tile);
			if (mask.isFlag()) flags++ ;
			else if ( !mask.isOpen() && !mask.isQuestion()) areaTiles.add(tile);
		}
		Area area = new Area(mField, mField.getTile(aTile) - flags, aProbability);
		area.addAll(areaTiles);
		return area;
	}
	
	private void randomSolve()
	{
		Log.log("RandomSolve <Start>");
		collectAreas(true);
		if ( !mAreas.isEmpty())
		{
			double prop = 0;
			Area maxArea = null;
			for (Area area : mAreas)
				if ( !area.isToRemove() && (maxArea == null || area.getProbability() > prop))
				{
					maxArea = area;
					prop = maxArea.getProbability();
				}
			for (int tile : maxArea.getTiles())
			{
				final int x = Tile.getX(tile), y = Tile.getY(tile);
				Log.log("Opening: " + x + " " + y);
				mAnalyzer.userOpenTile(x, y);
				mChanged = true;
				break;
			}
		}
		Log.log("RandomSolve <End>");
	}
	
	private void collectNumbers()
	{
		mNumbers.clear();
		mNumberId = 0;
		TileValue value;
		final int size = mField.getWidth() * mField.getHeight();
		for (int tile = 0; tile < size; tile++ )
			if (mField.getMaskValue(tile).isOpen())
			{
				value = mField.getTileValue(tile);
				if ( !value.isBomb() && !value.isEmpty() && isBorder(tile)) addNumber(tile);
			}
	}
	
	private void addNumber(int aTile)
	{
		TileSet numbers = mNumbers.get(mNumberId);
		if (numbers == null)
		{
			numbers = new TileSet(mField);
			mNumbers.put(mNumberId, numbers);
		}
		if (numbers.size() < MAX_NUMBER_SIZE) numbers.add(aTile);
		else
		{
			mNumberId++ ;
			numbers = new TileSet(mField);
			numbers.add(aTile);
			mNumbers.put(mNumberId, numbers);
		}
	}
	
	private boolean isBorder(int aTile)
	{
		for (int tile : mField.getBorderOf(aTile))
			if (mField.getMaskValue(Tile.getX(tile), Tile.getY(tile)).isNothing()) return true;
		return false;
	}
	
	private class TileSolver extends Thread
	{
		private final int		mId;
		
		private final TileSet	mSolverNumbers;
		
		private TileSolver(int aId)
		{
			mId = aId;
			mSolverNumbers = mNumbers.get(mId);
		}
		
		private int getNothings(int aTile)
		{
			int nothings = 0;
			for (int tile : mField.getBorderOf(aTile))
				if (mField.getMaskValue(tile).isNothing()) nothings++ ;
			return nothings;
		}
		
		private int getFlags(int aTile)
		{
			int flags = 0;
			for (int tile : mField.getBorderOf(aTile))
				if (mField.getMaskValue(tile).isFlag()) flags++ ;
			return flags;
		}
		
		private void markBorders(int aTile)
		{
			final byte flag = mField.getMasks().getFlag().getId();
			for (int tile : mField.getBorderOf(aTile))
				if (mField.getMaskValue(tile).isNothing()) mField.setMask(tile, flag);
		}
		
		private void openBorders(int aTile)
		{
			for (int tile : mField.getBorderOf(aTile))
				if (mField.getMaskValue(tile).isNothing()) mAnalyzer.userOpenTile(Tile.getX(tile), Tile.getY(tile));
		}
		
		private boolean solveTile(int aTile)
		{
			final int nothings = getNothings(aTile), flags = getFlags(aTile), number = mField.getTileValue(aTile).getId();
			if (nothings == 0) return false;
			if (number - flags == nothings)
			{
				markBorders(aTile);
				return true;
			}
			else if (number - flags == 0)
			{
				openBorders(aTile);
				return true;
			}
			return false;
		}
		
		@Override
		public void run()
		{
			boolean changed = false;
			for (int tile : mSolverNumbers)
				changed |= solveTile(tile);
			mChanged |= changed;
			synchronized (mFinishedSolvers)
			{
				mFinishedSolvers.add(this);
			}
			Log.log("<" + mId + "-" + mSolverNumbers.size() + ">");
		}
	}
	
	private class Starter extends Thread
	{
		@Override
		public void run()
		{
			startSolve();
		}
	}
}
