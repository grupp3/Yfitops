package ServerTest;

import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
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
		
		p.Register("register%håKan;lolL84");
		
		assertEquals("wrong username in connection class", "håKan", p.getUserName());
		assertEquals("wrong username to db handler", "håKan", db.userName);
		assertEquals("wrong password to db handler", "lolL84", db.password);
		assertEquals("wrong output", ServerProtocol.CreateLoggedIn(), os.out);
	}
	
	@Test
	public void UnaceptedRegisterToDBTest(){
		PlayerConnection p = new PlayerConnection();
		TestDB db = new TestDB();
		TestWriter os = new TestWriter();
		db.accept = false;
		p.addTestDBHandler(db);
		p.addTestDataWriter(os);
		
		p.Register("register%håKan;lolL84");
		
		assertEquals("wrong username in connection class", "", p.getUserName());
		assertEquals("wrong username to db handler", "håKan", db.userName);
		assertEquals("wrong password to db handler", "lolL84", db.password);
		assertEquals("wrong output", ServerProtocol.CreateRegisterFailed(), os.out);
	}
	
	@Test
	public void GameStartedTest(){
		PlayerConnection p = new PlayerConnection();
		TestWriter os = new TestWriter();
		p.addTestDataWriter(os);
		
		p.GameStarted(false, "håKan38", 0);
		
		assertEquals("wrong output", ServerProtocol.CreateGameStarted("håKan38", false, 0), os.out);
	}
	
	@Test
	public void GameStartedTest2(){
		PlayerConnection p = new PlayerConnection();
		TestWriter os = new TestWriter();
		p.addTestDataWriter(os);
		
		p.GameStarted(true, "agnes", 0);
		
		assertEquals("wrong output", ServerProtocol.CreateGameStarted("agnes", true, 0), os.out);
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
		p.addTestDataWriter(new TestWriter());
		p2.addTestDataWriter(new TestWriter());
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
		
		p.sendIllegalMove();
		
		assertEquals("wrong output", ServerProtocol.CreateIllegalMove(), os.out);
	}
	
	@Test
	public void SendYourTurnTest(){
		PlayerConnection p = new PlayerConnection();
		TestWriter os = new TestWriter();
		p.addTestDataWriter(os);
		
		p.sendYourTurn(12, 11);
		
		assertEquals("wrong output", ServerProtocol.CreateYourTurn(12, 11), os.out);
	}
	
	@Test
	public void SendGameEndTest(){
		PlayerConnection p = new PlayerConnection();
		TestWriter os = new TestWriter();
		p.addTestDataWriter(os);
		
		p.sendGameEnd(false);
		
		assertEquals("wrong output", ServerProtocol.CreateGameEnd(false), os.out);
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

}


    class TestWriter extends PrintWriter{
    	public String out;
	
		public TestWriter()  {
			super(new TestW());
		}
			
			@Override
			public void println(String out) {
				this.out = out;
			}
		
	}
    
    class TestW extends Writer{

		@Override
		public void close() throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void flush() throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void write(char[] cbuf, int off, int len) throws IOException {
			// TODO Auto-generated method stub
			
		}
    	
    }

	
