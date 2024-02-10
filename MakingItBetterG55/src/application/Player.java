package application;

public class Player {

	private char playerType; // s = student, p = parent, t = teacher, e = engineer
	private String playerName;
	private boolean isAIControlled;
	private int health;
	private int education;
	private int goods;
	private int money;
	private int[] objectives = { 0, 0, 0 };
	private static int[] studentStartingRes = { 1, 2, 3, 4 };
	private static int[] parentStartingRes = { 1, 2, 3, 4 };
	private static int[] teacherStartingRes = { 1, 2, 3, 4 };
	private static int[] engineerStartingRes = { 1, 2, 3, 4 };

	public Player(char playerType, String playerName, boolean isAIControlled) {
		this.playerType = playerType;
		this.playerName = playerName;
		this.isAIControlled = isAIControlled;
		switch (playerType) {
		case 's':
			health = studentStartingRes[0];
			education = studentStartingRes[1];
			goods = studentStartingRes[2];
			money = studentStartingRes[3];
			break;
		case 'p':
			health = parentStartingRes[0];
			education = parentStartingRes[1];
			goods = parentStartingRes[2];
			money = parentStartingRes[3];
			break;
		case 't':
			health = teacherStartingRes[0];
			education = teacherStartingRes[1];
			goods = teacherStartingRes[2];
			money = teacherStartingRes[3];
			break;
		case 'e':
			health = engineerStartingRes[0];
			education = engineerStartingRes[1];
			goods = engineerStartingRes[2];
			money = engineerStartingRes[3];
			break;
		default:
			health = -1;
			education = -1;
			goods = -1;
			money = -1;
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

	public int[] getObjectives() {
		return objectives;
	}

}
