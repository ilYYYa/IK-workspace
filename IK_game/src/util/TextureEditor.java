package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Resources.TextureLoader;

public class TextureEditor
{
	public static BufferedImage concatSummaringTexturesByMeta(BufferedImage img1, BufferedImage img2, int meta)
	{
		img1 = copyImage(img1);
		img2 = copyImage(img2);
		
		if(meta == 0) return img1;
		
		if((meta & 8) == 8) for(int i = 0; i < img1.getHeight(); i++) for(int j = 0; j < Math.random() * 4; j++) img1.setRGB(img1.getWidth() - j -1, i, img2.getRGB(img2.getWidth() - j - 1, i));
		if((meta & 4) == 4) for(int i = 0; i < img1.getHeight(); i++) for(int j = 0; j < Math.random() * 4; j++) img1.setRGB(j, i, img2.getRGB(j, i));
		if((meta & 2) == 2) for(int i = 0; i < img1.getWidth(); i++) for(int j = 0; j < Math.random() * 4; j++) img1.setRGB(i, img1.getWidth() - j - 1, img2.getRGB(i, img2.getWidth() - j - 1));
		if((meta & 1) == 1) for(int i = 0; i < img1.getWidth(); i++) for(int j = 0; j < Math.random() * 4; j++) img1.setRGB(i, j, img2.getRGB(i, j));
		
		return img1;
	}
	
	public static BufferedImage copyImage(BufferedImage source)
	{
	    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	
	public static final int space = 2;
	
	public static BufferedImage getImageWithDrawedStringViaFont(String str, int width, int height)
	{
		width = (width/height+1)*height;
		
		int w = (int)((double)height/16D*12D)+space;

	    BufferedImage b = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics g = b.getGraphics();
	    
	    char[] buff = str.toCharArray();
		
		for(int i = 0; i < buff.length; i++)
		{
			g.drawImage(getCharViaImage(buff[i]), i * w, 0, w-space, height, null);
		}

	    g.dispose();
	    return b;
	}
	
	public static int getStringWidthByHeight(String str, int height)
	{
		int w = (int)((double)height/16D*12D)+space;
		return str.length() * w;
	}
	
	public static BufferedImage getCharViaImage(char c)
	{
		String addSym = "" + c;
		
		if(c == '"') addSym = "34";
		if(c == '*') addSym = "42";
		if(c == '/') addSym = "47";
		if(c == ':') addSym = "58";
		if(c == '<') addSym = "60";
		if(c == '>') addSym = "62";
		if(c == '?') addSym = "63";
		if(c == '\\') addSym = "92";
		if(c == '|') addSym = "124";
		
		BufferedImage img = TextureLoader.getTextureByNamePURE("font_" + addSym).image;
		
		if(img == TextureLoader.getNullTexture().image && c != '?') img = (BufferedImage)TextureLoader.getTextureByNamePURE("font_63").image;
		
	    BufferedImage b = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(img, 0, 0, null);
	    g.dispose();
	    return b;
	}

	public static BufferedImage getFilledRect(int width, int height, Color color)
	{
		if(width <= 0 || height <= 0) return null;
		
		BufferedImage ret = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = ret.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.dispose();
		return ret;
	}
}





































