package myPart;

public class Player {
	
	protected static  String characterType;

	public String getCharacter() {
		return characterType;
	}

	public void setCharacter(String characterType) {
		Player.characterType = characterType;
	}
	
}
