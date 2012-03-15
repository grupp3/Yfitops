package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.SwingConstants;
import javax.swing.JTable;

//import sun.font.FontFamily;

public class Game extends JFrame {

	private JPanel contentPane;
	JButton[][] buttonArray;
	private ActionListener ActionListener;
	private GUIHandler mGUIHandler;
	protected boolean yourTurn;
	private JLabel lblX;
	private JLabel lblO;
	private JLabel lblUser;
	private JLabel lblOpponent;
	private JLabel XuserTime;
	private JLabel OuserTime;
	int lastx, lasty;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Game frame = new Game();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * }); }
	 */

	/**
	 * Create the frame.
	 */
	public Game(GUIHandler guiHandler) {
		mGUIHandler = guiHandler;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		// Panel for player-labels
		JPanel panel = new JPanel();
		panel.setBounds(10, 5, 670, 50);
		contentPane.add(panel);
		panel.setLayout(null);

		lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblX.setBounds(57, 0, 53, 50);
		panel.add(lblX);

		lblO = new JLabel("O");
		lblO.setHorizontalAlignment(SwingConstants.CENTER);
		lblO.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblO.setBounds(300, 0, 53, 50);
		panel.add(lblO);
	
		lblOpponent = new JLabel("User1");
		lblOpponent.setHorizontalAlignment(SwingConstants.LEFT);
		lblOpponent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOpponent.setBounds(120, 0, 166, 50);
		panel.add(lblOpponent);

		lblUser = new JLabel("User2");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setBounds(350, 0, 194, 50);
		panel.add(lblUser);

		// The panel for gamefield

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(27, 126, 460, 460);
		contentPane.add(panel_1);

		panel_1.setLayout(new GridLayout(15, 15, 1, 1));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 66, 494, 49);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		XuserTime = new JLabel("0:00");
		XuserTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		XuserTime.setBounds(123, 0, 72, 49);
		panel_2.add(XuserTime);
		
		OuserTime = new JLabel("0:00");
		OuserTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		OuserTime.setBounds(352, 0, 72, 49);
		panel_2.add(OuserTime);

		buttonArray = new JButton[15][15];
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++) {
				JButton newButton = new JButton();
				buttonArray[i][j] = newButton;

				newButton.setMargin(new Insets(0, 0, 0, 0));
				newButton.setMaximumSize(new Dimension(1, 1));
				// Font font = new Font("Arial", Font.BOLD, 11);
				// newButton.setFont(font);

				panel_1.add(newButton);

				newButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("your turn"+ yourTurn);
						if (!yourTurn)
							return;
						JButton button = (JButton) arg0.getSource(); // Examinates
						
						// Examines the clicked field
						for (int i = 0; i < 15; i++)
							for (int j = 0; j < 15; j++) {
								if (buttonArray[i][j].equals(button)) {
									String buttonText = button.getText().trim();
										if (buttonText == "") {

											lastx = i;
											lasty = j;
											button.setText("O");
											yourTurn = false;
											lblUser.setForeground(Color.RED);
											lblOpponent.setForeground(Color.GREEN);
											
											mGUIHandler.makeMove(lastx, lasty);
										}
								}
							}
					}

				});

			}
		panel_1.setVisible(true);
	}

	/**
	 * Resets the game state for a new game
	 * @param userName
	 * @param opponentName
	 * @param youStarting
	 */
	public void reset(String userName, String opponentName, boolean youStarting) {
		lblUser.setText(userName);
		lblOpponent.setText(opponentName);
		yourTurn = youStarting;

		if (yourTurn) {
			lblUser.setForeground(Color.GREEN);
			lblOpponent.setForeground(Color.RED);
		}
		else {
			lblUser.setForeground(Color.RED);
			lblOpponent.setForeground(Color.GREEN);
		}

		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				buttonArray[i][j].setText("");
		System.out.println("resetted");
	}

	/**
	 * adds the opponents move and changes to your turn
	 * @param x
	 * @param y
	 */
	public void yourMove(int x, int y) {
		buttonArray[x][y].setText("X");
		yourTurn = true;
		//lblUser.setForeground(Color.GREEN);
		lblUser.setForeground(Color.GREEN);
		lblOpponent.setForeground(Color.RED);
	}

	/**
	 * updates the game time
	 * @param yourTime
	 * @param opponentTime
	 */
	public void uppdateTime(int yourTime, int opponentTime) {
		int minutes = yourTime / 60;
		int seconds = yourTime % 60;
		
		OuserTime.setText(minutes + ":" + seconds);
		
		minutes = opponentTime / 60;
		seconds = opponentTime % 60;
		
		XuserTime.setText(minutes + ":" + seconds);
	}
}
