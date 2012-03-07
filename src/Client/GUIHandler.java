package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIHandler extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIHandler frame = new GUIHandler();
					frame.setVisible(false);
					
					Login frame2 = new Login();
					frame2.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
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
		btnNewButton.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnLobby = new JButton("Lobby");
		btnLobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lobby frame = new Lobby();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLobby.setBounds(10, 45, 89, 23);
		contentPane.add(btnLobby);
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
}
