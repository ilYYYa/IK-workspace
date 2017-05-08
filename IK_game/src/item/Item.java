package item;

public class Item
{
	public String ItemUnlocalizedName = "";
	public int ItemId = 0;
	
	private String textureName = "";
	
	public Item(int itemId, String itemUnlocalizedName)
	{
		this.ItemId = itemId;
		this.ItemUnlocalizedName = itemUnlocalizedName;
	}
	
	public static Item getItemByID(int id)
	{
		return ItemRegister.getItemById(id);
	}

	public static Item getItemByUnlocalizedName(String name)
	{
		return ItemRegister.getItemByUnlocalizedName(name);
	}
	
	public String getUnlocalizedName()
	{
		return this.ItemUnlocalizedName;
	}
	
	public int getItemID()
	{
		return this.ItemId;
	}
	
	public void setTexture(String texture)
	{
		this.textureName = texture;
	}
	public String getTexture()
	{
		return this.textureName;
	}
	
	@Override
	public String toString()
	{
		return "GlobalItem";
	}
}
