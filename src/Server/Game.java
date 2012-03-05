package Server;

import java.util.Random;

/**
 * Game class. Creates the logical ground of a game between 2 players.
 * 
 * @author Kamil
 * 
 **/

public class Game {
	// variables
	short[][] gameField;
	boolean currentPlayer;
	PlayerConnection player1;
	PlayerConnection player2;
	DBHandler dbHandler;

	// CONSTRUCTORS

	/**
	 * Creates a gamefield, changes the state of players and starts the game.
	 * 
	 * @param player1
	 * @param player2
	 * 
	 */
	public Game(PlayerConnection player1, PlayerConnection player2) {
		gameField = new short[15][15]; // Creates the gameField

		// Fills all the cells in gameField to the value of 0.
		for (int a = 0; a < 15; a++)
			for (int b = 0; b < 15; b++)
				gameField[a][b] = 0;

		dbHandler = DBHandler.getDatabase(); // Gets a reference to the database

		// gamingReady variable of the players are set to false so that the
		// other players can't reach them.
		player1.gamingReady = false;
		player2.gamingReady = false;

		// Choose the random player to start the game
		Random rnd = new Random();
		int temp = rnd.nextInt(9);
		if (temp % 2 == 0)
			currentPlayer = true;
		else
			currentPlayer = false;

		// Calls the GameStarted method for every player.
		player1.GameStarted(currentPlayer, null); // I don't know what the
													// second parameter of type
													// string does.
		player2.GameStarted(!currentPlayer, null);
	}
}
