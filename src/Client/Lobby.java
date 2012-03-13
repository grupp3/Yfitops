package Client;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Creates a window where the player can
 * navigate to history, highscore and start a game
 * 
 * @author Jeanie
 *
 */
public class Lobby extends JFrame {

	private GUIHandler mGUIHandler;
	private JPanel contentPane;
	private JButton btnPlay;
	private JButton btnHistory;
	private JButton btnHighscore;
	private JLabel lblStatus;
	private boolean playbuttonActivated;
	

	/**
	 * Constructor creates a lobbywindow
	 * ads actionevents to the button
	 * 
	 * @author Jeanie
	 */
	public Lobby(GUIHandler guiHandler) {
		
		//creates the windowframe
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Lobby");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mGUIHandler = guiHandler;
		
		//creates the playbutton
		playbuttonActivated = false;
		btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!playbuttonActivated) {
					playPressed();
				}
				else if (playbuttonActivated) {
					cancelPressed();
				}
			}
		});
		btnPlay.setBounds(295, 216, 113, 23);
		contentPane.add(btnPlay);
		
		//creates the historybutton
		btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGUIHandler.HistorySwitch();
			}
		});
		btnHistory.setBounds(25, 167, 116, 23);
		contentPane.add(btnHistory);
		
		//creates the highscorebutton
		btnHighscore = new JButton("HighScore");
		btnHighscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mGUIHandler.HighscoreSwitch();
			}
		});
		btnHighscore.setBounds(25, 216, 116, 23);
		contentPane.add(btnHighscore);
		
		//creates the statuslabel
		lblStatus = new JLabel("Waiting for opponent");
		lblStatus.setBounds(28, 28, 189, 14);
		lblStatus.setVisible(false);
		contentPane.add(lblStatus);
	}
	/**
	 * method called by the play button
	 * changes the buttontext to cancel
	 * shows the statuslabel 
	 * calls ToggleGamingReady method in GUIHandler class
	 * 
	 * @author Jeanie
	 * @author Mathias
	 */
	private void playPressed() {
		playbuttonActivated = true;
		btnPlay.setText("Cancel");
		lblStatus.setVisible(true);
		Object[] options = {"Unlimited", "3 min", "5 min", "7 min"};
		int choise = JOptionPane.showOptionDialog(this, "Choose time limit!", "Time Choise", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		switch(choise){
		case 0:
			mGUIHandler.ToggleGamingReady(0);
			break;
		case 1:
			mGUIHandler.ToggleGamingReady(3);
			break;
		case 2:
			mGUIHandler.ToggleGamingReady(5);
			break;
		case 3:
			mGUIHandler.ToggleGamingReady(7);
			break;
		}
		
	}
	/**
	 * method called by the cancel button (play button)
	 * changes the button text to play
	 * hides the statuslabel
	 * 
	 * calls ToggleGamingReady method in GUIHandler class
	 * 
	 * @author Jeanie
	 */
	private void cancelPressed() {
		playbuttonActivated = false;
		btnPlay.setText("Play");
		lblStatus.setVisible(false);
		mGUIHandler.ToggleGamingReady(0);
	}
	public void reset() {
		playbuttonActivated = false;
		btnPlay.setText("Play");
		lblStatus.setVisible(false);
	}
}
