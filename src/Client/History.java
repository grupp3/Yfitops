package Client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Historyclass that creates a window containing 
 * a table with gamehistory for the player
 * 
 * NOT COMPLETED: when button "Return to lobby" is pressed 
 * 				  the correct method to call 
 *				  in GUIHandler class must be implemented
 * 
 * 
 * @author Jeanie
 *
 */
public class History extends JFrame {

	private JPanel contentPane;
	private JTable historyTable;
	private JScrollPane historyScrollPane;
	private JButton btnReturnToGUIHandler;
	private GUIHandler mGUIHandler;
	private String[] columnNames = {"Time of game", "Opponent", "Result"};
	
	

	/**
	 * Constructor creates a History window
	 * ads actionevents to the button
	 * 
	 * NOT COMPLETED: when button pressed correct method to call 
	 *				  in GUIHandler class must be implemented
	 */
	public History(GUIHandler guiHandler) {
		setResizable(false);
		
		//creates the windowframe
		setTitle("History");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		mGUIHandler = guiHandler;
		
		//creates the table
		historyTable = new JTable(null, columnNames);
		historyTable.setRowSelectionAllowed(false);
		historyScrollPane = new JScrollPane(historyTable);
		historyScrollPane.setBounds(10, 11, 414, 187);
		getContentPane().add(historyScrollPane);
		
		//creates the returnbutton
		btnReturnToGUIHandler = new JButton("Return to Main menu");
		
		//METHOD TO COMPLETE
		btnReturnToGUIHandler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGUIHandler.Show(Enum_Window.Lobby);
				dispose();
			}
		});
		btnReturnToGUIHandler.setBounds(224, 228, 200, 23);
		contentPane.add(btnReturnToGUIHandler);
	}



	public void insertData(String[][] data) {
		// TODO Auto-generated method stub
		
	}

	


	
	
	/**
	 * Launch the application.
	 * Is left for testing of the window
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					History frame = new History();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
}
