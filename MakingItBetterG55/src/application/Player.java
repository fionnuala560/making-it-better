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

	/*
	Student objective 2 going to school a number of times
	Teacher objective 2 and 3 eat certain number of apples, teach at school a number of time
	Parent objective 2 and 3 buy certain number of goods from shop, interact with student
	Engineer objective 2 and 3 install certain no. improvements for school, train teacher
	 */

	//to be called every time a player makes a step to complete an objective
	public void contributeToObjective(){
		this.score += 5;
	}

	//each time a player completes a task that they had ownership of
	public void completeTask(){
		this.score += 20;
	}

	//each time a player contributes resources to a task that is not their own
	public void contributeResource(){
		this.score += 5;
	}

	//scoreMaintainResource() and scoreExcessResources() to be called only when the game is finished

	//calculated for each player on game over
	public void scoreMaintainResource(){
		if(this.playerType == 's'){
			if(this.health >= (studentStartingRes[1])*0.75){
				this.score += 20;
			}
		} else if(this.playerType == 't'){
			if(this.education >= (teacherStartingRes[2])*0.75){
				this.score += 20;
			}
		} else if(this.playerType == 'p'){
			if(this.goods >= (parentStartingRes[3])*0.75){
				this.score += 20;
			}
		} else if(this.playerType == 'e'){
			if(this.money >= (engineerStartingRes[0])*0.75){
				this.score += 20;
			}
		}
	}

	//student specific for objective achieved
	public void scoreStudentReachedResource(){
		if(this.playerType == 's'){
			if(this.education >= studentStartingRes[2]*2){
				this.score += 20;
			}
		}
	}

	//calculated for each player on game over
	public void scoreExcessResources(){
		if(this.playerType == 's'){
			if(this.money >= (studentStartingRes[0])*0.25){
				this.score += 10;
			} /*else if(this.education >= (studentStartingRes[2])*0.25){
				this.score += 10;
			} */else if(this.goods >= (studentStartingRes[3])*0.25){
				this.score += 10;
			}
		} else if(this.playerType == 't'){
			if(this.money >= (teacherStartingRes[0])*0.25){
				this.score += 10;
			} else if(this.health >= (teacherStartingRes[1])*0.25){
				this.score += 10;
			} else if(this.goods >= (teacherStartingRes[3])*0.25){
				this.score += 10;
			}
		} else if(this.playerType == 'p'){
			if(this.money >= (parentStartingRes[0])*0.25){
				this.score += 10;
			} else if(this.education >= (parentStartingRes[2])*0.25){
				this.score += 10;
			} else if(this.health >= (parentStartingRes[1])*0.25){
				this.score += 10;
			}
		} else if(this.playerType == 'e'){
			if(this.health >= (engineerStartingRes[1])*0.25){
				this.score += 10;
			} else if(this.education >= (engineerStartingRes[2])*0.25){
				this.score += 10;
			} else if(this.goods >= (engineerStartingRes[3])*0.25){
				this.score += 10;
			}
		}
	}





}
