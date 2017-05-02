package myPart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossEnemy extends Enemy{

	
	
	public BossEnemy(int xCoordinate, int yCoordinate, GameIDs id) {
		super(xCoordinate, yCoordinate, id);
		xDirectedSpeed =6;
		yDirectedSpeed =12;
		// TODO Auto-generated constructor stub
	}

	public void tick(){

		xCoordinate += xDirectedSpeed;
		yCoordinate += yDirectedSpeed;

		//checks if the enemy doesnot go out of bound
		if(xCoordinate < 0 )  
		{
			//System.out.println("Less than 0 "+ xCoordinate);
			xDirectedSpeed = -xDirectedSpeed;
		}
		else if (xCoordinate >= Game.WIDTH)
		{
			//System.out.println("More than weight " +xCoordinate);
			xDirectedSpeed = -xDirectedSpeed;
			
			//meaning that after one complete round the players will go one step down
//			yCoordinate += 20;  
		}
		//channges the player yDirection so it doesnot go out of the screen
		else if( (yCoordinate <= 0 ) || (yCoordinate >= Game.HEIGHT) )
		{
			yDirectedSpeed = -yDirectedSpeed;
			//System.out.println("Within");
		}
		

	}

	public void  render(Graphics graphics)
	{
		graphics.setColor(Color.GREEN);
		graphics.fillRect(xCoordinate, yCoordinate, 64, 64);
	}
	public Rectangle getBounds(){
		return new Rectangle(xCoordinate,yCoordinate,64,64);
	}
	
	
}
