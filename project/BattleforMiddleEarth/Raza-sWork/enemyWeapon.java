package myPart;

import java.awt.Graphics;
import java.awt.Rectangle;

public class enemyWeapon extends GameObject{
	private int attackDamage;
	
	Game game;
	GameManager gameManager;
	
	public enemyWeapon(int x, int y, GameIDs id) {
		super(x, y, id);
		this.xCoordinate = x;
		this.yCoordinate = y;
		
		yDirectedSpeed = 4;
		// TODO Auto-generated constructor stub
	}
	
	public int getAttackDamage() {
		return attackDamage;
	}


	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.yCoordinate += 5;
	}

	@Override
	public void render(Graphics graphics) {
		// TODO Auto-generated method stub
		graphics.fillRect(xCoordinate, yCoordinate, 10, 10);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(xCoordinate,yCoordinate,10,10);
	}

}
