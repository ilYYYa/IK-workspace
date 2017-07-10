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
	public static ArrayList<Texture> textures = new ArrayList<Texture>();
	private static Texture nuller = null;
	
	public static void initLoad() throws IOException
	{
		File f = new File(TextureLoader.class.getResource("").getFile() + "/Files/Textures");
		
		loadTexturesFromFolder(f);
	}
	
	public static void loadTexturesFromFolder(File directory) throws IOException
	{
		File[] files = directory.listFiles();
		
		for(int i = 0; files!=null && i < files.length; i++)
		{
			if(files[i].isDirectory()) loadTexturesFromFolder(files[i]);
			else addTexture(ImageIO.read(files[i]), files[i].getName().substring(0, files[i].getName().length() - 4));
		}
	}
	
	public static void addTexture(Image img, String name)
	{
		if(img == null)
		{
			//System.err.println(name + " texture can't loaded");
			return;
		}
		Texture t = new Texture((BufferedImage) img, name);
		textures.add(t);
	}
	
	public static void addTexture(Texture texture, String name)
	{
		texture.textureName = name;
		textures.add(texture);
	}
	
	public static Texture getTextureByName(String name)
	{
		if(!name.substring(0,1).equals("!")) return getTextureByNamePURE(name);
		else
		{
			Texture buff = getTextureByNamePURE(name);
			if(buff == nuller)
			{
				switch(name.substring(1,4))
				{
					case "001": buff = new Texture(func001(name.substring(4)), name); break;
					case "002": buff = new Texture(func002(name.substring(4)), name); break;
					case "003": buff = new Texture(func003(name.substring(4)), name); break;
					case "004": buff = new Texture(func004(name.substring(4)), name); break;
				}

				if(buff != getNullTexture()) addTexture(buff, name);
				
				return buff;
			}
			else return buff;
		}
	}

	public static Texture getTextureByNamePURE(String name)
	{
		int size = textures.size();
		for(int i = 0; i < size; i++)
		{
			if(textures.get(i).textureName.equals(name))
			{
				return textures.get(i);
			}
		}
		return getNullTexture();
	}
	
	public static BufferedImage func001(String str)
	{
		String[] split = str.split("&");
		
		BufferedImage buff = TextureEditor.concatSummaringTexturesByMeta(getTextureByNamePURE(split[0]).image, getTextureByNamePURE(split[1]).image, Integer.parseInt(split[2]));
		
		int a = (split.length - 1)/2;
		for(int i = 1; i < a; i++) buff = TextureEditor.concatSummaringTexturesByMeta((BufferedImage)buff, getTextureByNamePURE(split[i*2 + 1]).image, Integer.parseInt(split[i*2 + 2]));
		
		return buff;
	}
	
	public static BufferedImage func002(String str)
	{
		String[] split = str.split("&");
		split[0] = split[0].toUpperCase();
		BufferedImage buff = null;
		try
		{
			//buff = TextureEditor.getImageWithDrawedStringViaFont(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
			buff = TextureEditor.getCharViaImage(split[0].toCharArray()[0]);
		}
		catch(NumberFormatException e)
		{
			System.err.println("at TextureLoader at func002");
			System.err.println("\tstr: " + str);
			//System.err.println("\tthis function must be: <TEXTFORDRAW>&<INT WIDTH>&<INT HEIGHT>");
			System.err.println("\tthis function must be: <CHAR ch>");
			System.exit(-33);
		}
		return buff;
	}
	
	public static BufferedImage func003(String str)
	{
		String[] split = str.split("&");
		split[0] = split[0].toUpperCase();
		BufferedImage buff = null;
		try
		{
			buff = TextureEditor.getFilledRect(Integer.parseInt(split[0]), Integer.parseInt(split[1]), new Color(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Integer.parseInt(split[4]),Integer.parseInt(split[5])));
		}
		catch(NumberFormatException e)
		{
			System.err.println("at TextureLoader at func003");
			System.err.println("\tstr: " + str);
			System.err.println("\tthis function must be: <INT WIDTH>&<INT HEIGHT>&<INT R>&<INT G>&<INT B>&<INT A>");
			System.exit(-33);
		}
		return buff;
	}
	
	public static BufferedImage func004(String str)
	{
		String[] split = str.split("&");
		BufferedImage buff = null;
		try
		{
			buff = TextureEditor.createText(split[0], Integer.parseInt(split[1]));
		}
		catch(NumberFormatException e)
		{
			System.err.println("at TextureLoader at func004");
			System.err.println("\tstr: " + str);
			System.err.println("\tsplit[0]: " + split[0] + " split[1]: " + split[1]);
			System.err.println("\tthis function must be: <String STR>&<INT RGBColor>");
			System.exit(-33);
		}
		return buff;
	}
	
	public static Texture getNullTexture()
	{
		if(nuller == null)
		{
			BufferedImage nullImg = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
			Graphics g = nullImg.getGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, 50, 50);
			g.fillRect(50, 50, 50, 50);
			g.setColor(new Color(175, 35, 255));
			g.fillRect(0, 50, 50, 50);
			g.fillRect(50, 0, 50, 50);
			g.dispose();
			
			nuller = new Texture(nullImg, "nuller");
			nuller.textureName = "nuller";
		}
		
		return nuller;
	}

	public static void updateTexturesOpenGL()
	{
		int size = textures.size();
		for(int i = 0; i < size; i++)
		{
			textures.get(i).initOpenGL();
		}
	}
}






































