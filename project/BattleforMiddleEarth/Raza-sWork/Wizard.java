package myPart;

public class Wizard extends Player {
	private WeaponType weapon;
	private int health;
	private weapon playerWeapon;

	
	Wizard()
	{

		setPlayerWeapon(new weapon(0));
		this.setWeapon(WeaponType.STAFF);
		this.health = 750;
		this.setCharacter("WIZARD");
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
