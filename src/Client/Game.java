package Client;

import java.awt.BorderLayout;
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

import javax.swing.SwingConstants;
import javax.swing.JTable;

import sun.font.FontFamily;

public class Game extends JFrame {

	private JPanel contentPane;
	private JTable table;
	JButton[][] buttonArray;
	private ActionListener ActionListener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		//Panel for player-labels
		JPanel panel = new JPanel();
		panel.setBounds(10, 5, 670, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblX.setBounds(57, 0, 53, 50);
		
		panel.add(lblX);
		
		JLabel lblO = new JLabel("O");
		lblO.setHorizontalAlignment(SwingConstants.CENTER);
		lblO.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblO.setBounds(300, 0, 53, 50);
		panel.add(lblO);
		
		JLabel lblUser = new JLabel("User1");
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser.setBounds(120, 0, 166, 50);
		panel.add(lblUser);
		
		JLabel lblUser_1 = new JLabel("User2");
		lblUser_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser_1.setBounds(350, 0, 194, 50);
		panel.add(lblUser_1);
		
		
		//The panel for gamefield
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(27, 66, 460, 460);
		contentPane.add(panel_1);
		
		panel_1.setLayout(new GridLayout(15, 15, 1, 1));
		
		buttonArray = new JButton[15][15];
		for(int i = 0; i<15; i++)
			for(int j=0; j<15; j++){
				JButton newButton= new JButton();
				buttonArray[i][j] = newButton;				
				
				newButton.setMargin(new Insets(0,0,0,0));
				newButton.setMaximumSize(new Dimension(1,1));
				Font font = new Font("Arial", Font.BOLD, 11);
				newButton.setFont(font);				
				
				panel_1.add(newButton);
				
				ActionListener = new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JButton button = (JButton) arg0.getSource(); //Examinates which button was clicked
						int x,y;
						
						for(int i=0; i<15; i++)
							for(int j=0; j<15; j++){
								if(buttonArray[i][j].equals(button));
									x=i;
									y=j;
							}				
						
						//HERE CALL THE METHOD MakeMove() on GUIHandler
					}
				};				
			}
		panel_1.setVisible(true);
	 }
}
