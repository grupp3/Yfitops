package Server;

import java.util.Random;

/*
 * playerconectionsen ska sparas, en ny GameField ska skapas (alla värden 0),
 * DB referens hämtas, gamingReady sättas till false, och förstaspelaren bestämmas
 * (då också current player variablen) . Sen ska den kalla på GameStarted på båda playerConectionsen
 */

public class Game extends Thread {
	// variables
	private short[][] gameField;
	private boolean currentPlayer;
	private PlayerConnection player1;
	private PlayerConnection player2;
	private DBHandler dbHandler;
	private int timeLimit, timeLeftP1, timeLeftP2;
	private boolean gameEnd;

	// constructor
	/**
	 * Initilizes the game
	 * @param player1
	 * @param player2
	 * @param timeLimit
	 */
	public Game(PlayerConnection player1, PlayerConnection player2, int timeLimit) {
		this.player1 = player1;
		this.player2 = player2;
		this.timeLimit = timeLimit;
		gameEnd = false;
		
		gameField = new short[15][15]; // Creates the gameField

		// Fills all the cells in gameField to the value of 0.
		for (int a = 0; a < 15; a++)
			for (int b = 0; b < 15; b++)
				gameField[a][b] = 0;

		dbHandler = DBHandler.getDatabase(); // Gets a reference to the database

		if(timeLimit > 0)
		{
			timeLeftP1 = timeLimit * 60;
			timeLeftP2 = timeLeftP1;
			this.start();
		}

		player1.setCurrentGame(this);
		player2.setCurrentGame(this);
		
		// Choose the random player to start the game
		Random rnd = new Random();
		int temp = rnd.nextInt(9);
		if (temp % 2 == 0)
			currentPlayer = true;
		else
			currentPlayer = false;

		// Calls the GameStarted method for every player.
		player1.GameStarted(currentPlayer, player2.getUserName(), timeLimit);
		player2.GameStarted(!currentPlayer, player1.getUserName(), timeLimit);
	}
	
	/**
	 * Handles the case when a player disconects
	 * @param disconectedPlayer
	 */
	public void disconnect(PlayerConnection disconectedPlayer){
		if( disconectedPlayer == player1){
			player2.sendGameEnd(true);
			dbHandler.saveGame(player2.getUserName(), player1.getUserName(), timeLimit);
		}
		else if( disconectedPlayer == player2){
			player1.sendGameEnd(true);
			dbHandler.saveGame(player1.getUserName(), player2.getUserName(), timeLimit);
		}
		
		gameEnd = true;
	}
	
	/**
	 * Handles a new move made by player userName on position xy
	 * @param userName
	 * @param x
	 * @param y
	 */
	public synchronized void NewMove(String userName, int x, int y){
		if(userName.equals(player1.getUserName())){
			if(currentPlayer){
				System.out.println("preGameCheck");
				gameCheck(player1, player2, x, y, (short)1);
			}
			else{
				player1.sendIllegalMove();
			}
		}
		else if(userName.equals(player2.getUserName())){
			if(!currentPlayer){
				gameCheck(player2, player1, x, y, (short)2);
			}
			else{
				player2.sendIllegalMove();
			}
		}
		else
			System.out.println("oh gawd");
	}
	private void gameCheck(PlayerConnection activePlayer, PlayerConnection inactivePlayer, int x, int y, short symbol)
	{
		if(gameField[x][y] == 0){
			gameField[x][y] = symbol;

			if(this.VictoryCheck()){
				activePlayer.sendGameEnd(true);
				inactivePlayer.sendGameEnd(false);
				dbHandler.saveGame(activePlayer.getUserName(), inactivePlayer.getUserName(), timeLimit);
				gameEnd = true;

			}
			else if(this.checkBoardFull()){
				activePlayer.sendGameEnd(false);
				inactivePlayer.sendGameEnd(true);
				dbHandler.saveGame(inactivePlayer.getUserName(), activePlayer.getUserName(), timeLimit);
				gameEnd = true;
			}
			else{
				inactivePlayer.sendYourTurn(x, y);
			}

			currentPlayer = !currentPlayer;
		}
		else{
			activePlayer.sendIllegalMove();
		}
	}
		
	

	/**
	 * Changes how the game field. For testing only
	 * 
	 * @param testField
	 */
	public void setTestField(short[][] testField) {
		gameField = testField;
	}

	/**
	 * Checks if the current player has won the game
	 * 
	 * @return if the game is won
	 */
	public boolean VictoryCheck() {
		short symbol;

		if (currentPlayer)
			symbol = 1;
		else
			symbol = 2;

		for (int x = 0; x < gameField.length; x++) {
			for (int y = 0; y < gameField[0].length; y++) {
				if (horisontalLeft(x))
					if (horisontalCheck(x, y, symbol))
						return true;

				if (verticalLeft(y)) 
					if (verticalCheck(x, y, symbol))
						return true;
				
				if(horisontalLeft(x) && verticalLeft(y)){
					if (frontSlashCheck(x, y, symbol))
						return true;
					if (backSlashCheck(x, y, symbol))
						return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Keps track of the player times;
	 */
	public void run(){
		try{
			while(!gameEnd)
			{
				Thread.sleep(1000);
				if(gameEnd)
					break;
				
				if(currentPlayer){
					timeLeftP1--;
					if(timeLeftP1 <= 0)
					{
						player1.sendGameEnd(false);
						player2.sendGameEnd(true);
						dbHandler.saveGame(player2.getUserName(), player1.getUserName(), timeLimit);
						gameEnd = true;
						break;
					}
				}
				else{
					timeLeftP2--;
					if(timeLeftP2 <= 0)
					{
						player2.sendGameEnd(false);
						player1.sendGameEnd(true);
						dbHandler.saveGame(player1.getUserName(), player2.getUserName(), timeLimit);
						gameEnd = true;
						break;
					}
				}
				
				player1.sendTimeUpdate(timeLeftP1, timeLeftP2);
				player2.sendTimeUpdate(timeLeftP2, timeLeftP1);
			}
		}catch(InterruptedException e)
		{}
	}

	/**
	 * Checks if there is space left for a vertical check
	 * 
	 * @param y
	 * @return
	 */
	private boolean verticalLeft(int y) {
		return gameField[0].length > y + 4;
	}

	/**
	 * Checks if there is space left for a vertical check
	 * 
	 * @param y
	 * @return
	 */
	private boolean horisontalLeft(int x) {
		return gameField.length > x + 4;
	}

	/**
	 * Checks if the symbol going right is five in a row
	 * 
	 * @param x
	 * @param y
	 * @param symbol
	 * @return
	 */
	private boolean horisontalCheck(int x, int y, short symbol) {
		return gameField[x][y] == symbol && gameField[x + 1][y] == symbol
				&& gameField[x + 2][y] == symbol
				&& gameField[x + 3][y] == symbol
				&& gameField[x + 4][y] == symbol;
	}

	/**
	 * Checks if the symbol going down is five in a row
	 * 
	 * @param x
	 * @param y
	 * @param symbol
	 * @return
	 */
	private boolean verticalCheck(int x, int y, short symbol) {
		return gameField[x][y] == symbol && gameField[x][y + 1] == symbol
				&& gameField[x][y + 2] == symbol
				&& gameField[x][y + 3] == symbol
				&& gameField[x][y + 4] == symbol;
	}

	/**
	 * Checks if the symbol going diagonal is five in a row
	 * 
	 * @param x
	 * @param y
	 * @param symbol
	 * @return
	 */
	private boolean frontSlashCheck(int x, int y, short symbol) {
		return gameField[x][y] == symbol && gameField[x + 1][y + 1] == symbol
				&& gameField[x + 2][y + 2] == symbol
				&& gameField[x + 3][y + 3] == symbol
				&& gameField[x + 4][y + 4] == symbol;
	}

	/**
	 * Checks if the symbol going diagonal is five in a row
	 * 
	 * @param x
	 * @param y
	 * @param symbol
	 * @return
	 */
	private boolean backSlashCheck(int x, int y, short symbol) {
		return gameField[x + 4][y] == symbol
				&& gameField[x + 3][y + 1] == symbol
				&& gameField[x + 2][y + 2] == symbol
				&& gameField[x + 1][y + 3] == symbol
				&& gameField[x][y + 4] == symbol;
	}
	
	/**
	 * Check when game board is full
	 * 
	 * @param x
	 * @param y
	 * @return if its full
	 */
	
	public boolean checkBoardFull(){ 
		for (int x = 0; x < gameField.length; x++) {
			for (int y = 0; y < gameField.length; y++) {
				if (gameField[x][y] == 0)
					return false;
			}							
		}
		return true;
	}
}
			
		
        
    
