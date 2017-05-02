package myPart;

public class BossWeapon extends enemyWeapon {

	public BossWeapon(int x, int y, GameIDs id) {
		super(x, y, id);
		
		this.xCoordinate = x;
		this.yCoordinate = y;
		
		yDirectedSpeed = 2;
		xDirectedSpeed = 2;
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		// TODO Auto-generated method stub
		this.yCoordinate += 1;
		this.xCoordinate += 1;
		
	}
}
