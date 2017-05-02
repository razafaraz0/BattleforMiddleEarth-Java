package myPart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class powerUp extends GameObject {
	public enum PowerUpType {Armor,Health} //removed weapon
	PowerUpType[] powerUp;
	PowerUpType power;
	public powerUp(int x, int y, GameIDs id) {
		super(x, y, id);
		xDirectedSpeed =2;
		yDirectedSpeed =2;
		
    	powerUp = new PowerUpType[2];
    	
    	powerUp[0] = PowerUpType.Armor;
    	//powerUp[1] = PowerUpType.Weapon;
    	powerUp[1] = PowerUpType.Health;
    	
    	
    	power = selectPowerUp(powerUp);


	}

	
		
 /*   public static void main(String[] args) {
    	
    	//Array which include power ups. 
    	PowerUpType[] powerUp = new PowerUpType[3];
    	
    	powerUp[0] = PowerUpType.Armor;
    	//powerUp[1] = PowerUpType.Weapon;
    	powerUp[1] = PowerUpType.Health;
    	
    	PowerUpType power = selectPowerUp(powerUp);
    	
        System.out.println("Random power up chosen: " + power);
    }
    
   */ 
    //Returns randomly a power up type.
    public static PowerUpType selectPowerUp(PowerUpType[] powerUp){
    	
    	PowerUpType random = (powerUp[new Random().nextInt(powerUp.length)]);
    	return random;
    	
    }

    public PowerUpType getPowerUp(){
    	

    	return selectPowerUp(powerUp);
    	
    }
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		//xCoordinate += xDirectedSpeed;
		//yCoordinate += yDirectedSpeed;

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
    	
		System.out.println(power);
	}

	@Override
	public void render(Graphics graphics) {
		// TODO Auto-generated method stub
		graphics.setColor(Color.gray);
		graphics.fillRect(xCoordinate, yCoordinate, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(xCoordinate,yCoordinate,16,16);
		//return null;
	}
}
