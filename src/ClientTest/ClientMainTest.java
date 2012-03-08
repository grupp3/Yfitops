package ClientTest;

import static org.junit.Assert.*;

import org.junit.Test;
import Client.Login;

/**
 * Testing registration for a new user in Login class
 * @author Bernard
 *
 */
public class ClientMainTest {
	
	public void Register_username_password_sending(){
		Login NewRegister = new Login();
		String newuser = "User1";
		String newpass = "Pass1";
		NewRegister.textFieldUsername.setText(newuser);
		NewRegister.passwordField.setText(newpass);
		NewRegister.btnRegister.doClick();
	}
	
	/**
	 * Testing before registered user in sign in attempt
	 */
	public void Login_username_password_sending(){
		Login NewLogin = new Login();
		String registereduser = "User1";
		String registeredPass = "Pass1";
		NewLogin.textFieldUsername.setText(registereduser);
		NewLogin.passwordField.setText(registeredPass);
		NewLogin.btnLogin.doClick();
	}
}
