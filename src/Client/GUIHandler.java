package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
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

	public void LoginUser(String userName, String password) {
		// TODO Auto-generated method stub	
	}

	public void RegisterUser(String userName, String password) {
		// TODO Auto-generated method stub	
	}


}
