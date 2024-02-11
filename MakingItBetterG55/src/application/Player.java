package application;

public class Player {

	private char playerType; // s = student, p = parent, t = teacher, e = engineer
	private String playerName;
	private boolean isAIControlled;
	// indexed: health, education, goods, money
	private int[] resources = {-1, -1, -1, -1};
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
			resources[0] = studentStartingRes[0];
			resources[1] = studentStartingRes[1];
			resources[2] = studentStartingRes[2];
			resources[3] = studentStartingRes[3];
			break;
		case 'p':
			resources[0] = parentStartingRes[0];
			resources[1] = parentStartingRes[1];
			resources[2] = parentStartingRes[2];
			resources[3] = parentStartingRes[3];
			break;
		case 't':
			resources[0] = teacherStartingRes[0];
			resources[1] = teacherStartingRes[1];
			resources[2] = teacherStartingRes[2];
			resources[3] = teacherStartingRes[3];
			break;
		case 'e':
			resources[0] = engineerStartingRes[0];
			resources[1] = engineerStartingRes[1];
			resources[2] = engineerStartingRes[2];
			resources[3] = engineerStartingRes[3];
			break;
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
		return resources[3];
	}

	public void setMoney(int money) {
		resources[3] = money;
	}

	public int getHealth() {
		return resources[0];
	}

	public void setHealth(int health) {
		resources[0] = health;
	}

	public int getEducation() {
		return resources[1];
	}

	public void setEducation(int education) {
		resources[1] = education;
	}

	public int getGoods() {
		return resources[2];
	}

	public void setGoods(int goods) {
		resources[2] = goods;
	}
	
	public int[] getResources() {
		return resources;
	}

	public int[] getObjectives() {
		return objectives;
	}

}
