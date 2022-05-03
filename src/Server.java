
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static final int PORT = 7777;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		new Server().runServer();
	}
	
	public void runServer() throws IOException, ClassNotFoundException{
		ServerSocket serverSocket = new ServerSocket(PORT);
		
		System.out.print("Loading up server...\n");
		ChatService chatService = new ChatService();
		
		System.out.print("Server awaiting connections...\n");
		
		while(true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket, chatService).start();
		}
	}
	
	public class ServerThread extends Thread{
		//MEMBER VARIABLES
		Socket socket = null;

		ObjectInputStream objectInputStream;
		ObjectOutputStream objectOutputStream;
		
		ChatService chatService;
		
		boolean isSessionExpired = false;
		
		// CONSTRUCTOR
		ServerThread(Socket socket, ChatService chatService){
			this.socket = socket;
			this.chatService = chatService;
			
			try {
					this.objectInputStream = new ObjectInputStream(socket.getInputStream());
					this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				} 
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		// MEMBER FUNCTIONS
		public void run() {
			
			try {
				//LOGIN MESSAGE
				SocketMessage loginMessage = receive();
				login(loginMessage);
				
				// LATEST MESSAGE
				SocketMessage latestSocketMessage = receive();
				while (isSessionExpired != true && !(latestSocketMessage.getType().equals("none"))) {
					switch(latestSocketMessage.getType()) {
					  case "login":
						  	login(latestSocketMessage);
						  break;
					  case "logout":
						  	logout(latestSocketMessage);
						  break;
					  case "getEmployee":
						  	getEmployee(latestSocketMessage);
						  break;
					  case "getAllEmployees":
						  	getAllEmployees(latestSocketMessage);
						  break;
					  case "getAllChatLogs":
						  	getAllChatLogs(latestSocketMessage);
						  break;
					  case "getEmployeeChatLogList":
						  	getEmployeeChatLogList(latestSocketMessage);
						  break;
					  case "getEmployeeChatLog":
						  	getEmployeeChatLog(latestSocketMessage);
						  break;
					  case "getChatLog":
						  	getChatLog(latestSocketMessage);
						  break;
					  case "getNewMessage":
						  	getNewMessage(latestSocketMessage);
						  break;
					  case "createChatLog":
						  	createChatLog(latestSocketMessage);
						  break;
					  case "createEmployee":
						  	createEmployee(latestSocketMessage);
						  break;
					  case "deleteEmployee":
						  	deleteEmployee(latestSocketMessage);
						  break;
					  case "sendMessage":
						  	sendMessage(latestSocketMessage);
						  break;
					  default:
					    // code block
					}
					
					if(isSessionExpired != true) {
						latestSocketMessage = receive();
					}
				}
				
				
				System.out.print("\nSocket Closed\n");
				socket.close();
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		private void setEmployeeLoggedIn(SocketMessage message) {
			chatService.setEmployeeLoggedIn(message.getEmployeeLoggedIn());
		}
		
		// SOCKET METHODS
		public void print(String transaction, SocketMessage socketMessage) {
			System.out.println("\n" + transaction.toUpperCase() + ": ");
			socketMessage.print();
		}
		
		public void send(SocketMessage socketMessage) {
			try {
				this.objectOutputStream.writeObject(socketMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			print("sent", socketMessage);
		}
		
		public SocketMessage receive() {
			SocketMessage returnMessage = new SocketMessage();
			
			try {
				returnMessage = (SocketMessage) objectInputStream.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				isSessionExpired = true;
				
			} catch (IOException e) {
				e.printStackTrace();
				isSessionExpired = true;
			}
			
			print("received", returnMessage);
			
			return returnMessage;
		}

		//GETTERS
	  	private void getAllEmployees(SocketMessage socketMessage){
	  		SocketMessage returnEmployeeListMessage = new SocketMessage("getAllEmployees");
	  		setEmployeeLoggedIn(socketMessage);
			
			returnEmployeeListMessage.setEmployeeList(chatService.getAllEmployees());
			returnEmployeeListMessage.setStatus("SUCCESS");

			send(returnEmployeeListMessage);
	  	}
	  
	  	private void getAllChatLogs(SocketMessage socketMessage){
	  		SocketMessage returnChatLogListMessage = new SocketMessage("getAllChatLogs");
	  		setEmployeeLoggedIn(socketMessage);
			
	  		returnChatLogListMessage.setChatLogList(chatService.getAllChatLogs());
	  		returnChatLogListMessage.setStatus("SUCCESS");

			send(returnChatLogListMessage);
	  	}
	  	
		// MORE GETTERS
		//GET CHATLOGS
	    public void getEmployeeChatLogList(SocketMessage socketMessage){
	    	SocketMessage returnChatLogListMessage = new SocketMessage("getEmployeeChatLogList");
	  		setEmployeeLoggedIn(socketMessage);
	    	
	  		returnChatLogListMessage.setChatLogList(chatService.getEmployeeChatLogList());
	  		returnChatLogListMessage.setStatus("SUCCESS");

			send(returnChatLogListMessage);
	    }

		public void getEmployeeChatLog(SocketMessage socketMessage){
			SocketMessage returnChatLogMessage = new SocketMessage("getEmployeeChatLog");
	  		setEmployeeLoggedIn(socketMessage);
			String name = socketMessage.getChatLog().getName();
	    	
			returnChatLogMessage.setChatLog(chatService.getEmployeeChatLog(name));
			returnChatLogMessage.setStatus("SUCCESS");

			send(returnChatLogMessage);
	    }

	    public void getChatLog(SocketMessage socketMessage){
	    	SocketMessage returnChatLogMessage = new SocketMessage("getChatLog");
	  		setEmployeeLoggedIn(socketMessage);
			String name = socketMessage.getChatLog().getName();
	    	
			returnChatLogMessage.setChatLog(chatService.getEmployeeChatLog(name));
			returnChatLogMessage.setStatus("SUCCESS");

			send(returnChatLogMessage);
	    }
	    
	    public void getNewMessage(SocketMessage socketMessage){
			SocketMessage returnChatLogMessage = new SocketMessage("getNewMessage");
	  		setEmployeeLoggedIn(socketMessage);
			String chatLogName = socketMessage.getChatLog().getName();
	    	
			returnChatLogMessage.setMessage(chatService.getNewMessage(chatLogName));
			returnChatLogMessage.setStatus("SUCCESS");

			send(returnChatLogMessage);
	    }
	    
		private void getEmployee(SocketMessage socketMessage) {
			SocketMessage returnEmployeeMessage = new SocketMessage("getEmployee");
	  		setEmployeeLoggedIn(socketMessage);
			String username = socketMessage.getEmployee().getUsername();
			
			returnEmployeeMessage.setEmployee(chatService.getEmployee(username));
			returnEmployeeMessage.setStatus("SUCCESS");

			send(returnEmployeeMessage);
		}
		
		// CHAT SERVICE
		private void login(SocketMessage socketMessage) {
			SocketMessage returnLoginMessage = new SocketMessage("login");
	  		setEmployeeLoggedIn(socketMessage);
			String username = socketMessage.getEmployee().getUsername();
			String password = socketMessage.getEmployee().getPassword();
			
			boolean status = chatService.login(username, password);
			
			if(status == true) {
				returnLoginMessage.setStatus("SUCCESS");
			}
			else {
				returnLoginMessage.setStatus("FAILED");
			}
			
			send(returnLoginMessage);
		}
		
		private void logout(SocketMessage socketMessage) {
			SocketMessage returnLogoutMessage = new SocketMessage("logout");
	  		setEmployeeLoggedIn(socketMessage);
			chatService.logout();
			returnLogoutMessage.setStatus("SUCCESS");
			isSessionExpired = true;
			
			send(returnLogoutMessage);
		}
		 
		// CREATE AND DELETE
		public void createChatLog(SocketMessage socketMessage){
			SocketMessage returnChatLogMessage = new SocketMessage("createChatLog");
	  		setEmployeeLoggedIn(socketMessage);
			String name = socketMessage.getChatLog().getName();
			Vector<String> members = socketMessage.getChatLog().getMembers();
			
			chatService.createChatLog(name, members);
			returnChatLogMessage.setChatLog(chatService.getEmployeeChatLog(name));
			returnChatLogMessage.setStatus("SUCCESS");
			
			send(returnChatLogMessage);
	    }
	    public void createEmployee(SocketMessage socketMessage){
	    	SocketMessage returnLoginMessage = new SocketMessage("createEmployee");
	  		setEmployeeLoggedIn(socketMessage);
			String username = socketMessage.getEmployee().getUsername();
			String password = socketMessage.getEmployee().getPassword();
			boolean isSystemAdmin = socketMessage.getEmployee().getIsSystemAdmin();
			
			chatService.createEmployee(username, password, isSystemAdmin);
			returnLoginMessage.setStatus("SUCCESS");
			
			send(returnLoginMessage);
	    }

	    public void deleteEmployee(SocketMessage socketMessage){
	    	SocketMessage returnLoginMessage = new SocketMessage("deleteEmployee");
	  		setEmployeeLoggedIn(socketMessage);
			String username = socketMessage.getEmployee().getUsername();
			
			chatService.deleteEmployee(username);
			returnLoginMessage.setStatus("SUCCESS");
			
			send(returnLoginMessage);
	    }

		// SEND MESSAGE
	    public void sendMessage(SocketMessage socketMessage){
			SocketMessage returnMessageMessage = new SocketMessage("sendMessage");
	  		setEmployeeLoggedIn(socketMessage);
			String name = socketMessage.getChatLog().getName();
			Message message = socketMessage.getMessage();
			
			chatService.sendMessage(name, message, "DELIVERED");
			returnMessageMessage.getMessage().setSender("NONE");
			returnMessageMessage.setStatus("SUCCESS");
			
			send(returnMessageMessage);
	    }
	}
}

