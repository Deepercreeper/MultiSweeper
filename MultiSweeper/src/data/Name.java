package data;

public enum Name
{
	HEX_TILE("hexTile");
	
	private final String	mName;
	
	private Name(String aName)
	{
		mName = aName;
	}
	
	public String getName()
	{
		return mName;
	}
}
