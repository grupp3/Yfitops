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
		os.out= 0;
		
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
		os.out= 0;
		
		p.Register("register%håKan;lolL84");
		
		assertEquals("wrong username in connection class", "", p.getUserName());
		assertEquals("wrong username to db handler", "håKan", db.userName);
		assertEquals("wrong password to db handler", "lolL84", db.password);
		assertEquals("wrong output", expectedOutInt, os.out);
	}
	
	@Test
	public void GameStartedTest(){
		PlayerConnection p = new PlayerConnection();
		TestWriter os = new TestWriter();
		p.addTestDataWriter(os);
		String expectedOut = Protocoll.ServerProtocol.CreateGameStarted("håKan38", false, 0);
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		os.out= 0;
		
		p.GameStarted(false, "håKan38", 0);
		
		assertEquals("wrong output", expectedOutInt, os.out);
	}
	
	@Test
	public void GameStartedTest2(){
		PlayerConnection p = new PlayerConnection();
		TestWriter os = new TestWriter();
		p.addTestDataWriter(os);
		String expectedOut = Protocoll.ServerProtocol.CreateGameStarted("agnes", true, 0);
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		os.out= 0;
		
		p.GameStarted(true, "agnes", 0);
		
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
		
		p.gamingCheck("gamingredy%0");
		
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
		p.gamingCheck("gamingredy%0");
		
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
		
		p.gamingCheck("gamingredy%0");
		p.gamingCheck("gamingredy%0");
		
		assertFalse(p.getGamingRedy());
	}
	
	@Test
	public void SendIllegalMoveTest(){
		PlayerConnection p = new PlayerConnection();
		TestWriter os = new TestWriter();
		p.addTestDataWriter(os);
		String expectedOut = Protocoll.ServerProtocol.CreateIllegalMove();
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		os.out= 0;
		
		p.sendIllegalMove();
		
		assertEquals("wrong output", expectedOutInt, os.out);
	}
	
	@Test
	public void SendYourTurnTest(){
		PlayerConnection p = new PlayerConnection();
		TestWriter os = new TestWriter();
		p.addTestDataWriter(os);
		String expectedOut = Protocoll.ServerProtocol.CreateYourTurn(12,11);
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		os.out= 0;
		
		p.sendYourTurn(12, 11);
		
		assertEquals("wrong output", expectedOutInt, os.out);
	}
	
	@Test
	public void SendGameEndTest(){
		PlayerConnection p = new PlayerConnection();
		TestWriter os = new TestWriter();
		p.addTestDataWriter(os);
		String expectedOut = Protocoll.ServerProtocol.CreateGameEnd(false);
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		os.out= 0;
		
		p.sendGameEnd(false);
		
		assertEquals("wrong output", expectedOutInt, os.out);
	}
	
	class TestDB extends DBHandler{
		public String userName;
		public String password;
		public boolean accept;
		
		public TestDB(){super(true);}
		
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
