package application;

public class Player {
	
	private char playerType;
	private String playerName;
	private boolean aiControlled;
	private int moneyResource;
	private int healthResource;
	private int educationResource;
	private int goodsResource;
	
	public Player(char playerType, String playerName, boolean aiControlled, 
			int moneyResource, int healthResource, int educationResource, int goodsResource) {
		this.playerType = playerType;
		this.playerName = playerName;
		this.aiControlled = aiControlled;
		this.moneyResource = moneyResource;
		this.healthResource = healthResource;
		this.educationResource = educationResource;
		this.goodsResource = goodsResource;
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
	
	public int getMoneyResource() {
		return moneyResource;
	}
	
	public int getHealthResource() {
		return healthResource;
	}
	
	public int getEducationResource() {
		return educationResource;
	}
	
	public int getGoodsResource() {
		return goodsResource;
	}

}
