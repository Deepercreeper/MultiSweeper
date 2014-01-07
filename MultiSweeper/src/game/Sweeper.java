package game;

import game.analyzer.Analyzer;
import game.field.Field;
import game.util.Tile;
import game.values.Tiles;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import view.Viewer;

public abstract class Sweeper
{
	private static Sweeper	SWEEPER;
	
	protected BufferedImage	mImage;
	
	protected int			mWidth	= 20, mHeight = 20, mBombs = 150, mWindowWidth, mWindowHeight;
	
	private final Viewer	mViewer;
	
	private final Analyzer	mAnalyzer;
	
	protected final Field	mField;
	
	private boolean			mRunning, mGenerate;
	
	public Sweeper(Viewer aViewer)
	{
		mViewer = aViewer;
		mField = createField();
		mAnalyzer = new Analyzer(mField);
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
			render(mImage.getGraphics());
			try
			{
				Thread.sleep(1);
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
		Tile.initWidth(mWidth);
		mField.reload(mWidth, mHeight);
		
		setBombs();
		
		mAnalyzer.analyze();
		
		if (mAnalyzer.getAreas().size() > 0)
		{
			mAnalyzer.openFirst();
			
			mGenerate = false;
		}
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
				for (Tile tile : mField.getBorderOf(x, y))
				{
					byte tileId = mField.getTile(tile);
					mField.setTile(tile, tiles.get(tileId).getNextId());
				}
			}
		}
	}
	
	protected abstract void stopSweeper();
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void mouseClick(int aX, int aY, boolean aDown, int aButton);
	
	public abstract void mouseMove(int aX, int aY);
	
	public abstract void key(int aKey, boolean aDown);
	
	protected abstract Field createField();
	
	public void init(BufferedImage aImage)
	{
		mImage = aImage;
		mWindowWidth = mImage.getWidth();
		mWindowHeight = mImage.getHeight();
	}
	
	public static Sweeper createSweeper(int aSweeperId, Viewer aViewer)
	{
		switch (aSweeperId)
		{
			case 0 :
				return new TestSweeper(aViewer);
			default :
				return null;
		}
	}
	
	public static void forceStop()
	{
		if (SWEEPER != null) SWEEPER.stop();
		else System.exit(0);
	}
}
