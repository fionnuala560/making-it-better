package application;

public class Player {
	
	private char playerType;
	private String playerName;
	private boolean aiControlled;
	
	public Player(char playerType, String playerName, boolean aiControlled) {
		this.playerType = playerType;
		this.playerName = playerName;
		this.aiControlled = aiControlled;
	}

	public char getPlayerType() {
		return playerType;
	}

	public void setPlayerType(char playerType) {
		this.playerType = playerType;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public boolean isAiControlled() {
		return aiControlled;
	}

	public void setAiControlled(boolean aiControlled) {
		this.aiControlled = aiControlled;
	}

}
