package myPart;

public class weapon {
		private int attackDamage;
		private int ammoCount;
		
		weapon(int character) {
			if(character == 0 )
			{
				setAttackDamage(WeaponType.STAFF);
			}
			else if(character == 1)
			{
				setAttackDamage(WeaponType.SWORD);
			}
			else
			{
				setAttackDamage(WeaponType.ARROW);
			}
		}


		public void setAttackDamage(WeaponType weapon)
		{
			switch(weapon)
			{
			case STAFF:
					setAttackDamage(70);
					setAmmoCount(20);
					break;
			case SWORD:
				setAttackDamage(100);
					setAmmoCount(15);	
					break;
			case ARROW:
					setAttackDamage(50);
					setAmmoCount(30);
					break;
			default:
					setAttackDamage(0);
					setAmmoCount(0);
					break;
			}
		}


		public int getAttackDamage() {
			return attackDamage;
		}


		public void setAttackDamage(int attackDamage) {
			this.attackDamage = attackDamage;
		}


		public int getAmmoCount() {
			return ammoCount;
		}


		public void setAmmoCount(int ammoCount) {
			this.ammoCount = ammoCount;
		}
		
}
