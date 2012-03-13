package Client;
import Protocoll.*;
//import Client.Enum_RequestType_Class.Enum_RequestType_static.enum_RequestType_static;


public class ProtocolClient {
	//Enum_RequestType enum_RequestType {};
	RequestType requestType;
	ClientProtocol clientProtocol;
	ProtocolClient(){
		clientProtocol = new ClientProtocol();
	}

	public RequestType getRequestType(String readUTF){
		return ClientProtocol.GetRequestType(readUTF);
	}

	public String[] getOpponentStarting(RequestType requestType2) {
		// TODO Auto-generated method stub
		return null;
	}
}
