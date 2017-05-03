package myPart;
import java.awt.Graphics;
import java.util.LinkedList;

public  class GameManager  {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>(); 
	LinkedList<weapon> theWeaponList = new LinkedList<weapon>();	
	LinkedList<Enemy> theEnemyList = new LinkedList<Enemy>();
	LinkedList<Enemy2> theEnemyList2 = new LinkedList<Enemy2>();
	LinkedList<BossEnemy> theBossList = new LinkedList<BossEnemy>();
	
	
	LinkedList<enemyWeapon> theEnemyWeapon = new LinkedList<enemyWeapon>();
	
	Enemy tempEnemy;
	weapon tempWeapon;
	Game game;
	GameObject tempObj;
	GameManager gameManager;
	
	
	enemyWeapon tempEnemyWeapon;
	
	int startYPosition = 50;
	int separation;
	public void addEnemyObjects(Game game)
	{
		this.game = game;
		this.addEnemy(new BossEnemy(0 , startYPosition , GameIDs.BossEnemy));
		//this.addEnemy(new Enemy2(50 , startYPosition , GameIDs.Enemy2));
		//this.addEnemy(new Enemy2(100 , startYPosition , GameIDs.Enemy2));
		//this.addEnemy(new Enemy(150 , startYPosition , GameIDs.Enemy));
	}
	

	
	// updates all the game obejcts
	public void tick()
	{	
		
		//UPADATES all the game element , such as health bar
		for(int i = 0 ; i < object.size() ; i++)
		{
			tempObj = object.get(i);
			tempObj.tick();
		}
		
		//updates the weapon bullets
		for(int i = 0 ; i < theWeaponList.size() ; i++)
		{
			tempWeapon = theWeaponList.get(i);			
			//to remove the bullet once it is out of bound from the game screen 
			if( (tempWeapon.getYCoordinate() < 0))
			{	
				removeWeapon(tempWeapon);
				System.out.println("Bullet Destroyed");
			}			
			tempWeapon.tick();
		}
		//updates the enemy weapon bullets
		for(int i = 0 ; i < theEnemyWeapon.size() ; i++)
		{
			tempEnemyWeapon = theEnemyWeapon.get(i);
						
			if( (tempEnemyWeapon.getYCoordinate() > Game.HEIGHT))
			{	
				removeEnemyWeapon(tempEnemyWeapon);
				System.out.println("EnemyBullet Destroyed");
			}
			tempEnemyWeapon.tick();	
		}
		
		//updates the enemy
		for(int i = 0 ; i < theEnemyList.size() ; i++)
		{
			tempEnemy = theEnemyList.get(i);
			tempEnemy.tick();
		}
		
		//updates the enemy2
		for(int i = 0 ; i < theEnemyList2.size() ; i++)
		{
			tempEnemy = theEnemyList2.get(i);
			tempEnemy.tick();
		}
		
		//updates the Boss
		for(int i = 0 ; i < theBossList.size() ; i++)
		{
			tempEnemy = theBossList.get(i);
			tempEnemy.tick();
		}
		
	}
	//updates teh display of all the game objects
	public void render(Graphics graphics)
	{
		for(int i = 0 ; i < object.size() ; i++)
		{
			GameObject tempObj = object.get(i);
			tempObj.render(graphics);
		}
		for(int i = 0 ; i < theWeaponList.size() ; i++)
		{
			tempWeapon = theWeaponList.get(i);
			tempWeapon.render(graphics);
		}
		
		for(int i = 0 ; i < theEnemyList.size() ; i++)
		{
			tempEnemy = theEnemyList.get(i);
			tempEnemy.render(graphics);
		}
		for(int i = 0 ; i < theEnemyList2.size() ; i++)
		{
			tempEnemy = theEnemyList2.get(i);
			tempEnemy.render(graphics);
		}
		
		for(int i = 0 ; i < theBossList.size() ; i++)
		{
			tempEnemy = theBossList.get(i);
			tempEnemy.render(graphics);
		}
		
	for(int i = 0 ; i < theEnemyWeapon.size() ; i++)
		{
			tempEnemyWeapon = theEnemyWeapon.get(i);
			tempEnemyWeapon.render(graphics);
		}

	}
	
	
	public void randomFire() {
		
		System.out.println(this.theEnemyList.size());
		
		for(int i = 0 ; i < this.theEnemyList.size() ; i++){
			//GameObject tempObj = gameManager.theEnemyWeapon.get(i);
			Enemy tempObject = this.theEnemyList.get(i);
			if(tempObject.getID() == GameIDs.Enemy)
			{
				

				this.addEnemyWeapon(new enemyWeapon(tempObject.getXCoordinate() , tempObject.getYCoordinate(), GameIDs.Enemy));

			}
			else if(tempObject.getID() == GameIDs.Enemy2)
			{
				//Enemy tempObject = this.theEnemyList.get(i);

				this.addEnemyWeapon(new enemyWeapon2(tempObject.getXCoordinate() , tempObject.getYCoordinate(), GameIDs.Enemy2));

			}
			else if(tempObject.getID() == GameIDs.BossEnemy)
			{
				this.addEnemyWeapon(new enemyWeapon2(tempObject.getXCoordinate() , tempObject.getYCoordinate(), GameIDs.BossEnemy));
				
			}
			else
			{
				System.out.println("Stuff");
			}

		}

		
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}

	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
	
	

	public void addWeapon(weapon theWeapon)
	{
		theWeaponList.add(theWeapon);
	}
	public void removeWeapon(weapon theWeapon)
	{
		theWeaponList.remove(theWeapon);
	}
	
	
	
	public void addEnemy(Enemy theEnemy)
	{
		theEnemyList.add(theEnemy);
	}

	public void removeEnemy(Enemy theEnemy)
	{
		theEnemyList.remove(theEnemy);
	}
	
	
	
	public void removeEnemyWeapon(enemyWeapon enemyWeapon)
	{
		theEnemyWeapon.remove(enemyWeapon);
	}
	public void addEnemyWeapon(enemyWeapon enemyWeapon)
	{
		theEnemyWeapon.add(enemyWeapon);
	}
}
