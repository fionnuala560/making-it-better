package application;

public class EventOption {

	private String text;
	private int[] effects = {-1, -1, -1, -1};
	private int[] requirements = {-1, -1, -1, -1};
	private boolean[] playersVisableTo = {true, true, true, true};
	private int objectiveIndex = -1;
	private boolean endsEvent = true;
	
	public EventOption (String text, int[] effects, int[] requirements) {
		this.text = text;
		this.effects = effects;
		this.requirements = requirements;
	}
	
	public EventOption(String text, int[] effects, int[] requirements, boolean[] playersVisableTo) {
		this(text, effects, requirements);
		this.playersVisableTo = playersVisableTo;
	}
	
	public EventOption(String text, int[] effects, int[] requirements, boolean[] playersVisableTo, int objectiveIndex) {
		this(text, effects, requirements, playersVisableTo);
		this.objectiveIndex = objectiveIndex;
	}
	
	public EventOption(String text, int[] effects, int[] requirements, boolean[] playersVisableTo, int objectiveIndex, boolean endsEvent) {
		this(text, effects, requirements, playersVisableTo, objectiveIndex);
		this.endsEvent = endsEvent;
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

	public boolean[] getPlayersVisableTo() {
		return playersVisableTo;
	}

	public int getObjectiveIndex() {
		return objectiveIndex;
	}
	
	public boolean getEndsEvent() {
		return endsEvent;
	}
	
}
