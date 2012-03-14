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
	private JButton btnReturnToGUIHandler;
	private GUIHandler mGUIHandler;
	private String[] columnNames = {"Player", "Number of wins"};

	/**
	 * Constructor creates a Highscore window
	 * ads actionevents to the button
	 * 
	 * NOT COMPLETED: when button pressed 
	 * 				  correct method to call 
	 *				  in GUIHandler class must be implemented
	 */
	public HighScore(GUIHandler guiHandler) {
		setResizable(false);
		
		//creates the windowframe
				
		setTitle("Highscore");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		mGUIHandler = guiHandler;
		
		//creates the table
		highscoreTable = new JTable(10, 2);
		highscoreTable.setRowSelectionAllowed(false);
		highscoreScrollPane = new JScrollPane(highscoreTable);
		highscoreScrollPane.setBounds(10, 11, 414, 187);
		getContentPane().add(highscoreScrollPane);
		highscoreTable.getColumnModel().getColumn(0).setHeaderValue("Player");
		highscoreTable.getColumnModel().getColumn(1).setHeaderValue("Number of wins");
		
		//creates the returnbutton
		btnReturnToGUIHandler = new JButton("Return to Main menu");
		
		//METHOD TO COMPLETE
		btnReturnToGUIHandler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGUIHandler.Show(Enum_Window.Lobby); 
			}
		});
		btnReturnToGUIHandler.setBounds(224, 228, 200, 23);
		contentPane.add(btnReturnToGUIHandler);
	}
	
	/**
	 * Sätter in highscoredata i tabellen
	 * @param data - Column data
	 */
	public void insertData(String[][] data) {
		for(int i = 0; i < data.length; i++)
			for(int j = 0; j < data[i].length; j++){
				if(data[i][j] != null)
					highscoreTable.getModel().setValueAt(data[i][j], i, j);
				else 
					highscoreTable.getModel().setValueAt("", i, j);
			}
					
		highscoreTable.setAutoCreateRowSorter(true);	
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
