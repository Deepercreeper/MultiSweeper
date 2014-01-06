package game;

import game.field.Field;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import view.Viewer;

public abstract class Sweeper
{
	private static Sweeper	SWEEPER;
	
	protected BufferedImage	mImage;
	
	protected int			mWindowWidth, mWindowHeight;
	
	private final Viewer	mViewer;
	
	protected final Field	mField;
	
	private boolean			mRunning;
	
	public Sweeper(Viewer aViewer)
	{
		mViewer = aViewer;
		mField = createField();
		SWEEPER = this;
	}
	
	public void start()
	{
		mRunning = true;
		while (mRunning)
		{
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
