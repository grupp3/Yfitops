package ClientTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Client.GUIHandler;
import Client.Game;


public class gameTest {

	GUIHandler connection;
	
	@Test
	public void gameConstructorTestSucceeds() {
		Game testGame = new Game(connection);
	}

}
