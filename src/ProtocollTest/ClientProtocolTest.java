/**
 * 
 */
package ProtocollTest;

import static org.junit.Assert.*;

import org.junit.Test;
import Protocoll.*;

/**
 * @author Mathulus
 *
 */
public class ClientProtocolTest {

	@Test
	public void RegisterRequestStringTest() {
		String requestString = ClientProtocol.CreateRegister("håKan", "lövbiff13");
		
		assertEquals("register%håKan;lövbiff13", requestString);
	}
	
	@Test
	public void RegisterCorrectRequestType()
	{
		String requestString = ClientProtocol.CreateRegister("håKan", "lövbiFF13");
		RequestType requestType = ServerProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.Register, requestType);
	}
	
	@Test
	public void RegisterCorrectValues()
	{
		String password = "löavefBBiiFF";
		String userName = "MarkUs15";
		String requestString = ClientProtocol.CreateRegister(userName, password);
		
		String[] recevedValues = ServerProtocol.GetUsernamePassword(requestString);
		
		assertEquals("wrong username", userName, recevedValues[0]);
		assertEquals("wrong password", password, recevedValues[1]);
	}

}
