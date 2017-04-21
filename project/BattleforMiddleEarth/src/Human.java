package myPart;

public class Human extends Player  {
		private WeaponType weapon;
		private int health;
		private weapon playerWeapon;
		
		Human()
		{
			setPlayerWeapon(new weapon(1));
			this.setWeapon(WeaponType.SWORD);
			this.health = 1000;		
			this.setCharacter("HUMAN");
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
