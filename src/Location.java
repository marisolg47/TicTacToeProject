
/**
 * 
 * This class represents one space on the grid, given by its row and column.
 * 
 */
public class Location 
{

	private int row;
	private int column;
	
	public Location( int r, int c )
	{
		row = r;
		column = c;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	
}
