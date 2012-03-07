package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.Spring;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 73, 60, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 110, 60, 14);
		contentPane.add(lblPassword);
		
		textFieldUsername = new JTextField(10);
		textFieldUsername.setBounds(80, 70, 294, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	//Sends login data to the GUIHandler
				
				String userName = textFieldUsername.getText();
				String password = passwordField.getName();
				
				GUIHandler guiHandler = new GUIHandler();
				guiHandler.LoginUser(userName, password);
				
				
				GUIHandler frame = new GUIHandler();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogin.setBounds(10, 138, 170, 23);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	//Sends register data to the GUIHandler
				
				String UserName = textFieldUsername.getText();
				String Password = passwordField.getName();
				
				GUIHandler.RegisterUser(UserName, Password);
				
				GUIHandler frame = new GUIHandler();
				frame.setVisible(true);
				frame.setEnabled(true);
				dispose();
			}
		});
		btnRegister.setBounds(204, 138, 170, 23);
		contentPane.add(btnRegister);
		
		JLabel lblWelcomeText = new JLabel("Welcome fellow player!");
		lblWelcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWelcomeText.setForeground(Color.RED);
		lblWelcomeText.setBounds(10, 11, 364, 51);
		contentPane.add(lblWelcomeText);
		
		passwordField = new JPasswordField(10);
		passwordField.setBounds(80, 107, 294, 20);
		contentPane.add(passwordField);
	}
}


