package application;

public class EventOption {

	private String text;
	private int[] effects = {-1, -1, -1, -1};
	private int[] requirements = {-1, -1, -1, -1};
	private int[] playersVisableTo = {0, 1, 2, 3};
	private int objectiveIndex = -1;
	
	public EventOption (String text, int[] effects, int[] requirements) {
		this.text = text;
		this.effects = effects;
		this.requirements = requirements;
	}
	
	public EventOption(String text, int[] effects, int[] requirements, int[] playersVisableTo) {
		this(text, effects, requirements);
		this.playersVisableTo = playersVisableTo;
	}
	
	public EventOption(String text, int[] effects, int[] requirements, int[] playersVisableTo, int objectiveIndex) {
		this(text, effects, requirements, playersVisableTo);
		this.objectiveIndex = objectiveIndex;
	}

	public String getText() {
		return text;
	}

	public int[] getEffects() {
		return effects;
	}

	public int[] getRequirements() {
		return requirements;
	}

	public int[] getPlayersVisableTo() {
		return playersVisableTo;
	}

	public int getObjectiveIndex() {
		return objectiveIndex;
	}
	
}
