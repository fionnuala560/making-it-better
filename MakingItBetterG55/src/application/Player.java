package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {

	private char playerType; // s = student, p = parent, t = teacher, e = engineer
	private String playerName;
	private boolean isAIControlled;
	// indexed: health, education, goods, money
	private int[] resources = {-1, -1, -1, -1};
	private int[] objectives = { 0, 0, 0 };
	private static int[] studentStartingRes = { 100, 10, 30, 0 };
	private static int[] parentStartingRes = { 100, 40, 50, 30 };
	private static int[] teacherStartingRes = { 100, 70, 50, 30 };
	private static int[] engineerStartingRes = { 100, 80, 50, 80 };

	private IntegerProperty score = new SimpleIntegerProperty(0);

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

		initListeners();
	}

	//automatically adds 20 to the score if the player is a Student and their education has at least doubled from its starting value
	private void addStudentEducationListener() {
		if (playerType == 's') {
			int initialEducation = resources[1];

			score.addListener((observable, oldValue, newValue) -> {
				if (resources[1] >= initialEducation * 2) {
					increaseScore(20);
				}
			});
		}
	}

	private void initListeners(){
		addStudentEducationListener();
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

	public IntegerProperty scoreProperty() {
		return score;
	}

	public int getScore() {
		return score.get();
	}

	public void setScore(int score) {
		this.score.set(score);
	}

	private void increaseScore(int n){
		score.set(score.get() + n);
	}

	/*
	Student objective 2 going to school a number of times
	Teacher objective 2 and 3 eat certain number of apples, teach at school a number of times
	Parent objective 2 and 3 buy certain number of goods from shop, interact with student
	Engineer objective 2 and 3 install certain no. improvements for school, train teacher
	 */

	//to be called every time a player makes a step to complete an objective
	public void contributeToObjective(){
		score.set(score.get() + 5);
	}

	//each time a player completes a task that they had ownership of
	public void completeTask(){
		score.set(score.get() + 20);
	}

	//each time a player contributes resources to a task that is not their own
	public void contributeResource(){
		score.set(score.get() + 5);
	}

	//scoreMaintainResource() and scoreExcessResources() to be called only when the game is finished
	//calculated for each player on game over
	public void scoreMaintainResource(){
		if(this.playerType == 's'){
			if(this.resources[0] >= (studentStartingRes[1])*0.75){
				score.set(score.get() + 20);
			}
		} else if(this.playerType == 't'){
			if(this.resources[1] >= (teacherStartingRes[2])*0.75){
				score.set(score.get() + 20);
			}
		} else if(this.playerType == 'p'){
			if(this.resources[2] >= (parentStartingRes[3])*0.75){
				score.set(score.get() + 20);
			}
		} else if(this.playerType == 'e'){
			if(this.resources[3] >= (engineerStartingRes[0])*0.75){
				score.set(score.get() + 20);
			}
		}
	}

	//student specific for objective achieved
	public void scoreStudentReachedResource(){
		if(this.playerType == 's'){
			if(this.resources[1] >= studentStartingRes[2]*2){
				score.set(score.get() + 20);
			}
		}
	}

	//calculated for each player on game over
	public void scoreExcessResources(){
		if(this.playerType == 's'){
			if(this.resources[3] >= (studentStartingRes[0])*0.25){
				score.set(score.get() + 10);
			} else if(this.resources[2] >= (studentStartingRes[3])*0.25){
				score.set(score.get() + 10);
			}
		} else if(this.playerType == 't'){
			if(this.resources[3] >= (teacherStartingRes[0])*0.25){
				score.set(score.get() + 10);
			} else if(this.resources[0] >= (teacherStartingRes[1])*0.25){
				score.set(score.get() + 10);
			} else if(this.resources[2] >= (teacherStartingRes[3])*0.25){
				score.set(score.get() + 10);
			}
		} else if(this.playerType == 'p'){
			if(this.resources[3] >= (parentStartingRes[0])*0.25){
				score.set(score.get() + 10);
			} else if(this.resources[1] >= (parentStartingRes[2])*0.25){
				score.set(score.get() + 10);
			} else if(this.resources[0] >= (parentStartingRes[1])*0.25){
				score.set(score.get() + 10);
			}
		} else if(this.playerType == 'e'){
			if(this.resources[0] >= (engineerStartingRes[1])*0.25){
				score.set(score.get() + 10);
			} else if(this.resources[1] >= (engineerStartingRes[2])*0.25){
				score.set(score.get() + 10);
			} else if(this.resources[2] >= (engineerStartingRes[3])*0.25){
				score.set(score.get() + 10);
			}
		}
	}

	public void addGameOverScores(){
		this.scoreExcessResources();
		this.scoreMaintainResource();
	}

}
