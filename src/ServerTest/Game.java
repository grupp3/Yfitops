
package ServerTest;

public class Game {
	public static void main(String[] args){
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2);
	}

}
