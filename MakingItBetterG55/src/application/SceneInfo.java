package application;

public class SceneInfo {
	
	private boolean fullscreen;
	private boolean music;
	private int volume;
	private String font;
	private String colourblind;
	private String language;
	
	public void setAll(boolean fullscreen, boolean music, int volume, String font, String colourblind, String language) {
		setFullScreen(fullscreen);
		setMusic(music);
		setVolume(volume);
		setFont(font);
		setColourblind(colourblind);
		setLanguage(language);
	}
	
	//setters
	public void setFullScreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}
	
	public void setMusic(boolean music) {
		this.music = music;
	}
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public void setFont(String font) {
		this.font = font;
	}
	
	public void setColourblind(String colourblind) {
		this.colourblind = colourblind;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	//getters
	public boolean getFullScreen() {
		return fullscreen;
	}
	
	public boolean getMusic() {
		return music;
	}
	
	public int getVolume() {
		return volume;
	}
	
	public String getFont() {
		return font;
	}
	
	public String getColourblind() {
		return colourblind;
	}
	
	public String getLanguage() {
		return language;
	}

}
