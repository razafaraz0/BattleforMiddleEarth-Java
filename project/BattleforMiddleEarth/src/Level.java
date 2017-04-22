package cs319;
import java.util.*;

public class Level {
	
	int[] levels;
	public Level(int[] levels){
		this.levels = new int[4];
		levels[0] = 1;
		levels[1] = 2;
		levels[2] = 3;
		levels[3] = 4;
		levels[4] = 5;
	}
    
    public int getEnemyNumber(int level){
    	return level*5;
    }
}
