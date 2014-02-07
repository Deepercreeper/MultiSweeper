package data;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;
import log.Log;

public class DataManager
{
	private static final HashMap<String, BufferedImage>	IMAGES	= new HashMap<>();
	
	private DataManager()
	{}
	
	public static BufferedImage getImage(String aName)
	{
		BufferedImage image = IMAGES.get(aName);
		if (image == null)
		{
			image = loadImage(aName);
			IMAGES.put(aName, image);
		}
		return image;
	}
	
	private static BufferedImage loadImage(String aName)
	{
		try
		{
			return ImageIO.read(load(aName));
		}
		catch (IOException e)
		{
			Log.exception("Could not read Image " + aName);
		}
		return null;
	}
	
	private static URL load(String aName)
	{
		return DataManager.class.getClass().getResource("/" + aName + ".png");
	}
}
