package game;

import game.analyzer.Analyzer;
import game.field.Field;
import game.util.Tile;
import game.values.Tiles;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
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
	
	protected int			mMouseX, mMouseY;
	
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
	
	public void mouseClick(int aX, int aY, boolean aDown, int aButton)
	{
		if ( !aDown)
		{
			final Tile tile = getSelectedTile(aX, aY);
			final int x = tile.getX(), y = tile.getY();
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
	
	protected abstract void renderField(Graphics g);
	
	protected abstract void renderSelected(Graphics g);
	
	protected abstract void leftClick(int aX, int aY);
	
	protected abstract void rightClick(int aX, int aY);
	
	public abstract void key(int aKey, boolean aDown);
	
	protected abstract Tile getSelectedTile(int aX, int aY);
	
	protected abstract Field createField();
	
	protected abstract void refreshSize();
	
	protected abstract void stopSweeper();
	
	public abstract void update();
}
