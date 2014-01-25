package game.solver;

import game.analyzer.Analyzer;
import game.field.Field;
import game.util.Tile;
import game.values.TileValue;
import java.util.HashMap;
import java.util.HashSet;
import log.Log;

public class Solver
{
	private static final int							MAX_NUMBER_SIZE	= 10;
	
	private final Field									mField;
	
	private final Analyzer								mAnalyzer;
	
	private final HashMap<Integer, HashSet<Integer>>	mNumbers;
	
	private int											mNumberId, mSolversDone;
	
	private boolean										mSolving, mChanged;
	
	public Solver(Field aField, Analyzer aAnalyzer)
	{
		mField = aField;
		mAnalyzer = aAnalyzer;
		mNumbers = new HashMap<>();
	}
	
	public void solve()
	{
		mSolving = true;
		while (mSolving)
		{
			collectNumbers();
			normalSolve();
			if ( !mChanged) areaSolve();
			if ( !mChanged) randomSolve();
			if ( !mChanged) mSolving = false;
		}
	}
	
	private void normalSolve()
	{
		for (int id : mNumbers.keySet())
			new NormalSolver(id).start();
		while (mSolversDone < mNumberId - 1)
		{
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private void areaSolve()
	{	
		
	}
	
	private void randomSolve()
	{	
		
	}
	
	private void collectNumbers()
	{
		mNumbers.clear();
		mNumberId = 0;
		TileValue tile;
		for (int x = 0; x < mField.getWidth(); x++ )
			for (int y = 0; y < mField.getHeight(); y++ )
				if (mField.getMaskValue(x, y).isOpen())
				{
					tile = mField.getTileValue(x, y);
					if ( !tile.isBomb() && !tile.isEmpty() && isBorder(x, y)) addNumber(Tile.create(x, y));
				}
	}
	
	private void addNumber(int aTile)
	{
		HashSet<Integer> numbers = mNumbers.get(mNumberId);
		if (numbers.size() < MAX_NUMBER_SIZE) numbers.add(aTile);
		else
		{
			mNumberId++ ;
			numbers = new HashSet<>();
			numbers.add(aTile);
			mNumbers.put(mNumberId, numbers);
		}
	}
	
	private boolean isBorder(int aX, int aY)
	{
		for (int tile : mField.getBorderOf(aX, aY))
			if (mField.getMaskValue(Tile.getX(tile), Tile.getY(tile)).isNothing()) return true;
		return false;
	}
	
	private class NormalSolver extends Thread
	{
		private final int				mId;
		
		private final HashSet<Integer>	mSolverNumbers;
		
		private NormalSolver(int aId)
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
				if (mField.getMaskValue(tile).isNothing()) mAnalyzer.openTile(Tile.getX(tile), Tile.getY(tile));
		}
		
		private boolean solveTile(int aTile)
		{
			int nothings = getNothings(aTile), flags = getFlags(aTile), number = mField.getTileValue(aTile).getId();
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
			mSolversDone++ ;
			Log.log("<" + mId + ">");
		}
	}
}
