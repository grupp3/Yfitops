package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private GUIHandler mGUIHandler;
	private JPanel contentPane;
	public JTextField textFieldUsername;
	public JPasswordField passwordField;
	public JButton btnLogin;
	public JButton btnRegister;

	/**
	 * Create the frame.
	 */
	public Login(GUIHandler guiHandler) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mGUIHandler = guiHandler;

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 73, 60, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 110, 60, 14);
		contentPane.add(lblPassword);

		textFieldUsername = new JTextField(10);
		textFieldUsername.setBounds(80, 70, 294, 20);
		contentPane.add(textFieldUsername);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Sends login data
															// to the GUIHandler
				System.out.println("klicka Login!");
				loginButtonPressedHelpMethod();
			}
		});
		btnLogin.setBounds(10, 138, 170, 23);
		contentPane.add(btnLogin);

		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Sends register
															// data to the
															// GUIHandler
				registerButtonPressedHelpMethod();
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

	/**
	 * Helpmethod for loginButton
	 */
	private void loginButtonPressedHelpMethod() {
		if(!validateForm("Login failed"))
			return;
		
		String userName = textFieldUsername.getText();
		String password = new String(passwordField.getPassword());
		mGUIHandler.LoginUser(userName, password);
	}

	/**
	 * Helpmethod for registerButton
	 */
	private void registerButtonPressedHelpMethod() {
		if (!validateForm("Registration failed"))
			return;
		
		String UserName = textFieldUsername.getText();
		String Password = new String(passwordField.getPassword());
		mGUIHandler.RegisterUser(UserName, Password);
	}

	/**
	 * Creates and shows a popup-window
	 * 
	 * @param messageHead
	 *            - the headline
	 * @param messageBody
	 *            - the body
	 */
	private void showPopUp(String messageHead, String messageBody) {
		JOptionPane optionPane = new JOptionPane(messageBody, JOptionPane.INFORMATION_MESSAGE);
		JDialog popup = optionPane.createDialog(null, messageHead);
		popup.setModal(true);
		popup.setVisible(true);
	}

	/**
	 * Validates the form and shows an errormessage if neccesary
	 * @param errorMessage - The head of an eventual errormessage
	 * @return true upon validation success
	 */
	private boolean validateForm(String errorMessage) {
		if (textFieldUsername.getText().length() > 10) {
			showPopUp(errorMessage, "Usernames can be no longer than 10 characters.");
			return false;
		}
		
		if (passwordField.getPassword().length > 10) {
			showPopUp(errorMessage, "Passwords can be no longer than 10 characters.");
			return false;
		}
		
		if (!textFieldUsername.getText().trim().matches("\\w*")){
			showPopUp(errorMessage, "Usernames can only contain alphanumeric characters.");
			return false;
		}
		
		if (!(new String(passwordField.getPassword()).matches("\\w*"))){
				showPopUp(errorMessage, "Passwords can only contain alphanumeric characters.");
			return false;
		}
		return true;
	}
}
