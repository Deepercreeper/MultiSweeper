package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import view.Viewer;

public abstract class Sweeper
{
	protected BufferedImage	mImage;
	
	protected int			mWindowWidth, mWindowHeight;
	
	private final Viewer	mViewer;
	
	private boolean			mRunning;
	
	public Sweeper(Viewer aViewer)
	{
		mViewer = aViewer;
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
		
	}
	
	public void stop()
	{
		mRunning = false;
		stopSweeper();
		mViewer.stopWindow();
	}
	
	protected abstract void stopSweeper();
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void mouseClick(int aX, int aY, boolean aDown, int aButton);
	
	public abstract void mouseMove(int aX, int aY);
	
	public abstract void key(int aKey, boolean aDown);
	
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
}
