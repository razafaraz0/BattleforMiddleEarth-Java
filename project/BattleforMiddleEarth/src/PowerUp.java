package cs319;
import java.util.*;
public class PowerUp {
	
	enum PowerUpType {Armor, Weapon, Health}
	
	/*PowerUpType[] powerUp;
	public PowerUp(PowerUpType[] powerUp){
		this.powerUp = new PowerUpType[3];
    	powerUp[0] = PowerUpType.Armor;
    	powerUp[1] = PowerUpType.Weapon;
    	powerUp[2] = PowerUpType.Health;
		
	}*/
    public static void main(String[] args) {
    	
    	//Array which include power ups. 
    	PowerUpType[] powerUp = new PowerUpType[3];
    	
    	powerUp[0] = PowerUpType.Armor;
    	powerUp[1] = PowerUpType.Weapon;
    	powerUp[2] = PowerUpType.Health;
    	
    	PowerUpType power = selectPowerUp(powerUp);
    	
        System.out.println("Random power up chosen: " + power);
    }
    
    //Returns randomly a power up type.
    public static PowerUpType selectPowerUp(PowerUpType[] powerUp){
    	
    	PowerUpType random = (powerUp[new Random().nextInt(powerUp.length)]);
    	return random;
    	
    }

}
