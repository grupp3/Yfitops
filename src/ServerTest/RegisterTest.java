package ServerTest;

import static org.junit.Assert.*;
import Server.*;
import org.junit.Test;

public class RegisterTest {

	@Test
	public void RegisterToDBTest() {
		PlayerConnection p = new PlayerConnection();
		TestDB db = new TestDB();
		db.accept = true;
		p.addTestDBHandler(db);
		
		p.Register("register%håKan;lolL84");
		
		assertEquals("wrong username in connection class", "håKan", p.getUserName());
		assertEquals("wrong username to db handler", "håKan", db.userName);
		assertEquals("wrong password to db handler", "lolL84", db.password);
	}
	
	class TestDB extends DBHandler{
		public String userName;
		public String password;
		public boolean accept;
		
		public TestDB(){}
		
		public boolean registerUser(String userName, String password){
			this.userName = userName;
			this.password = password;
			return accept;
		}
	}

}
