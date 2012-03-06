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

}
