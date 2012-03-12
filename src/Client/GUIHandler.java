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


public class GUIHandler extends JFrame {

	private JPanel contentPane;
	public JButton btnLobby;
	public JButton btnHistory;
	public JButton btnGame;
	public JButton btnHighScore;
	private ClientMain mConnection;
	
	/**
	 * Create the frame.
	 */
	public GUIHandler(ClientMain connection) {
		//setEnabled(false);
		connection = mConnection;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Show(Enum_Window.Login);
			}
		});
		btnNewButton.setBounds(10, 11, 130, 23);
		contentPane.add(btnNewButton);
		
		btnLobby = new JButton("Lobby");
		btnLobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Show(Enum_Window.Lobby);
			}
		});
		btnLobby.setBounds(10, 45, 130, 23);
		contentPane.add(btnLobby);
		
		btnGame = new JButton("Game");
		btnGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Show(Enum_Window.Game);
			}
		});
		btnGame.setBounds(10, 79, 130, 23);
		contentPane.add(btnGame);
		
		btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Show(Enum_Window.History);
			}
		});
		btnHistory.setBounds(10, 113, 130, 23);
		contentPane.add(btnHistory);
		
		btnHighScore = new JButton("High Score");
		
		btnHighScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Show(Enum_Window.Highscore);
			}
		});
		btnHighScore.setBounds(10, 147, 130, 23);
		contentPane.add(btnHighScore);
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
			Login loginWin = new Login(this);
			loginWin.setVisible(true);
			dispose();
			break;
			
		case Lobby:
			Lobby lobbyWin = new Lobby(this);
			lobbyWin.setVisible(true);
			dispose();
			break;
			
		case Game:
			Game gameWin = new Game(this);
			gameWin.setVisible(true);
			dispose();
			break;
		
		case History:
			History historyWin = new History(this);
			historyWin.setVisible(true);
			dispose();
			break;
			
		case Highscore:
			HighScore frame = new HighScore(this);
			frame.setVisible(true);
			dispose();
			break;
		}			
	}
	
	/**
	 * NOT IMPLEMENTED YET
	 */
	public void HistorySwitch() {
		// TODO Auto-generated method stub
	}

	/**
	 * NOT IMPLEMENTED YET
	 */
	public void HighscoreSwitch() {
		// TODO Auto-generated method stub
	}

	/**
	 * Calls the ToggelGamingReady method 
	 * in ClientMain class
	 * 
	 * @author Jeanie
	 */
	public void ToggleGamingReady() {
		mConnection.ToggleGamingReady();
	}

	/**
	 * Sends login information to ClientMain
	 */
	public static void LoginUser(String userName, String password) {
		
		ClientMain.loginUsr(userName, password);
	}
	
	public void LoginFailed(){
		
		
	}

	/**
	 * Register a new player with new username and password
	 * @param userName
	 * @param password
	 * @author Pernilla
	 */
	public static void RegisterUser(String userName, String password) {
		String FromProtocol = ClientProtocol.CreateRegister(userName, password);
		try {
			ClientMain.sendRequest(FromProtocol);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
//		int lenght;
//		
//		lenght = userName.length();
//		if(lenght > 10){
//			return false;
//		}
		
		//if(userName.equals("") || password.equals("")){
			
		//}
	}
	
	/**
	 * NOT IMPLEMENTED YET 
	 * 						contains hardcoded field for the moment
	 * 
	 * @return history field from connection -> protocol class -> fetched from server -> database
	 * 		
	 * @author Jeanie
	 */
	public String[][] InsertHistory() {
		
		String[][] columnContent = {{"Game 1", "Adam", "win"},{"Game 2", "Bertil", "win"},
									{"Game 3", "Carl", "loss"},{"Game 4", "David", "loss"},
									{"Game 5", "Erik", "win"},{"Game 6", "Fredrik", "win"},
									{"Game 7", "Gustav","loss"},{"Game 8", "Hans", "loss"},
									{"Game 9", "Ingmar", "win"},{"Game 10", "Johan", "win"}};
		return columnContent;
	}
	/**
	 *  NOT IMPLEMENTED YET 
	 * 						contains hardcoded field for the moment
	 * 
	 * @return highscore field from connection -> protocol class -> fetched from server -> database
	 * 	
	 * @author Jeanie	
	 */
	public String[][] InsertHighScore() {
		
		String[][] columnContent = {{"Adam", "10"},{"Bertil", "9"},
									{"Carl", "8"},{"David", "7"},
									{"Erik", "6"},{"Fredrik", "5"},
									{"Gustav","4"},{"Hans", "3"},
									{"Ingmar", "2"},{"Johan", "1"}};
		return columnContent;
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
		Show(Enum_Window.Login);
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
		JOptionPane optionPane = new JOptionPane("Welcome, you are now registered", JOptionPane.INFORMATION_MESSAGE);
		JDialog popup = optionPane.createDialog(null, "Registration success");
		popup.setModal(true);
		popup.setVisible(true);
		//switchToLobby();
		Show(Enum_Window.Lobby);
	}



	public void NewGame(String string, boolean opponentStarting) {
		// TODO Auto-generated method stub
		
	}
}
