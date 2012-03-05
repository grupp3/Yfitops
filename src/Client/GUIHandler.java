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
					frame.setVisible(true);
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
		
		JButton button = new JButton("New button");
		button.setBounds(10, 45, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(10, 79, 89, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("New button");
		button_2.setBounds(10, 113, 89, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("New button");
		button_3.setBounds(10, 147, 89, 23);
		contentPane.add(button_3);
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

	public static void LoginUser(String userName, String password) {
		// TODO Auto-generated method stub	
	}

	public static void RegisterUser(String userName, String password) {
		// TODO Auto-generated method stub	
	}
}
