package myPart;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Collision {
	
	/**
	 * 
	 */
	
	
	public void collisionHandling(Player player,GameManager gameManager){

		for(int i = 0 ; i < gameManager.object.size(); i++)
		{
			GameObject tempObj = gameManager.object.get(i);
			
			if(tempObj.getID() == GameIDs.Enemy)
			{
				
				if(player.getBounds().intersects(tempObj.getBounds()))
				{
					System.out.println("sa");
				}
				
			}
		}
	}

}

