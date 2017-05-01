package myPart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



public class Player extends GameObject {
	protected static  String characterType;
	GameManager gameManager;
	Collision cool;
	Enemy enemy;
	GameBar gameBar;
	
	public Player(int x , int y , GameIDs id , GameManager gameManager)
	{
		super(x , y , id);
		this.gameManager = gameManager;

	}
	
	public Rectangle getBounds(){
		return new Rectangle(xCoordinate,yCoordinate,32,32);
	}

	public Rectangle getWeaponBounds(){
		return new Rectangle(xCoordinate,yCoordinate,10,10);
	}
	
	public String getCharacter() {
		return characterType;
	}


	public void setCharacter(String characterType) {
		Player.characterType = characterType;
	}
	
	public void tick(){
		

		xCoordinate += xDirectedSpeed;
		yCoordinate += yDirectedSpeed; 

		
		
		xCoordinate = Game.boundControl(xCoordinate, 0, Game.WIDTH-32);
		yCoordinate = Game.boundControl(yCoordinate, 0, Game.HEIGHT-64);
		
		PlayerEnemyCollisionHandling();
		BulletcollisionHandling();
		EnemyBulletPlayerCollisionHandling();
	}

	//the following method deals with Player EnemyCollission
	private void PlayerEnemyCollisionHandling(){
		
		
		//the following method handle s player enemy collision
		for(int i = 0 ; i < gameManager.theEnemyList.size(); i++)
		{
			GameObject tempObj = gameManager.theEnemyList.get(i);
			if(tempObj.getID() == GameIDs.Enemy)
			{
				if(getBounds().intersects(tempObj.getBounds()))
				{
					System.out.println("Player-EnemyCollision");
					//first the armor must reach 0 then health will decrease
					if(GameBar.armor <= 0 )
					{
						GameBar.health -= 2; //health decreaes by 2 
					}
					else
					{
						GameBar.armor -= 1;
						System.out.println(GameBar.armor);
					}
					
					GameBar.score-=10;	//score decreses by 10
				}
				
			}
		}		
	}
	
	//the following method deals with Enemy Bullet and Player Collission
	private void EnemyBulletPlayerCollisionHandling(){
		
		
		//the following method handle s player enemy collision
		for(int i = 0 ; i < gameManager.theEnemyWeapon.size(); i++)
		{
			GameObject tempObj = gameManager.theEnemyWeapon.get(i);

				if(getBounds().intersects(tempObj.getBounds()))
				{
					System.out.println("EnemyBullet- Player Collision");
					//first the armor must reach 0 then health will decrease
					if(GameBar.armor <= 0 )
					{
						GameBar.health -= 2; //health decreaes by 2 
					}
					else
					{
						GameBar.armor -= 1;
					}
					GameBar.score-=5;
					gameManager.theEnemyWeapon.remove(i); // if the bullet and the player hit each other 
				}
				
			}
}	
	
	
	//the following method handle s player enemy collision
	private void BulletcollisionHandling(){
		GameObject tempEnemy = null;
		
	for (int j = 0 ; j < gameManager.theEnemyList.size() ; j++)
	{
		tempEnemy= gameManager.theEnemyList.get(j);

		
		for(int i = 0 ; i < gameManager.theWeaponList.size(); i++)
		{
			GameObject tempWeapon = gameManager.theWeaponList.get(i); 
			
			if(tempWeapon.getID() == GameIDs.Staff)
			{
				if(tempWeapon.getBounds().intersects(tempEnemy.getBounds()))
				{
					System.out.println("Player-Staff Collision");
					gameManager.theEnemyList.remove(j);
					GameBar.score+=50;
				}
				
			}
			else if(tempWeapon.getID() == GameIDs.Sword)
			{
				if(tempWeapon.getBounds().intersects(tempEnemy.getBounds()))
				{
					System.out.println("Player-Sword Collision");
					gameManager.theEnemyList.remove(j);
					GameBar.score+=50;
				}
				
			}
			else //Arrow()
			{
				if(tempWeapon.getBounds().intersects(tempEnemy.getBounds()))
				{
					System.out.println("Player-Arrow Collision");
					gameManager.theEnemyList.remove(j);
					GameBar.score+=50;
				}
				
			}
		}	
	}
}

	
	public void  render(Graphics graphics)
	{
		if(id == GameIDs.Player)
		{
			graphics.setColor(Color.white);
		graphics.fillRect(xCoordinate, yCoordinate, 32, 32);
		}
	}
}
