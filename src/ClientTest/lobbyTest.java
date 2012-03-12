package ClientTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Client.ClientMain;
import Client.GUIHandler;
import Client.Lobby;

public class lobbyTest {

	GUIHandler connection;
	
	@Test
	public void lobbyConstructorTestSucceeds() {
		Lobby testLobby = new Lobby(connection);
	}
}
