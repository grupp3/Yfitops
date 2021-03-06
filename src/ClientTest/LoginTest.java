package ClientTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Client.GUIHandler;
import Client.Login;

/**
 * Testing registration for a new user in Login class
 * @author Bernard
 *
 */
public class LoginTest {
	
	private GUIHandler mGUIHandler;
	
	@Test
	public void Register_username_password_sending(){
		Login NewRegister = new Login(mGUIHandler);
		String newuser = "User1";
		String newpass = "Pass1";
		NewRegister.textFieldUsername.setText(newuser);
		NewRegister.passwordField.setText(newpass);
		NewRegister.btnRegister.doClick();
	}
	
	/**
	 * Testing before registered user in sign in attempt
	 */
	@Test
	public void Login_username_password_sending(){
		Login NewLogin = new Login(mGUIHandler);
		String registereduser = "User1";
		String registeredPass = "Pass1";
		NewLogin.textFieldUsername.setText(registereduser);
		NewLogin.passwordField.setText(registeredPass);
		NewLogin.btnLogin.doClick();
	}
}