package application;

public class Player {
	
	private char playerType; //s = student, p = parent, t = teacher, e = engineer
	private String playerName;
	private boolean isAIControlled;
	private int money;
	private int health;
	private int education;
	private int goods;
	private int score = 0;
	private static int[] studentStartingRes = {1, 2, 3, 4};
	private static int[] parentStartingRes = {1, 2, 3, 4};
	private static int[] teacherStartingRes = {1, 2, 3, 4};
	private static int[] engineerStartingRes = {1, 2, 3, 4};
	
	public Player(char playerType, String playerName, boolean isAIControlled) {
		this.playerType = playerType;
		this.playerName = playerName;
		this.isAIControlled = isAIControlled;
		switch(playerType) {
		case 's':
			money = studentStartingRes[0]; 
			health = studentStartingRes[1];
			education = studentStartingRes[2];
			goods = studentStartingRes[3];
			break;
		case 'p':
			money = parentStartingRes[0]; 
			health = parentStartingRes[1];
			education = parentStartingRes[2];
			goods = parentStartingRes[3];
			break;
		case 't':
			money = teacherStartingRes[0]; 
			health = teacherStartingRes[1];
			education = teacherStartingRes[2];
			goods = teacherStartingRes[3];
			break;
		case 'e':
			money = engineerStartingRes[0]; 
			health = engineerStartingRes[1];
			education = engineerStartingRes[2];
			goods = engineerStartingRes[3];
			break;
		default:
			money = -1;
			health = -1;
			education = -1;
			goods = -1;
			
		}
	}

	public char getPlayerType() {
		return playerType;
	}

	public String getPlayerName() {
		return playerName;
	}

	public boolean isAIControlled() {
		return isAIControlled;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getEducation() {
		return education;
	}
	
	public void setEducation(int education) {
		this.education = education;
	}
	
	public int getGoods() {
		return goods;
	}
	
	public void setGoods(int goods) {
		this.goods = goods;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getscore() {
		return score;
	}

}
