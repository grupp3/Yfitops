package ServerTest;

import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

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
		
		p.Register("register%h�Kan;lolL84");
		
		assertEquals("wrong username in connection class", "h�Kan", p.getUserName());
		assertEquals("wrong username to db handler", "h�Kan", db.userName);
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
		
		p.Register("register%h�Kan;lolL84");
		
		assertEquals("wrong username in connection class", "", p.getUserName());
		assertEquals("wrong username to db handler", "h�Kan", db.userName);
		assertEquals("wrong password to db handler", "lolL84", db.password);
		assertEquals("wrong output", expectedOutInt, os.out);
	}
	
	@Test
	public void GameStartedTest(){
		PlayerConnection p = new PlayerConnection();
		TestWriter os = new TestWriter();
		p.addTestDataWriter(os);
		String expectedOut = Protocoll.ServerProtocol.CreateGameStarted("h�Kan38", false);
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		
		p.GameStarted(false, "h�Kan38");
		
		assertEquals("wrong output", expectedOutInt, os.out);
	}
	
	@Test
	public void GameStartedTest2(){
		PlayerConnection p = new PlayerConnection();
		TestWriter os = new TestWriter();
		p.addTestDataWriter(os);
		String expectedOut = Protocoll.ServerProtocol.CreateGameStarted("agnes", true);
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		
		p.GameStarted(true, "agnes");
		
		assertEquals("wrong output", expectedOutInt, os.out);
	}
	
	@Test
	public void ToggleGamingNoOpponentTest(){
		PlayerConnection p = new PlayerConnection();
		ArrayList<PlayerConnection> testList = new ArrayList<PlayerConnection>();
		testList.add(p);
		testList.add(new PlayerConnection());
		testList.add(new PlayerConnection());
		p.addTestPlayerConnectionList(testList);
		
		p.gamingCheck();
		
		assertTrue(p.getGamingRedy());
	}
	
	@Test
	public void ToggleGamingOpponentExistTest(){
		PlayerConnection p = new PlayerConnection();
		PlayerConnection p2 = new PlayerConnection();
		ArrayList<PlayerConnection> testList = new ArrayList<PlayerConnection>();
		testList.add(p);
		testList.add(p2);
		testList.add(new PlayerConnection());
		p.addTestPlayerConnectionList(testList);
		
		p2.setGamingRedy(true);
		p.gamingCheck();
		
		assertFalse(p.getGamingRedy());
	}
	
	@Test
	public void ToggleGamingWasAlredyTrueTest(){
		PlayerConnection p = new PlayerConnection();
		ArrayList<PlayerConnection> testList = new ArrayList<PlayerConnection>();
		testList.add(p);
		testList.add(new PlayerConnection());
		testList.add(new PlayerConnection());
		p.addTestPlayerConnectionList(testList);
		
		p.gamingCheck();
		p.gamingCheck();
		
		assertFalse(p.getGamingRedy());
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
