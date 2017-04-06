package Resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LanguageLoader
{
	public static ArrayList<String> fileNames = new ArrayList<String>();
	
	public static void initLoad() throws IOException
	{
		File f = new File("Resources\\Files\\Languages");
		File[] files = f.listFiles();
		for(int i = 0; files!=null && i < files.length; i++)
		{
			fileNames.add(files[i].getName());
		}
	}
}
