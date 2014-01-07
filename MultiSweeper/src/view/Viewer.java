package view;

import game.Sweeper;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import data.DataManager;

@SuppressWarnings("serial")
public class Viewer extends JFrame
{
	private final Sweeper	mSweeper;
	
	private int				mWidth, mHeight;
	
	private BufferedImage	mImage;
	
	public Viewer(int aSweeperId)
	{
		DataManager.init();
		mSweeper = Sweeper.createSweeper(aSweeperId, this);
		init();
		start();
	}
	
	private void init()
	{
		mWidth = 800;
		mHeight = 600;
		
		Container cp = new Container()
		{
			@Override
			public void paint(Graphics g)
			{
				g.drawImage(mImage, 0, 0, null);
			}
			
			@Override
			public Dimension getPreferredSize()
			{
				return new Dimension(mWidth, mHeight);
			}
		};
		
		Adapter adapter = new Adapter(this);
		addComponentListener(adapter);
		addKeyListener(adapter);
		addWindowListener(adapter);
		cp.addMouseListener(adapter);
		cp.addMouseMotionListener(adapter);
		
		setContentPane(cp);
		pack();
		
		resize();
		setVisible(true);
	}
	
	void mouseClick(int aX, int aY, boolean aDown, int aButton)
	{
		mSweeper.mouseClick(aX, aY, aDown, aButton);
	}
	
	void mouseMove(int aX, int aY)
	{
		mSweeper.mouseMove(aX, aY);
	}
	
	void key(int aKey, boolean aDown)
	{
		mSweeper.key(aKey, aDown);
	}
	
	void stop()
	{
		mSweeper.stop();
	}
	
	public void stopWindow()
	{
		setVisible(false);
		System.exit(0);
	}
	
	void resize()
	{
		mWidth = getContentPane().getWidth();
		mHeight = getContentPane().getHeight();
		mImage = new BufferedImage(mWidth, mHeight, BufferedImage.TYPE_INT_ARGB);
		mSweeper.init(mImage);
	}
	
	private void start()
	{
		mSweeper.init(mImage);
		mSweeper.start();
	}
}
