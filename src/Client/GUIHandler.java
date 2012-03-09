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

public class GUIHandler extends JFrame {

	private JPanel contentPane;
	public JButton btnLobby;
	public JButton btnHistory;
	public JButton btnGame;
	public JButton btnHighScore;

	
	
	/**
	 * Create the frame.
	 */
	public GUIHandler() {
		//setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login frame = new Login();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 11, 130, 23);
		contentPane.add(btnNewButton);
		
		btnLobby = new JButton("Lobby");
		btnLobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lobby frame = new Lobby();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLobby.setBounds(10, 45, 130, 23);
		contentPane.add(btnLobby);
		
		btnGame = new JButton("Game");
		btnGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Game frame = new Game();
				frame.setVisible(true);
				dispose();
			}
		});
		btnGame.setBounds(10, 79, 130, 23);
		contentPane.add(btnGame);
		
		btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				History frame = new History();
				frame.setVisible(true);
				dispose();
			}
		});
		btnHistory.setBounds(10, 113, 130, 23);
		contentPane.add(btnHistory);
		
		btnHighScore = new JButton("High Score");
		btnHighScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HighScore frame = new HighScore();
				frame.setVisible(true);
				dispose();
			}
		});
		btnHighScore.setBounds(10, 147, 130, 23);
		contentPane.add(btnHighScore);
	}


	/**
	 * NOT IMPLEMENTED YET
	 * 
	 * @param Enum_window
	 */
	public void Show(Object Enum_window){
		
	}
	
	
	public void HistorySwitch() {
		// TODO Auto-generated method stub
	}

	public void HighscoreSwitch() {
		// TODO Auto-generated method stub
	}

	public void ToggleGamingReady() {
		// TODO Auto-generated method stub	
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
	 */
	public static void RegisterUser(String userName, String password) {
		
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
		Login frame = new Login();
		frame.setVisible(true);
		dispose();
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
		Lobby frame = new Lobby();
		frame.setVisible(true);
		dispose();
	}



	public void NewGame(String string, boolean opponentStarting) {
		// TODO Auto-generated method stub
		
	}
}
