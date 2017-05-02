package myPart;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class InputManager extends KeyAdapter {

		private GameManager gameManager;
	
		public InputManager(GameManager gameManager)
		{
			this.gameManager = gameManager;
		}
		public 	void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			System.out.println(key);//prints the kay pressed
			
			
			for(int i = 0 ; i < gameManager.object.size() ; i++)
			{
				GameObject tempObject = gameManager.object.get(i);
				
				if(tempObject.getID()== GameIDs.Player)
				{
					if(key == KeyEvent.VK_UP)
					{
						tempObject.yDirectedSpeed = -10;
						
						//tempObject.updateYCoordinate(tempObject.getYCoordinate()-10);
					}
					
					if(key == KeyEvent.VK_DOWN)
					{
						tempObject.yDirectedSpeed = 10;
						//tempObject.updateYCoordinate(tempObject.getYCoordinate()+10);
					}
					if(key == KeyEvent.VK_RIGHT)
					{
						tempObject.xDirectedSpeed = 10;
						//tempObject.updateXCoordinate(tempObject.getXCoordinate()+10);
					}
					
					if(key == KeyEvent.VK_LEFT)
					{
						tempObject.xDirectedSpeed = -10;
						//tempObject.updateXCoordinate(tempObject.getXCoordinate()-10);
					}
					
					if(key == KeyEvent.VK_SPACE)
					{
						gameManager.addWeapon(new weapon(0 , tempObject.getXCoordinate() , tempObject.getYCoordinate()));
					}
				} 
			}
		}
		
		public 	void keyReleased(KeyEvent e)
		{
			int key = e.getKeyCode();
			System.out.println(key);//prints the kay pressed
			
			
			for(int i = 0 ; i < gameManager.object.size() ; i++)
			{
				GameObject tempObject = gameManager.object.get(i);
				
				if(tempObject.getID()== GameIDs.Player)
				{
					if(key == KeyEvent.VK_UP)
					{
						tempObject.yDirectedSpeed = 0;
						
						//tempObject.updateYCoordinate(tempObject.getYCoordinate()-10);
					}
					
					if(key == KeyEvent.VK_DOWN)
					{
						tempObject.yDirectedSpeed = 0;
						//tempObject.updateYCoordinate(tempObject.getYCoordinate()+10);
					}
					if(key == KeyEvent.VK_RIGHT)
					{
						tempObject.xDirectedSpeed = 0;
						//tempObject.updateXCoordinate(tempObject.getXCoordinate()+10);
					}
					
					if(key == KeyEvent.VK_LEFT)
					{
						tempObject.xDirectedSpeed = 0;
						//tempObject.updateXCoordinate(tempObject.getXCoordinate()-10);
					}
					
				} 
			}
		}
}
