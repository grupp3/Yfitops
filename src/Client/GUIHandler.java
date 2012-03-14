package Client;


import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import Protocoll.ClientProtocol;
import Protocoll.ServerProtocol;


public class GUIHandler {

	private ClientMain mConnection;
	private Login login;
	private Game game;
	private Lobby lobby;
	private HighScore highScore;
	private History history;
	private String userName;
	
	/**
	 * Create the frame.
	 */
	public GUIHandler(ClientMain connection) {
		mConnection = connection;
		
		this.login = new Login(this);
		this.game = new Game(this);
		this.lobby = new Lobby(this);
		this.highScore = new HighScore(this);
		this.history = new History(this);
		this.hideAllWindows();
		this.Show(Enum_Window.Login);
	}
	
	/**
	 * getter
	 * @return username
	 */
	public String getUserName(){
		return this.userName;
	}
	/**
	 * Shows the window that
	 * is sent into the method
	 * 
	 * @param Enum_window
	 * 					 the window to show
	 * 
	 * @author Jeanie
	 * 
	 */
	public void Show(Enum_Window window){

		switch(window){
		
		case Login: 
			hideAllWindows();
			login.setVisible(true);
			break;
			
		case Lobby:
			hideAllWindows();
			lobby.setVisible(true);
			break;
			
		case Game:
			hideAllWindows();
			game.setVisible(true);
			break;
		
		case History:
			hideAllWindows();
			history.setVisible(true);
			break;
			
		case Highscore:
			hideAllWindows();
			highScore.setVisible(true);
			break;
		}			
	}
	
	private void hideAllWindows(){
		login.setVisible(false);
		game.setVisible(false);
		lobby.setVisible(false);
		highScore.setVisible(false);
		history.setVisible(false);
	}
	/**
	 *
	 */
	public void HistorySwitch() {
		mConnection.historyRequest();
	}

	/**
	 *
	 */
	public void HighscoreSwitch() {
		mConnection.highScoreRequest();
	}

	/**
	 * Calls the ToggelGamingReady method 
	 * in ClientMain class
	 * @author Jeanie
	 */
	public void ToggleGamingReady(int timeLimit) {
		mConnection.ToggleGamingReady(timeLimit);
	}

	/**
	 * Sends login information to ClientMain
	 */
	public void LoginUser(String userName, String password) {
		
		mConnection.loginUsr(userName, password);
		this.userName = userName;
	}
	
	public void LoginFailed(){
		JOptionPane optionPane = new JOptionPane("The password didn't match the username!", JOptionPane.INFORMATION_MESSAGE);
		JDialog popup = optionPane.createDialog(null, "Login failed");
		popup.setModal(true);
		popup.setVisible(true);
	}

	/**
	 * Register a new player with new username and password
	 * @param userName
	 * @param password
	 * @author Pernilla
	 */
	public void RegisterUser(String userName, String password) {
		String FromProtocol = ClientProtocol.CreateRegister(userName, password);
		mConnection.sendRequest(FromProtocol);
		this.userName = userName;
	}
	
	/**
	 * NOT IMPLEMENTED YET 
	 * 						contains hardcoded field for the moment
	 * 
	 * @return history field from connection -> protocol class -> fetched from server -> database
	 * 		
	 * @author Jeanie
	 * @param data 
	 */
	public void InsertHistory(String[][] data) {
		history.insertData(data);
		Show(Enum_Window.History);
	}
	
	/**
	 * @author Mathias	
	 * @param data 
	 */
	public void InsertHighScore(String[][] data) {
		highScore.insertData(data);
		Show(Enum_Window.Highscore);
	}
	
	/**
	 * Shows a popup window 
	 * informating the player that 
	 * the registration failed
	 * links back to the login window
	 * 
	 * @author Jeanie
	 */
	public void registerFailed() {
		JOptionPane optionPane = new JOptionPane("Your registration failed. Please try again.", JOptionPane.INFORMATION_MESSAGE);
		JDialog popup = optionPane.createDialog(null, "Registration failure");
		popup.setModal(true);
		popup.setVisible(true);
		//switchToLogin();
		//Show(Enum_Window.Login);
	}
	/**
	 * Shows a popup window 
	 * informating the player that 
	 * the registration succeeded
	 * links to the lobby window
	 * 
	 * @author Jeanie
	 */
	 public void login() {
		JOptionPane optionPane = new JOptionPane("Welcome "+ this.userName, JOptionPane.INFORMATION_MESSAGE);
		JDialog popup = optionPane.createDialog(null, "Login success");
		popup.setModal(true);
		popup.setVisible(true);
		//switchToLobby();
		Show(Enum_Window.Lobby);
	}


	 /**
	  * starts a new game with 2 players
	  * @param opponentName
	  * @param opponentStarting
	  */
	public void NewGame(String opponentName, boolean youStarting) {
		game.reset(userName, opponentName, youStarting);
		lobby.reset();
		this.Show(Enum_Window.Game);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void makeMove(int x, int y) {
		mConnection.sendRequest(ClientProtocol.CreateNewMove(x, y));
	}

	public void yourTurn(int x, int y) {
		game.yourMove(x,y);
	}

	public void gameEnd(boolean victory) {
		if(victory)
			JOptionPane.showMessageDialog(game, "You won!");
		else
			JOptionPane.showMessageDialog(game, "You lost...");
		
		this.Show(Enum_Window.Lobby);
	}

	public void illegalMove() {
		System.out.println("illegal move !!!!!");
		// TODO Auto-generated method stub
	}

	public void UpdateTimes(int yourTime, int opponentTime) {
		game.uppdateTime(yourTime, opponentTime);
	}
}
