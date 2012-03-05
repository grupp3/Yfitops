package ServerTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Server.Game;
import Server.PlayerConnection;

/**
 * Tests correctness of class Server.Game
 * 
 * @author Kamil
 * 
 */
public class GameTest {

	@Test
	public void test() {
		Server.PlayerConnection player1= new Server.PlayerConnection(null, null);
		Server.PlayerConnection player2 = new Server.PlayerConnection(null, null);
		Server.Game game = new Server.Game(player1, player2);
	}

}
