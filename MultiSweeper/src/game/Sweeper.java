package game;

import game.analyzer.Analyzer;
import game.field.Field;
import game.solver.Solver;
import game.util.Tile;
import game.values.MaskValue;
import game.values.Tiles;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import log.Log;
import view.Viewer;

public abstract class Sweeper
{
	private static Sweeper	SWEEPER;
	
	protected BufferedImage	mImage;
	
	private final int		mStartLives	= 5;
	
	protected int			mWidth		= 20, mHeight = 20, mBombs = (int) (mWidth * mHeight * 0.2), mLives = mStartLives, mWindowWidth, mWindowHeight, mMouseX, mMouseY;
	
	private final Viewer	mViewer;
	
	private final Analyzer	mAnalyzer;
	
	private final Solver	mSolver;
	
	protected final Field	mField;
	
	private boolean			mRunning, mGenerate, mDied;
	
	public Sweeper(Viewer aViewer)
	{
		mViewer = aViewer;
		mField = createField();
		mAnalyzer = new Analyzer(mField, this);
		mSolver = new Solver(mField, mAnalyzer);
		SWEEPER = this;
	}
	
	public void start()
	{
		mRunning = mGenerate = true;
		while (mRunning)
		{
			while (mGenerate)
				generate();
			update();
			render();
			mViewer.repaint();
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		mViewer.stopWindow();
	}
	
	public void stop()
	{
		mRunning = false;
		stopSweeper();
	}
	
	private void generate()
	{
		mDied = false;
		mSolver.setDone(false);
		
		mLives = mStartLives;
		
		Tile.initWidth(mWidth);
		mField.reload(mWidth, mHeight);
		
		setBombs();
		
		mAnalyzer.analyze();
		
		if (mAnalyzer.openFirst()) mGenerate = false;
	}
	
	private void setBombs()
	{
		int x, y;
		final Random random = new Random();
		final Tiles tiles = mField.getTiles();
		final byte bombId = tiles.getBomb().getId();
		for (int i = 0; i < mBombs; i++ )
		{
			x = random.nextInt(mWidth);
			y = random.nextInt(mHeight);
			if (mField.getTileValue(x, y).isBomb()) i-- ;
			else
			{
				mField.setTile(x, y, bombId);
				for (int tile : mField.getBorderOf(x, y))
				{
					byte tileId = mField.getTile(tile);
					mField.setTile(tile, tiles.get(tileId).getNextId());
				}
			}
		}
	}
	
	public int getBombs()
	{
		return mBombs;
	}
	
	public void mouseClick(int aX, int aY, boolean aDown, int aButton)
	{
		if ( !aDown)
		{
			final int tile = getSelectedTile(aX, aY);
			final int x = Tile.getX(tile), y = Tile.getY(tile);
			if (x < 0 || y < 0 || x >= mWidth || y >= mHeight) return;
			if (aButton == MouseEvent.BUTTON1) leftClick(x, y);
			else if (aButton == MouseEvent.BUTTON3) rightClick(x, y);
		}
	}
	
	public void mouseMove(int aX, int aY)
	{
		mMouseX = aX;
		mMouseY = aY;
	}
	
	public void render()
	{
		Graphics g = mImage.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, mWindowWidth, mWindowHeight);
		
		renderField(g);
		
		renderSelected(g);
		
		g.dispose();
	}
	
	public void init(BufferedImage aImage)
	{
		mImage = aImage;
		mWindowWidth = mImage.getWidth();
		mWindowHeight = mImage.getHeight();
		refreshSize();
	}
	
	public static Sweeper createSweeper(int aSweeperId, Viewer aViewer)
	{
		switch (aSweeperId)
		{
			case 0 :
				return new HexSweeper(aViewer);
			default :
				return null;
		}
	}
	
	public static void forceStop()
	{
		if (SWEEPER != null) SWEEPER.stop();
		else System.exit(0);
	}
	
	public void markBomb(int aX, int aY)
	{
		MaskValue mask = mField.getMaskValue(aX, aY);
		if ( !mask.isOpen())
		{
			if (mask.isNothing()) mField.setMask(aX, aY, mField.getMasks().getFlag().getId());
			else if (mask.isFlag()) mField.setMask(aX, aY, mField.getMasks().getQuestion().getId());
			else mField.setMask(aX, aY, mField.getMasks().getNothing().getId());
		}
	}
	
	public void remarkBomb(int aX, int aY)
	{
		MaskValue mask = mField.getMaskValue(aX, aY);
		if ( !mask.isOpen()) mField.setMask(aX, aY, mField.getMasks().getNothing().getId());
	}
	
	public boolean isDied()
	{
		return mDied;
	}
	
	protected void leftClick(int aX, int aY)
	{
		mAnalyzer.userOpenTile(aX, aY);
	}
	
	protected void rightClick(int aX, int aY)
	{
		markBomb(aX, aY);
	}
	
	public void key(int aKey, boolean aDown)
	{
		if (aDown)
		{
			if (aKey == KeyEvent.VK_ESCAPE) stop();
		}
		else
		{
			if (aKey == KeyEvent.VK_S)
			{
				if (mSolver.isSolving()) mSolver.stopSolving();
				else mSolver.solve();
			}
			if (aKey == KeyEvent.VK_SPACE) mGenerate = true;
		}
	}
	
	public void win()
	{
		mSolver.setDone(true);
		Log.log("Win!");
	}
	
	public void die(int aX, int aY)
	{
		if (--mLives == 0)
		{
			mDied = true;
			mSolver.setDone(true);
			Log.log("Died!");
		}
	}
	
	protected abstract void renderField(Graphics g);
	
	protected abstract void renderSelected(Graphics g);
	
	protected abstract int getSelectedTile(int aX, int aY);
	
	protected abstract Field createField();
	
	protected abstract void refreshSize();
	
	protected abstract void stopSweeper();
	
	public abstract void update();
}
