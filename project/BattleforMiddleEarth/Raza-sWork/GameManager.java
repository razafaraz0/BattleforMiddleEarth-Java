package myPart;
import java.awt.Graphics;
import java.util.LinkedList;

public  class GameManager  {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	LinkedList<weapon> theWeaponList = new LinkedList<weapon>();	
	LinkedList<Enemy> theEnemyList = new LinkedList<Enemy>();
	
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
		this.addEnemy(new Enemy(0 , startYPosition , GameIDs.Enemy));
		this.addEnemy(new Enemy(50 , startYPosition , GameIDs.Enemy));
		this.addEnemy(new Enemy(100 , startYPosition , GameIDs.Enemy));
		this.addEnemy(new Enemy(150 , startYPosition , GameIDs.Enemy));
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
			{	removeWeapon(tempWeapon);
				System.out.println("Bullet Destroyed");
			}			
			tempWeapon.tick();
		}
		//updates the weapon bullets
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
		
	for(int i = 0 ; i < theEnemyWeapon.size() ; i++)
		{
			tempEnemyWeapon = theEnemyWeapon.get(i);
			tempEnemyWeapon.render(graphics);
		}

	}
	
	
	public void randomFire() {
		
		System.out.println(this.theEnemyList.size());
		for(int i = 0 ; i < this.theEnemyList.size() ; i++){

			Enemy tempObject = this.theEnemyList.get(i);

			this.addEnemyWeapon(new enemyWeapon (tempObject.getXCoordinate() , tempObject.getYCoordinate(), GameIDs.Enemy));


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
