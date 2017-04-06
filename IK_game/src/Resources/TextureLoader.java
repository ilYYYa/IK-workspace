package Resources;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TextureLoader
{
	public static ArrayList<String> fileNames = new ArrayList<String>();
	public static ArrayList<Image> textures = new ArrayList<Image>();
	
	public static void initLoad() throws IOException
	{
		File f = new File("Resources\\Files");
		File[] files = f.listFiles();
		for(int i = 0; i < files.length; i++)
		{
			fileNames.add(files[i].getName());
			textures.add(ImageIO.read(files[i]));
		}
	}
	
	public static Image getTextureByName(String name)
	{
		for(int i = 0; i < fileNames.size(); i++)
		{
			if(fileNames.get(i).equals(name))
			{
				return textures.get(i);
			}
		}
		return null;
	}
}
