package myPart;

import java.awt.Graphics;
import java.awt.Rectangle;


public class weapon extends GameObject{
		private int attackDamage;
		private int ammoCount;
		
		weapon tempWeapon;
		Game game;
		GameManager gameManager;
		
		weapon(int character, int x, int y) {	
			super(x , y , GameIDs.Staff);
			this.xCoordinate = x;
			this.yCoordinate = y;
			
			yDirectedSpeed = 4;
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


		//@Override
		public void tick() {
			this.yCoordinate += -5;
			
			
		}
		


		//weapon is created here
		@Override
		public void render(Graphics graphics) {

				graphics.fillRect(xCoordinate, yCoordinate, 10, 10);

		}
		//
		public Rectangle getBounds() {
			// TODO Auto-generated method stub
			return new Rectangle(xCoordinate,yCoordinate,10,10);
			//return null;
		}
		
}
