package block;

import Resources.Texture;
import Resources.TextureLoader;
import Window.MainWindow;
import world.BlockPos;
import world.World;

public class HugeBlock extends Block
{
	public int width = 1;
	public int height = 1;
	
	public HugeBlock(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);
		setBlockSummaring(false);
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
	
	@Override
	public void drawAtScreen(MainWindow g, int x, int y, int width, int height, World world, BlockPos pos)
	{
		x -= (this.width - 1) * width;
		y -= (this.height - 1) * height;
		
		width *= this.width;
		height *= this.height;
		
		String textureName = this.getTextureNameByMetaData(world, pos);
		Texture texture = TextureLoader.getTextureByName(textureName);
		g.drawTexture(texture, x, y, width, height);
	}
}
