package myPart;

public class Elf extends Player {
	private WeaponType weapon;
	private int health;
	private weapon playerWeapon;
	
	Elf()
	{	
		setPlayerWeapon(new weapon(2));
		this.setWeapon(WeaponType.ARROW);
		this.health = 500;	
		this.setCharacter("ELF");
	}
	
	public WeaponType getWeapon() {
		return weapon;
	}
	public void setWeapon(WeaponType weapon) {	
		this.weapon = weapon;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}

	public weapon getPlayerWeapon() {
		return playerWeapon;
	}

	public void setPlayerWeapon(weapon playerWeapon) {
		this.playerWeapon = playerWeapon;
	}

	
}
