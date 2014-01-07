package data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
	
	private static InputStream load(String aName)
	{
		try
		{
			return new FileInputStream(new File("data/" + aName + ".png"));
		}
		catch (FileNotFoundException e)
		{
			Log.exception("Could not find Image " + aName);
		}
		return null;
	}
}
