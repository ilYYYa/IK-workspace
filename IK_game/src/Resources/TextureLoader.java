package Resources;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import util.TextureEditor;

public class TextureLoader
{
	public static ArrayList<String> fileNames = new ArrayList<String>();
	public static ArrayList<Image> textures = new ArrayList<Image>();
	private static BufferedImage nuller = null;
	
	public static void initLoad() throws IOException
	{
		File f = new File(TextureLoader.class.getResource("").getFile() + "/Files/Textures");
		
		loadTexturesFromFolder(f);
		
		/*File[] files = f.listFiles();
		
		for(int i = 0; files!=null && i < files.length; i++)
		{
			addImage(ImageIO.read(files[i]), files[i].getName().substring(0, files[i].getName().length() - 4));
		}*/
	}
	
	public static void loadTexturesFromFolder(File directory) throws IOException
	{
		File[] files = directory.listFiles();
		
		for(int i = 0; files!=null && i < files.length; i++)
		{
			if(files[i].isDirectory()) loadTexturesFromFolder(files[i]);
			else addImage(ImageIO.read(files[i]), files[i].getName().substring(0, files[i].getName().length() - 4));
		}
	}
	
	public static void addImage(Image img, String name)
	{
		fileNames.add(name);
		textures.add(img);
	}
	
	public static Image getTextureByName(String name)
	{
		if(!name.substring(0,1).equals("!")) return getTextureByNamePURE(name);
		else
		{
			Image buff = getTextureByNamePURE(name);
			if(buff == nuller)
			{
				switch(name.substring(1,4))
				{
					case "001": buff = func001(buff, name.substring(4)); break;
					case "002": buff = func002(buff, name.substring(4)); break;
				}

				if(buff != getNullTexture()) addImage(buff, name);
				
				return buff;
			}
			else return buff;
		}
	}
	
	public static Image func001(Image buff, String str)
	{
		String[] split = str.split("&");
		
		buff = TextureEditor.concatSummaringTexturesByMeta((BufferedImage)getTextureByNamePURE(split[0]), (BufferedImage)getTextureByNamePURE(split[1]), Integer.parseInt(split[2]));
		
		int a = (split.length - 1)/2;
		for(int i = 1; i < a; i++) buff = TextureEditor.concatSummaringTexturesByMeta((BufferedImage)buff, (BufferedImage)getTextureByNamePURE(split[i*2 + 1]), Integer.parseInt(split[i*2 + 2]));
		
		return buff;
	}
	
	public static Image func002(Image buff, String str)
	{
		String[] split = str.split("&");
		split[0] = split[0].toUpperCase();
		try
		{
			buff = TextureEditor.getImageWithDrawedStringViaFont(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
		}
		catch(NumberFormatException e)
		{
			System.err.println("at TextureLoader at func002");
			System.err.println("\tstr: " + str);
			System.err.println("\tthis function must be: <TEXTFORDRAW>&<INT WIDTH>&<INT HEIGHT>");
			System.exit(-33);
		}
		return buff;
	}
	
	public static Image getTextureByNamePURE(String name)
	{
		for(int i = 0; i < fileNames.size(); i++)
		{
			if(fileNames.get(i).equals(name))
			{
				return textures.get(i);
			}
		}
		return getNullTexture();
	}
	
	public static Image getNullTexture()
	{
		if(nuller == null)
		{
			nuller = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
			Graphics g = nuller.getGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, 50, 50);
			g.fillRect(50, 50, 50, 50);
			g.setColor(new Color(175, 35, 255));
			g.fillRect(0, 50, 50, 50);
			g.fillRect(50, 0, 50, 50);
			g.dispose();
		}
		
		return nuller;
	}
}






































