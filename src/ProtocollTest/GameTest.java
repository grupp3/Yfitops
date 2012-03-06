/**
 * 
 */
package ProtocollTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Protocoll.ClientProtocol;
import Protocoll.RequestType;
import Protocoll.ServerProtocol;

/**
 * @author Mathulus
 *
 */
public class GameTest {

	@Test
	public void ToggleGameingRedyRequestStringTest() {
		String requestString = ClientProtocol.CreateToggleGamingRedy();
		
		assertEquals("gamingredy", requestString);
	}
	
	@Test
	public void ToggleGameingRedyCorrectRequestType()
	{
		String requestString = ClientProtocol.CreateToggleGamingRedy();
		RequestType requestType = ServerProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.ToggleRedy, requestType);
	}
	
	@Test
	public void GameStartedRequestStringTest() {
		String requestString = ServerProtocol.CreateGameStarted("håKan", true);
		
		assertEquals("newgame%håKan;"+ true, requestString);
	}
	
	@Test
	public void GameStartedCorrectRequestType()
	{
		String requestString = ServerProtocol.CreateGameStarted("håKan", false);
		RequestType requestType = ClientProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.GameStarted, requestType);
	}
	
	@Test
	public void GameStartedCorrectValues()
	{
		Boolean starting = true;
		String opponentName = "MarkUs15";
		String requestString = ServerProtocol.CreateGameStarted(opponentName, starting);
		
		String[] recevedValues = ClientProtocol.GetOpponentStarting(requestString);
		
		assertEquals("wrong username", opponentName, recevedValues[0]);
		assertEquals("wrong password", starting.toString(), recevedValues[1]);
	}

}
