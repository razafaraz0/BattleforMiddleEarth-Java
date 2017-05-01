package myPart;
import java.awt.Graphics;
import java.awt.Rectangle;
//import java.util.LinkedList;

public abstract class GameObject {
	protected int xCoordinate , yCoordinate;
	protected int xDirectedSpeed , yDirectedSpeed;
	protected GameIDs id;
	protected Collision collisionHandler;
	
	public GameObject(int x, int y , GameIDs id)
	{
		xCoordinate = x;
		yCoordinate = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics graphics);
	public abstract Rectangle getBounds();
	

	
	public void updateXCoordinate(int x)
	{
		this.xCoordinate = x;
	}
	public int getXCoordinate()
	{
		return xCoordinate;
	}
	
	
	public void updateYCoordinate(int y)
	{
		this.yCoordinate = y;
	}
	
	public int getYCoordinate()
	{
		return yCoordinate;
	}
	
	
	public void setID(GameIDs id)
	{
		this.id = id;
	}
	
	public GameIDs getID()
	{
		return id;
	}
	
	
	public void SetXDirectedSpeed(int x)
	{
		this.xDirectedSpeed = x;
	}
	public int getXDirectedSpeed()
	{
		return xDirectedSpeed;
	}
	
	public void SetYDirectedSpeed(int y)
	{
		this.yDirectedSpeed = y;
	}
	

	public int getYDirectedSpeed()
	{
		return yDirectedSpeed;
	}
	
}


