package Resources;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture
{
	public BufferedImage image;
	public String textureName = "";
	
	public int target = GL_TEXTURE_2D;
	public int textureID;
	public int width;
	public int height;
	
	public float U = 1f;
	public float V = 1f;

	public Texture(BufferedImage img, String textureName)
	{
		this.textureName = textureName;
		
		if(img == null)
		{
			width = 0;
			height = 0;
			
			image = null;
			
			U = 0;
			V = 0;
			
			textureID = TextureLoader.getNullTexture().textureID;
		}
		else
		{
			width = img.getWidth();
			height = img.getHeight();
			
			image = toFormalImage(img);
			
			if(width > height) U = (float)height / (float)width;
			if(width < height) V = (float)width / (float)height;
			
			initOpenGL();
		}
	}
	
	public BufferedImage toFormalImage(BufferedImage img)
	{
		BufferedImage buff = img;
		
		if(width < height) buff = new BufferedImage(height, height, img.getType());
		else buff = new BufferedImage(width, width, img.getType());
		
		Graphics g = buff.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		
		return buff;
	}
	
	public void initOpenGL()
	{
		glEnable(target);
		textureID = glGenTextures();

		bind();

		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		glTexParameteri(target, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(target, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(target, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(target, GL_TEXTURE_WRAP_T, GL_REPEAT);

		glTexImage2D(target, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, this.getImageBuffer());
	}
	
	public ByteBuffer getImageBuffer()
	{
		ByteBuffer buff = BufferUtils.createByteBuffer(4 * image.getWidth() * image.getHeight());
		
		for(int ix = 0; ix < image.getWidth(); ix++)
        {
        	for(int iy = 0; iy < image.getHeight(); iy++)
            {
        		Color c = new Color(image.getRGB(ix, iy), true);
        		buff.put((byte)c.getRed());
        		buff.put((byte)c.getGreen());
        		buff.put((byte)c.getBlue());
        		buff.put((byte)c.getAlpha());
            }
        }
		buff.flip();

		return buff;
	}

	public void bind()
	{
		glBindTexture(target, textureID);
	}
	
	
	@Override
	public String toString()
	{
		return super.toString() + "\n\ttextureName: " + this.textureName + "\n\ttextureID: " + this.textureID + "\n\twidth: " + this.width + "\n\theight: " + this.height + "\n\tU:" + U + "\n\tV:" + V;
	}
}


























