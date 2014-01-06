package view;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Adapter implements ComponentListener, MouseListener, MouseMotionListener, KeyListener, WindowListener
{
	private final Viewer	mViewer;
	
	public Adapter(Viewer aViewer)
	{
		mViewer = aViewer;
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		mViewer.mouseMove(e.getX(), e.getY());
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{}
	
	@Override
	public void mouseExited(MouseEvent e)
	{}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		mViewer.mouseClick(e.getX(), e.getY(), true, e.getButton());
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		mViewer.mouseClick(e.getX(), e.getY(), false, e.getButton());
	}
	
	@Override
	public void componentHidden(ComponentEvent e)
	{}
	
	@Override
	public void componentMoved(ComponentEvent e)
	{}
	
	@Override
	public void componentResized(ComponentEvent e)
	{
		mViewer.resize();
	}
	
	@Override
	public void componentShown(ComponentEvent e)
	{}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		mViewer.key(e.getKeyCode(), true);
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		mViewer.key(e.getKeyCode(), false);
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{}
	
	@Override
	public void windowActivated(WindowEvent e)
	{}
	
	@Override
	public void windowClosed(WindowEvent e)
	{}
	
	@Override
	public void windowClosing(WindowEvent e)
	{
		mViewer.stop();
	}
	
	@Override
	public void windowDeactivated(WindowEvent e)
	{}
	
	@Override
	public void windowDeiconified(WindowEvent e)
	{}
	
	@Override
	public void windowIconified(WindowEvent e)
	{}
	
	@Override
	public void windowOpened(WindowEvent e)
	{}
}
