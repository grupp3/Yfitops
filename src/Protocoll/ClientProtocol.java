package Protocoll;

public class ClientProtocol {

	public static String CreateRegister(String userName, String password) {
		return "register%" + userName +";" + password;
	}

}
