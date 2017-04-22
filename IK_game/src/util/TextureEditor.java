package util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
}
