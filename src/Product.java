public class Product
{
	// Private
	private int id;
	private String name;
	private int price;
	private int itemsCount;

	// Public
	public Product()
	{
		this.id = 0;
		this.name = null;
		this.price = 0;
		this.itemsCount = 0;
	}

	public Product(int id, String name, int price, int itemsCount)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.itemsCount = itemsCount;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public int getItemsCount()
	{
		return itemsCount;
	}

	public void setItemsCount(int itemsCount)
	{
		this.itemsCount = itemsCount;
	}

	@Override
	public String toString()
	{
		return id + "\t\t" + name + "\t\t" + price + "\t\t" + itemsCount;
	}

}
