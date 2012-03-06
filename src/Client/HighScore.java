package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 * Highscoreclass that creates a window containing 
 * a table with the top 10 players
 * 
 * NOT COMPLETED: when button "Return to lobby" is pressed 
 * 				  the correct method to call 
 *				  in GUIHandler class must be implemented
 * 
 * 
 * @author Jeanie
 *
 */
public class HighScore extends JFrame {

	private JPanel contentPane;

	private JTable highscoreTable;
	private JScrollPane highscoreScrollPane;
	private JButton btnReturnToLobby;
	private GUIHandler mGUIHandler;
	private String[] columnNames = {"Player", "Wins"};

	/**
	 * Constructor creates a Highscore window
	 * ads actionevents to the button
	 * 
	 * NOT COMPLETED: when button pressed 
	 * 				  correct method to call 
	 *				  in GUIHandler class must be implemented
	 */
	public HighScore() {
		
		//creates the windowframe
				
		setTitle("Highscore");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		mGUIHandler = new GUIHandler();
		
		//creates the table
		highscoreTable = new JTable(mGUIHandler.InsertHighScore(), columnNames);
		highscoreTable.setRowSelectionAllowed(false);
		highscoreScrollPane = new JScrollPane(highscoreTable);
		highscoreScrollPane.setBounds(10, 11, 414, 187);
		getContentPane().add(highscoreScrollPane);
		
		//creates the returnbutton
		btnReturnToLobby = new JButton("Return to lobby");
		
		//METHOD TO COMPLETE
		btnReturnToLobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//mGUIHandler.Show(Lobby);
			}
		});
		btnReturnToLobby.setBounds(283, 228, 141, 23);
		contentPane.add(btnReturnToLobby);
	}

	/**
	 * Launch the application.
	 * Is left for testing of the window
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HighScore frame = new HighScore();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
}
