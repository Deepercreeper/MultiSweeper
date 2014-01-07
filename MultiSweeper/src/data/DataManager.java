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
	
	public static void init()
	{
		for (Name name : Name.values())
			IMAGES.put(name.getName(), loadImage(name.getName()));
	}
	
	public static BufferedImage getImage(Name aName)
	{
		return IMAGES.get(aName.getName());
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
