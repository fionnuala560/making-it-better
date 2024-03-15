package application;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import javafx.scene.Scene;

public class UnitTester {

	@Test
	public void testGetText() {
		TextRetriever t = new TextRetriever();
		String output = t.getText(2, 1);
		assertEquals("Health", output);
	}
	
	@Test
	public void testGetText1() {
		TextRetriever t = new TextRetriever();
		String output = t.getText(-1, 1);
		assertEquals("Invalid Text", output);
	}
	
	@Test
	public void testGetText2() {
		TextRetriever t = new TextRetriever();
		String output = t.getText(2, 2);
		assertEquals("Salud", output);
	}
	
	@Ignore
	public void testMakeMainScene() {
		MainSceneHandler m = new MainSceneHandler(new Player[] {new Player('e', "bob", false, new String[] {"", "", ""}),
				new Player('e', "bob", false, new String[] {"", "", ""}),
				new Player('e', "bob", false, new String[] {"", "", ""}),
				new Player('e', "bob", false, new String[] {"", "", ""})});
		Scene output = m.makeMainScene();
		assertNotEquals(null, output);
	}

}
