import java.io.*;
import java.net.*;
import java.util.*;

// Client class
class Client {
	
	// driver code
	public static void main(String[] args)
	{
		
		// establish a connection by providing host and port
		// number
		try (Socket socket = new Socket("localhost", 1234)) {
			
			
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
			
			
			//change attributes for new Socket Message
			//use employee, message, chatlog, attributes to create a new Socket message and then this smessage will be sent to server
			SocketMessage sMessage = new SocketMessage(Employee, Message, ChatLog , Vector<ChatLog>, String);
			
			outStream.writeObject(sMessage);
			//message sent to server
			
			//read message from server
			sMessage = (SocketMessage)inStream.readObject();
			
	
			
			
			Boolean end = false;

						while (!end) {
							
							//create new socket message with new info to send to server
							sMessage = new SocketMessage(Employee, Message, ChatLog , Vector<ChatLog>, String);
							//send to server
							outStream.writeObject(sMessage);
							
							//read message from server
							sMessage = (SocketMessage)inStream.readObject();
						
							//this will go on untill user want to end it.
							
							//end = true;
						}
						
						
						
					
			
			
			
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}