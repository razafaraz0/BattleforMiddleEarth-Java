package myPart;

public class enemyWeapon2 extends enemyWeapon {

	public enemyWeapon2(int x, int y, GameIDs id) {
		super(x, y, id);
		
		this.xCoordinate = x;
		this.yCoordinate = y;
		
		yDirectedSpeed = 3;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.yCoordinate += 9;
	}

}
