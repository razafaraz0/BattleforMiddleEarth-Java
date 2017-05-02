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
	GameBar enemyBar;
	
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
		PlayerPowerUpCollison();
		
		
	}
	//collison between player and powerup
	private void PlayerPowerUpCollison() {
		// TODO Auto-generated method stub
		//the following method handle s player enemy collision
		for(int i = 0 ; i < gameManager.object.size(); i++)
		{
			GameObject tempObj = gameManager.object.get(i);
			if(tempObj.getID() == GameIDs.powerUpHealth)
			{
				if(getBounds().intersects(tempObj.getBounds()))
				{
					System.out.println("Player-Armor Collision");
					GameBar.health += 10;
					gameManager.object.remove(i);
				}	
				
			}
			else if(tempObj.getID() == GameIDs.powerUpArmor)
			{
				if(getBounds().intersects(tempObj.getBounds()))
				{
					System.out.println("Player-Armor Collision");
					GameBar.armor += 10;
					gameManager.object.remove(i);
				}
			}
			else{}
			
		}
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
			// enemy 2 is being used
			else if(tempObj.getID() == GameIDs.Enemy2)
			{
				if(getBounds().intersects(tempObj.getBounds()))
				{
					System.out.println("Player-Enemy2 Collision");
					//first the armor must reach 0 then health will decrease
					if(GameBar.armor <= 0 )
					{
						GameBar.health -= 5; //health decreaes by 2 
						
					}
					else
					{
						GameBar.armor -= 3;
						System.out.println(GameBar.armor);
					}
					
					GameBar.score-=10;	//score decreses by 10
				}
				
			}
			else
			{
				//HERE IS THE BOSS
				if(getBounds().intersects(tempObj.getBounds()))
				{
					//first the armor must reach 0 then health will decrease
					if(GameBar.armor <= 0 )
					{
						GameBar.health -= 30; //health decreaes by 2 
					}
					else
					{
						GameBar.armor -= 20;
					//	System.out.println(GameBar.armor);
					}
					
					GameBar.score-=10;	//score decreses by 10
					System.out.println("Player-Boss Collision");
				}
				
			}
		}		
	}
	
	//The enemy fires at the player
	private void EnemyBulletPlayerCollisionHandling(){
		
		
		//the following method handle s player enemy collision
		for(int i = 0 ; i < gameManager.theEnemyWeapon.size(); i++)
		{
			GameObject tempObj = gameManager.theEnemyWeapon.get(i);
			if(tempObj.getID() == GameIDs.Enemy)
			{
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
			else if(tempObj.getID() == GameIDs.Enemy2)
			{
				if(getBounds().intersects(tempObj.getBounds()))
				{
					System.out.println("Enemy2Bullet- Player Collision");
					//first the armor must reach 0 then health will decrease
					if(GameBar.armor <= 0 )
					{
						GameBar.health -= 10; //health decreaes by 2 
					}
					else
					{
						GameBar.armor -= 5;
					}
					GameBar.score-=5;
					gameManager.theEnemyWeapon.remove(i); // if the bullet and the player hit each other 
				}
			}
			else if(tempObj.getID() == GameIDs.BossEnemy)
			{
				if(getBounds().intersects(tempObj.getBounds()))
				{
					System.out.println("BossBullet- Player Collision");
					//first the armor must reach 0 then health will decrease
					if(GameBar.armor <= 0 )
					{
						GameBar.health -= 50; //health decreaes by 2 
					}
					else
					{
						GameBar.armor -= 40;
					}
					GameBar.score-=15;
					gameManager.theEnemyWeapon.remove(i); // if the bullet and the player hit each other 
	
				}
				
			}
			else
			{
				System.out.println("collison");
			}

				
		}
}	
	
	
	//meaning the player fires at the enemy
	private void BulletcollisionHandling(){
		GameObject tempEnemy = null;
		//GameObject temp = null;
	
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
					if(tempEnemy.getID() == GameIDs.BossEnemy)
					{
						System.out.println("Player-Staff Bullet and Boss Collision");
						GameBar.bossHealth-=2;
						if(GameBar.bossHealth <= 0 )
						{
							////
							//Need to update this to bossList
							///
							gameManager.theEnemyList.remove();
						}
						else{}
						
					}
					else{
						System.out.println("Player-Staff Collision");
						gameManager.theEnemyList.remove(j);
						
					}
					GameBar.score+=50;
				}
				
			}
			else if(tempWeapon.getID() == GameIDs.Sword)
			{
				if(tempWeapon.getBounds().intersects(tempEnemy.getBounds()))
				{
					if(tempEnemy.getID() == GameIDs.BossEnemy)
					{
						System.out.println("Player-Sword Bullet and Boss Collision");
						GameBar.bossHealth-=2;	
						
						if(GameBar.bossHealth <= 0 )
						{
							gameManager.theBossList.remove(i);
						}
					}
					else{
						System.out.println("Player-Sword Collision");
						gameManager.theEnemyList.remove(j);
						GameBar.score+=50;
					}

				}
				
			}
			else //Arrow()
			{
				if(tempWeapon.getBounds().intersects(tempEnemy.getBounds()))
				{
					if(tempEnemy.getID() == GameIDs.BossEnemy)
					{
						System.out.println("Player-Arrow Bullet and Boss Collision");
						GameBar.bossHealth-=2;	
						if(GameBar.bossHealth <= 0 )
						{
							gameManager.theBossList.remove(i);
						}
					}
					else{
						System.out.println("Player-Arrow Collision");
						gameManager.theEnemyList.remove(j);
						GameBar.score+=50;
					}

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
