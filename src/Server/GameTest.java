package Server;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void test() {
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2);
	}

}
