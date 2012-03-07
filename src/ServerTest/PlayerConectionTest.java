package ServerTest;

import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Protocoll.ServerProtocol;
import Server.*;
import org.junit.Test;

public class PlayerConectionTest {

	@Test
	public void RegisterToDBTest() {
		PlayerConnection p = new PlayerConnection();
		TestDB db = new TestDB();
		TestWriter os = new TestWriter();
		db.accept = true;
		p.addTestDBHandler(db);
		p.addTestDataWriter(os);
		String expectedOut = Protocoll.ServerProtocol.CreateLoggedIn();
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		
		p.Register("register%håKan;lolL84");
		
		assertEquals("wrong username in connection class", "håKan", p.getUserName());
		assertEquals("wrong username to db handler", "håKan", db.userName);
		assertEquals("wrong password to db handler", "lolL84", db.password);
		assertEquals("wrong output", expectedOutInt, os.out);
		
	}
	
	@Test
	public void UnaceptedRegisterToDBTest(){
		PlayerConnection p = new PlayerConnection();
		TestDB db = new TestDB();
		TestWriter os = new TestWriter();
		db.accept = false;
		p.addTestDBHandler(db);
		p.addTestDataWriter(os);
		String expectedOut = Protocoll.ServerProtocol.CreateRegisterFailed();
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		
		p.Register("register%håKan;lolL84");
		
		assertEquals("wrong username in connection class", "", p.getUserName());
		assertEquals("wrong username to db handler", "håKan", db.userName);
		assertEquals("wrong password to db handler", "lolL84", db.password);
		assertEquals("wrong output", expectedOutInt, os.out);
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
	
	class TestWriter extends OutputStream{
		public int out;
		
		public TestWriter() {
		}

		@Override
		public void write(int b) throws IOException {
			out = b;
		}
	}

}
