package application;

public class Player {
	
	private char playerType;
	private String playerName;
	private boolean aiControlled;
	private int money;
	private int health;
	private int education;
	private int goods;
	
	public Player(char playerType, String playerName, boolean aiControlled, 
			int money, int health, int education, int goods) {
		this.playerType = playerType;
		this.playerName = playerName;
		this.aiControlled = aiControlled;
		this.money = money;
		this.health = health;
		this.education = education;
		this.goods = goods;
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
	
	public int getMoney() {
		return money;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getEducation() {
		return education;
	}
	
	public int getGoods() {
		return goods;
	}

}
