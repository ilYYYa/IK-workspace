package Resources;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SoundLoader
{
	public static ArrayList<String> fileNames = new ArrayList<String>();
	
	public static void initLoad() throws IOException
	{
		File f = new File("Resources\\Files\\Sounds");
		File[] files = f.listFiles();
		for(int i = 0; files!=null && i < files.length; i++)
		{
			fileNames.add(files[i].getName());
		}
	}
}
