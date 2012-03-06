package Server;

import java.util.Random;

/*
 * playerconectionsen ska sparas, en ny GameField ska skapas (alla värden 0),
 * DB referens hämtas, gamingReady sättas till false, och förstaspelaren bestämmas
 * (då också current player variablen) . Sen ska den kalla på GameStarted på båda playerConectionsen
 */

public class Game {
	// variables
	short[][] gameField;
	boolean currentPlayer;
	PlayerConnection player1;
	PlayerConnection player2;
	DBHandler dbHandler;

	// constructor
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
		player1.GameStarted(currentPlayer, player2.getUserName()); // I don't know what the
													// second parameter of type
													// string does.
		player2.GameStarted(!currentPlayer, player1.getUserName());
	}

	/**
	 * Changes how the game field. For testing only
	 * @param testField
	 */
	public void setTestField(short[][] testField) {
		gameField = testField;
	}

	/**
	 * Checks if the current player has won the game
	 * @return if the game is won
	 */
	public boolean VictoryCheck() {
		// TODO Auto-generated method stub
		return false;
	}
}