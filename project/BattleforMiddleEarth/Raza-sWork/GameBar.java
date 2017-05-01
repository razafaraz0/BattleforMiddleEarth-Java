package myPart;

import java.awt.Color;
import java.awt.Graphics;
public class GameBar {

	public static int health = 100;
	public void tick()
	{	
		
		health = Game.boundControl(health, 0, 100);
	}
	
	public void render(Graphics graphics)
	{
		graphics.setColor(Color.gray);
		graphics.fillRect(10, 10, 200, 32);  // 15 and 15 are postion on the screen
		graphics.setColor(Color.GREEN);
		graphics.fillRect(10, 10, health*2, 32);  // 15 and 15 are postion on the screen
		graphics.setColor(Color.white);
		graphics.drawRect(10, 10, 200, 32);  // 15 and 15 are postion on the screen
	}
	
	public void setHealth(int x)
	{
		GameBar.health = x;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	
}
