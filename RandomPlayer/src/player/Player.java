package player;

import java.util.ArrayList;
import java.util.Random;

public class Player
{
	private static final char[]		letters	= new char[ ] {
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
											};
	
	private final ArrayList<String>	mTitles;
	
	private int						mSong;
	
	private boolean					mRunning;
	
	public static void main(String[] args)
	{
		new Player();
	}
	
	public Player()
	{
		mTitles = new ArrayList<>();
		fillTitles();
		start();
	}
	
	private void fillTitles()
	{
		Random random = new Random();
		for (int titles = random.nextInt(10) + 20; titles > 0; titles--)
			mTitles.add(randomTitle());
	}
	
	private String randomTitle()
	{
		StringBuilder string = new StringBuilder(
				""
						+ Character.toUpperCase(letters[(int) (Math.random() * letters.length)]));
		for (int i = 0; i < 10; i++)
			string.append(letters[(int) (Math.random() * letters.length)]);
		return string.toString();
	}
	
	private void nextSong()
	{
		mSong++;
		if (mSong >= mTitles.size()) mSong = 0;
	}
	
	private void start()
	{
		mRunning = true;
		mSong = 0;
		while (mRunning)
		{
			System.out.println(mTitles.get(mSong));
			nextSong();
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
