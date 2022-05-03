import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;

public class Client {
	
	// MEMBER VARIABLES
	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	
	private BufferedReader bufferedReader;
	private Scanner sc; 
	
	private Employee employeeLoggedIn = new Employee();
	
	private boolean isSessionExpired = false;
	
	// CONSTRUCTOR
	Client(){
		// ESTABLISH CONNECTION
		try {
			
			this.socket = new Socket("localhost", Server.PORT);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// INITIALIZE OUTPUT STREAM
		try {
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// INITIALIZE INPUT STREAM
		try {
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// INITIALIZE USER INPUT
		this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		this.sc = new Scanner(System.in);
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		print("received", returnMessage);
		
		return returnMessage;
	}
	
  	public void setEmployeeLoggedIn(String username){
  		this.employeeLoggedIn = getEmployee(username);
  	}
  	
  	public Employee getEmployeeLoggedIn(){
  		return this.employeeLoggedIn;
  	}
  	
  	public SocketMessage setEmployeeLoggedInForSocketMessage(SocketMessage message){
  		message.setEmployeeLoggedIn(this.employeeLoggedIn);
  		return message;
  	}
  
	//CHAT SERVICE
	//GETTERS
  	public EmployeeList getAllEmployees(){
  		SocketMessage employeeListMessage = new SocketMessage("getAllEmployees");
  		employeeListMessage = setEmployeeLoggedInForSocketMessage(employeeListMessage);
  		
		send(employeeListMessage);
		SocketMessage returnEmployeeListMessage = receive();
		
		return returnEmployeeListMessage.getEmployeeList();
  	}
  
  	public ChatLogList getAllChatLogList(){
  		SocketMessage chatLogListMessage = new SocketMessage("getAllChatLogs");
  		chatLogListMessage = setEmployeeLoggedInForSocketMessage(chatLogListMessage);
  		
		send(chatLogListMessage);
		SocketMessage returnChatLogListMessage = receive();
		
		return returnChatLogListMessage.getChatLogList();
  	}
  	
	// MORE GETTERS
	//GET CHATLOGS
    public ChatLogList getEmployeeChatLogList(){
    	SocketMessage chatLogListMessage = new SocketMessage("getEmployeeChatLogList");
    	chatLogListMessage = setEmployeeLoggedInForSocketMessage(chatLogListMessage);
  		
		send(chatLogListMessage);
		SocketMessage returnChatLogListMessage = receive();
		
		return returnChatLogListMessage.getChatLogList();
    }

	public ChatLog getEmployeeChatLog(String name){
		SocketMessage chatLogMessage = new SocketMessage("getEmployeeChatLog");
		chatLogMessage = setEmployeeLoggedInForSocketMessage(chatLogMessage);
		chatLogMessage.getChatLog().setName(name);
  		
		send(chatLogMessage);
		SocketMessage returnChatLogMessage = receive();
		
		return returnChatLogMessage.getChatLog();
    }

    public ChatLog getChatLog(String name){
    	SocketMessage chatLogMessage = new SocketMessage("getChatLog");
    	chatLogMessage = setEmployeeLoggedInForSocketMessage(chatLogMessage);
		chatLogMessage.getChatLog().setName(name);
  		
		send(chatLogMessage);
		SocketMessage returnChatLogMessage = receive();
		
		return returnChatLogMessage.getChatLog();
    }
    
    // GET NEWEST MESSAGE
 	public Message getNewMessage(String chatLogName){
     	SocketMessage messageMessage = new SocketMessage("getNewMessage");
     	messageMessage = setEmployeeLoggedInForSocketMessage(messageMessage);
     	messageMessage.getChatLog().setName(chatLogName);
   		
 		send(messageMessage);
 		SocketMessage returnMessageMessage = receive();
 		
 		return returnMessageMessage.getMessage();
     }

	// GET EMPLOYEE
	public Employee getEmployee(String username){
		SocketMessage employeeMessage = new SocketMessage("getEmployee");
		employeeMessage = setEmployeeLoggedInForSocketMessage(employeeMessage);
		employeeMessage.getEmployee().setUsername(username);
		
		send(employeeMessage);
		SocketMessage returnEmployeeMessage = receive();
		
		return returnEmployeeMessage.getEmployee();
    }

	// LOGIN AND LOGOUT
    public boolean login(String username, String password){
    	
		SocketMessage loginMessage = new SocketMessage("login");
		loginMessage = setEmployeeLoggedInForSocketMessage(loginMessage);
		loginMessage.getEmployee().setUsername(username);
		loginMessage.getEmployee().setPassword(password);
		
		send(loginMessage);
		SocketMessage returnLoginMessage = receive();
		
		if(returnLoginMessage.getStatus().equals("SUCCESS")) {
	    	this.setEmployeeLoggedIn(username);
			return true;
		}
		else{
			return false;
		}
    }
    
    public void logout(){
    	SocketMessage logoutMessage = new SocketMessage("logout");
    	logoutMessage = setEmployeeLoggedInForSocketMessage(logoutMessage);
		send(logoutMessage);
		SocketMessage returnLogoutMessage = receive();
    }
    
	// CREATE AND DELETE
    public void createChatLog(String name, Vector<String> members){
		SocketMessage chatLogMessage = new SocketMessage("createChatLog");
		chatLogMessage = setEmployeeLoggedInForSocketMessage(chatLogMessage);
		chatLogMessage.getChatLog().setName(name);
		chatLogMessage.getChatLog().setMembers(members);
		
		send(chatLogMessage);
		SocketMessage returnChatLogMessage = receive();
    }
    
    public void createEmployee(String username, String password, boolean isSystemAdmin){
		SocketMessage employeeMessage = new SocketMessage("createEmployee");
		employeeMessage = setEmployeeLoggedInForSocketMessage(employeeMessage);
		employeeMessage.getEmployee().setUsername(username);
		employeeMessage.getEmployee().setPassword(password);
		employeeMessage.getEmployee().setIsSystemAdmin(isSystemAdmin);
		
		send(employeeMessage);
		SocketMessage returnEmployeeMessage = receive();
    }

    public void deleteEmployee(String username){
    	SocketMessage deleteMessage = new SocketMessage("deleteEmployee");
    	deleteMessage = setEmployeeLoggedInForSocketMessage(deleteMessage);
    	deleteMessage.getEmployee().setUsername(username);
		
		send(deleteMessage);
		SocketMessage returnDeleteMessage = receive();
    }

	// SEND MESSAGE
    public void sendMessage(String chatLogName, Message message){
		SocketMessage messageMessage = new SocketMessage("sendMessage");
		messageMessage = setEmployeeLoggedInForSocketMessage(messageMessage);
		messageMessage.getChatLog().setName(chatLogName);
		messageMessage.setMessage(message);
		
		send(messageMessage);
		SocketMessage returnMessageMessage = receive();
    }
}
