import java.util.*;
public class Account {

	private String name;
	private String password;
	private int currentLevel= 1;
	private int id = 0;
	private int score = 78;
	private ArrayList<Integer> highscores;
	private static Account user = null;
	
	
	public Account (String name, String password, int currentLevel )
	
	{
		this.name= name;
		this.password= password;
		this.currentLevel = currentLevel;
				
	}
	
	
	public ArrayList<Integer> getHighscores() {
		
		Database temp = new Database();
		temp.getHighscores(this);
		return highscores;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		Database temp = new Database();
		temp.setUsername(this);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		Database temp = new Database();
		temp.setPassword(this);
	}
	public int getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
		Database temp = new Database();
		temp.setLatestLevetl(this);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
		Database temp = new Database();
		temp.addHighscore(this);
	}
	


}
