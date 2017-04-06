package Resources;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SettingLoader
{
	public static void initLoad() throws IOException
	{
		File f = new File("settings.save");
		if(!f.exists())
		{
			f.createNewFile();
			initSave();
		}
		else
		{
			DataInputStream in = new DataInputStream(new FileInputStream(f));
			
			Settings.fullScreen = in.readBoolean();
			Settings.widthScreen = in.readInt();
			Settings.heightScreen = in.readInt();
			
			Settings.soundMultiply = in.readDouble();
		}
	}
	
	public static void initSave()
	{
		try
		{
			File f = new File("settings.save");
			if(!f.exists()) f.createNewFile();
			
			DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
			
			out.writeBoolean(Settings.fullScreen);
			out.writeInt(Settings.widthScreen);
			out.writeInt(Settings.heightScreen);
			
			out.writeDouble(Settings.soundMultiply);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
