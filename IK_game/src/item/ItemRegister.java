package item;

public class ItemRegister
{

	private static Item[] registeredItems = new Item[0];
	
	public static Item registerItem(Item item)
	{
		item.ItemUnlocalizedName = item.ItemUnlocalizedName.toLowerCase();
		
		if(getItemById(item.ItemId) != null) return null;
		if(getItemByUnlocalizedName(item.ItemUnlocalizedName) != null) return null;
		
		Item[] buff = new Item[registeredItems.length + 1];
		for(int i = 0; i < registeredItems.length; i++) buff[i] = registeredItems[i];
		buff[buff.length - 1] = item;
		registeredItems = buff;
		
		return item;
	}
	
	public static Item getItemById(int id)
	{
		for(int i = 0; i < registeredItems.length; i++)
		{
			if(registeredItems[i].ItemId == id) return registeredItems[i];
		}
		return null;
	}
	
	public static Item getItemByUnlocalizedName(String name)
	{
		for(int i = 0; i < registeredItems.length; i++)
		{
			if(registeredItems[i].ItemUnlocalizedName.equals(name)) return registeredItems[i];
		}
		return null;
	}
}
