package ServerTest;

import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Test;

import Server.Game;
import Server.PlayerConnection;



/**
 * Tests correctness of class Server.Game
 * 
 * @author Kamil
 * 
 */
public class GameTest {

	@Test
	public void ConstruktorExceptionTest() throws UnknownHostException, IOException {
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2,0);
	}
	
	@Test
	public void VictoryHorisontalTest() throws UnknownHostException, IOException
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2,0);
		
		short[][] testField =	{{1,1,0,2,2,2,1,1,1}
							  	,{1,1,0,0,0,1,1,1,2}
							  	,{0,0,0,1,1,0,1,1,2}
							  	,{1,1,0,0,0,2,1,0,2}
							  	,{0,0,0,1,2,1,1,1,2}
							  	,{0,2,2,2,0,2,2,2,2}
							  	,{0,0,0,0,1,0,0,0,2}
							  	,{0,0,2,0,0,0,2,0,0}
							  	,{0,0,0,1,0,2,0,0,0}}; 
		
		game.setTestField(testField);
		boolean victory = game.VictoryCheck();
		
		assertTrue(victory);
	}
	
	@Test
	public void VictoryVerticalTest() throws UnknownHostException, IOException
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2, 0);
		
		short[][] testField =	{{1,1,0,2,2,2,1,1,1}
							  	,{1,1,0,0,0,1,1,1,2}
							  	,{0,0,0,1,1,1,1,1,2}
							  	,{1,1,0,0,0,0,1,0,0}
							  	,{0,0,0,1,2,1,2,1,2}
							  	,{0,2,2,2,2,2,2,2,2}
							  	,{0,0,0,0,1,0,0,0,2}
							  	,{0,0,2,0,0,0,2,0,0}
							  	,{0,0,0,1,0,2,0,0,0}}; 
		
		game.setTestField(testField);
		boolean victory = game.VictoryCheck();
		
		assertTrue(victory);
	}
	
	@Test
	public void VictoryFrontslashTest() throws UnknownHostException, IOException
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2,0);
		
		short[][] testField =	{{1,1,0,2,2,2,1,1,1}
							  	,{1,1,0,0,1,1,1,1,2}
							  	,{0,0,0,2,2,1,1,1,2}
							  	,{1,1,0,0,0,2,1,0,2}
							  	,{0,0,0,1,2,1,2,1,2}
							  	,{0,2,2,2,2,1,2,2,1}
							  	,{0,0,0,0,1,0,0,0,2}
							  	,{0,0,2,0,0,0,2,0,0}
							  	,{0,0,0,1,0,2,0,0,0}}; 
		
		game.setTestField(testField);
		boolean victory = game.VictoryCheck();
		
		assertTrue(victory);
	}
	
	@Test
	public void VictoryBackslashTest() throws UnknownHostException, IOException
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2,0);
		
		short[][] testField =	{{1,1,0,2,2,2,1,1,1}
							  	,{1,1,0,0,0,1,1,1,2}
							  	,{0,0,0,2,1,1,1,1,2}
							  	,{1,1,0,1,0,2,1,0,2}
							  	,{0,0,1,1,2,1,2,1,2}
							  	,{0,2,2,2,2,1,2,2,0}
							  	,{0,0,2,0,1,0,0,0,2}
							  	,{0,2,2,0,0,0,2,0,0}
							  	,{0,0,0,1,0,2,0,0,0}}; 
		
		game.setTestField(testField);
		boolean victory = game.VictoryCheck();
		
		assertTrue(victory);
	}
	
	
	
	public void NoVictoryTest() throws UnknownHostException, IOException
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2,0);
		
		short[][] testField =	{{1,1,0,2,2,2,1,1,1}
							  	,{1,1,0,0,0,1,1,1,2}
							  	,{0,0,0,1,1,2,1,1,2}
							  	,{1,1,0,0,0,2,1,0,2}
							  	,{0,0,0,1,2,1,2,1,2}
							  	,{0,2,2,2,2,1,2,2,0}
							  	,{0,0,0,0,1,0,0,0,2}
							  	,{0,0,2,0,0,0,2,0,0}
							  	,{0,0,0,1,0,2,0,0,0}}; 
		
		game.setTestField(testField);
		boolean victory = game.VictoryCheck();
		
		assertFalse(victory);
		
	}
	
	@Test
	public void GameEnd() throws UnknownHostException, IOException
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2,0);
		
		short[][] testField =	{{1,1,2,2,2,2,1,1,1}
							  	,{1,1,2,1,1,1,1,1,2}
							  	,{2,2,2,1,1,2,1,1,2}
							  	,{1,1,1,1,2,2,1,2,2}
							  	,{2,2,1,1,2,1,2,1,2}
							  	,{1,2,2,2,2,1,2,2,1}
							  	,{1,2,2,2,1,1,1,1,2}
							  	,{1,2,2,2,1,1,2,1,1}
							  	,{2,2,2,1,1,2,1,1,1}}; 
		
		game.setTestField(testField);
		boolean gameend = game.checkBoardFull();
		
		assertTrue(gameend);
	}
	
	@Test
	public void noGameEndTest() throws UnknownHostException, IOException
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2,0);
		
		short[][] testField =	{{1,1,0,2,0,2,1,0,1}
							  	,{1,1,0,1,1,1,1,1,2}
							  	,{2,2,2,1,1,2,1,1,2}
							  	,{1,1,0,1,2,2,1,2,2}
							  	,{2,2,1,1,2,0,0,1,2}
							  	,{1,2,2,2,0,1,2,2,1}
							  	,{1,2,0,0,1,1,1,1,2}
							  	,{1,0,2,2,0,0,2,1,1}
							  	,{2,2,2,0,0,2,0,1,1}}; 
		
		game.setTestField(testField);
		boolean gameend = game.checkBoardFull();
		
		assertFalse(gameend);
    }
	
	@Test
	public void NewMoveNotYourTurnTest()
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		TestWriter os = new TestWriter();
		player1.addTestDataWriter(os);
		player1.setUserName("frank");
		player2.setUserName("lolcat");
		Server.Game game = new Server.Game(player1, player2, 0);
		game.SetTurn(false);
		String expectedOut = Protocoll.ServerProtocol.CreateIllegalMove();
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		os.out= 0;
		
		
		game.NewMove("frank", 10 , 12);
		
		assertEquals(expectedOutInt, os.out);
	}
	
	@Test
	public void NewMoveOccupiedSpot()
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		TestWriter os = new TestWriter();
		player1.addTestDataWriter(os);
		player1.setUserName("frank");
		player2.setUserName("lolcat");
		Server.Game game = new Server.Game(player1, player2, 0);
		game.SetTurn(true);
		String expectedOut = Protocoll.ServerProtocol.CreateIllegalMove();
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		os.out= 0;
		short[][] testField =	{{1,1,0,2,0,2,1,0,1}
	  							,{1,1,0,1,1,1,1,1,2}
	  							,{2,2,2,1,1,2,1,1,2}
	  							,{1,1,0,1,2,2,1,2,2}
	  							,{2,2,1,1,2,0,0,1,2}
	  							,{1,2,2,2,0,1,2,2,1}
	  							,{1,2,0,0,1,1,1,1,2}
	  							,{1,0,2,2,0,0,2,1,1}
	  							,{2,2,2,0,0,2,0,1,1}}; 
		game.setTestField(testField);
		
		game.NewMove("frank", 2 , 2);
		
		assertEquals(expectedOutInt, os.out);
	}
	
	@Test
	public void NewMoveOtherPlayerGetTurn()
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		TestWriter os = new TestWriter();
		player2.addTestDataWriter(os);
		player1.setUserName("frank");
		player2.setUserName("lolcat");
		Server.Game game = new Server.Game(player1, player2, 0);
		game.SetTurn(true);
		String expectedOut = Protocoll.ServerProtocol.CreateYourTurn(0, 2);
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		os.out= 0;
		short[][] testField =	{{1,1,0,2,0,2,1,0,1}
	  							,{1,1,0,1,1,0,1,1,2}
	  							,{2,2,2,1,1,2,1,1,2}
	  							,{1,1,0,1,2,2,1,2,2}
	  							,{2,2,1,1,2,0,0,1,2}
	  							,{1,2,2,2,0,1,2,2,1}
	  							,{1,2,0,0,1,1,1,1,2}
	  							,{1,0,2,2,0,0,2,1,1}
	  							,{2,2,2,0,0,2,0,1,1}}; 
		game.setTestField(testField);
		
		game.NewMove("frank", 0 , 2);
		
		assertEquals(expectedOutInt, os.out);
	}
	
	@Test
	
	public void NewMoveVictory()
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		TestWriter os = new TestWriter();
		player1.addTestDataWriter(os);
		player1.setUserName("frank");
		player2.setUserName("lolcat");
		Server.Game game = new Server.Game(player1, player2, 0);
		game.SetTurn(true);
		String expectedOut = Protocoll.ServerProtocol.CreateGameEnd(true);
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		os.out= 0;
		short[][] testField =	{{1,1,0,2,0,2,1,0,1}
	  							,{1,1,0,1,1,0,1,1,2}
	  							,{2,2,2,1,1,2,1,1,2}
	  							,{1,1,0,1,2,2,1,2,2}
	  							,{2,2,1,1,2,0,0,1,2}
	  							,{1,2,2,2,0,1,2,2,1}
	  							,{1,2,0,0,1,1,1,1,2}
	  							,{1,0,2,2,0,0,2,1,1}
	  							,{2,2,2,0,0,2,0,1,1}}; 
		game.setTestField(testField);
		
		game.NewMove("frank", 1 , 2);
		assertEquals(expectedOutInt, os.out);
	}
	
	@Test
	public void NewMoveFilledGameField()
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		TestWriter os = new TestWriter();
		player2.addTestDataWriter(os);
		player1.setUserName("frank");
		player2.setUserName("lolcat");
		Server.Game game = new Server.Game(player1, player2, 0);
		game.SetTurn(true);
		String expectedOut = Protocoll.ServerProtocol.CreateGameEnd(false);
		try {
			new DataOutputStream(os).writeUTF(expectedOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expectedOutInt = os.out;
		os.out= 0;
		short[][] testField =	{{1,1,0,2,2,2,1,1,1}
	  							,{1,1,2,1,1,2,1,1,2}
	  							,{2,2,2,1,1,2,1,1,2}
	  							,{1,1,1,1,2,2,1,2,2}
	  							,{2,2,1,1,2,1,2,1,2}
	  							,{1,2,1,2,2,1,2,2,1}
	  							,{1,2,1,2,1,1,1,1,2}
	  							,{1,1,2,2,2,1,2,1,1}
	  							,{2,2,2,1,2,2,2,1,1}}; 
		game.setTestField(testField);
		
		game.NewMove("frank", 0 , 2);
		
		assertEquals(expectedOutInt, os.out);
	}	
	
}
