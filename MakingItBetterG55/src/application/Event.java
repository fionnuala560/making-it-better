package application;

public class Event {
	
	private String title;
	private String textBody;
	private EventOption[] options;
	private EventOption cancelOption = new EventOption("Cancel", new int[] {0,0,0,0}, new int[] {0,0,0,0});
	
	public Event(String title, String textBody, EventOption[] options, boolean isOptional) {
		this.title = title;
		this.textBody = textBody;
		
		if (isOptional) {
			EventOption[] allOptions = new EventOption[options.length + 1];
			allOptions[0] = cancelOption;
			for(int i = 0; i < options.length; i++) {
				allOptions[i+1] = options[i];
			}
		} else {
			this.options = options;
		}
		
	}

	public String getTitle() {
		return title;
	}

	public String getTextBody() {
		return textBody;
	}

	public EventOption[] getOptions() {
		return options;
	}
	
}
