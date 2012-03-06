package ServerTest;

import static org.junit.Assert.*;

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
		Server.Game game = new Server.Game(player1, player2);
	}
	
	@Test
	public void VictoryHorisontalTest() throws UnknownHostException, IOException
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2);
		
		short[][] testField =	{{1,1,0,2,2,2,1,1,1}
							  	,{1,1,0,0,0,1,1,1,2}
							  	,{0,0,0,1,1,1,1,1,2}
							  	,{1,1,0,0,0,2,1,0,2}
							  	,{0,0,0,1,2,1,2,1,2}
							  	,{0,2,2,2,2,1,2,2,0}
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
		Server.Game game = new Server.Game(player1, player2);
		
		short[][] testField =	{{1,1,0,2,2,2,1,1,1}
							  	,{1,1,0,0,0,1,1,1,2}
							  	,{0,0,0,2,1,1,1,1,2}
							  	,{1,1,0,0,0,1,1,0,2}
							  	,{0,0,0,1,2,1,2,1,2}
							  	,{0,2,2,2,2,1,2,2,0}
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
		Server.Game game = new Server.Game(player1, player2);
		
		short[][] testField =	{{1,1,0,2,2,2,1,1,1}
							  	,{1,1,0,0,1,1,1,1,2}
							  	,{0,0,0,2,1,1,1,1,2}
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
		Server.Game game = new Server.Game(player1, player2);
		
		short[][] testField =	{{1,1,0,2,2,2,1,1,1}
							  	,{1,1,0,0,0,1,1,1,2}
							  	,{0,0,0,2,1,1,1,1,2}
							  	,{1,1,0,1,0,2,1,0,2}
							  	,{0,0,1,1,2,1,2,1,2}
							  	,{0,2,2,2,2,1,2,2,0}
							  	,{0,0,0,0,1,0,0,0,2}
							  	,{0,0,2,0,0,0,2,0,0}
							  	,{0,0,0,1,0,2,0,0,0}}; 
		
		game.setTestField(testField);
		boolean victory = game.VictoryCheck();
		
		assertTrue(victory);
	}
	
	@Test
	public void NoVictoryTest() throws UnknownHostException, IOException
	{
		Server.PlayerConnection player1= new Server.PlayerConnection();
		Server.PlayerConnection player2 = new Server.PlayerConnection();
		Server.Game game = new Server.Game(player1, player2);
		
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
}
