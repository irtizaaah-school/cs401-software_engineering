
import java.util.Vector;

public class Employee{
	
	String username;
	String password;
	boolean isLoggedIn;
	boolean isSystemAdmin;
	Vector<ChatLog> allUserChatLogs;
	
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
		isLoggedIn = false;
		//isSystemAdmin = false;
		
	}
	
	
	//Setters
	public void setUsername(String username) {
		
		this.username = username;
		
	}
	
	public void setPassword(String password) {
		
		this.password = password;
		
	}
	
	public void setIsLoggedIn(boolean isloggedin) {
		
		this.isLoggedIn = isloggedin;
		
	}
	
	public void setAllUserChatLogs(Vector<ChatLog> alluserchatlogs) {
		
		this.allUserChatLogs = alluserchatlogs;
		
	}

	
	
	
	//getters
	public String getUsername() {
		
		return this.username;
		
	}
	
	public String getPassword() {
		
		return this.password;
		
	}
	
	public boolean getIsLoggedIn() {
		
		return this.isLoggedIn;
		
	}
	
	public Vector<ChatLog> getAllUserChatLogs() {
		
		return this.allUserChatLogs;
		
	}
	
	public boolean getIsSystemAdmin() {
		
		return this.isSystemAdmin;
		
	}
	
	
	
	
	public boolean verifyEmployee(String username, String password) {
		if(this.username.equals(username) && this.password.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public void createUserChatLog(String chatLogName, Vector<String> allMembers) {
		allUserChatLogs.add(new ChatLog(chatLogName, allMembers));
		
	}
	
	public void deleteUserChatLog(String ChatLogName){
		for(int i = 0; i < allUserChatLogs.size(); i++) {
			if(allUserChatLogs.get(i).getChatLogName().equals(ChatLogName)) {
				allUserChatLogs.remove(i);
			}
		}
	}
	
	public ChatLog getUserChatLog(String ChatLogName){
		int index = 0;
		for(int i = 0; i < allUserChatLogs.size(); i++) {
			if(allUserChatLogs.get(i).getChatLogName().equals(ChatLogName)) {
				index = i;
			}
		}
		return allUserChatLogs.get(index);
	}
	
	public void sendMessage(String ChatLogName, Message message) {
		for(int i = 0; i < allUserChatLogs.size(); i++) {
			if(allUserChatLogs.get(i).getChatLogName().equals(ChatLogName)) {
				allUserChatLogs.get(i).addMessage(message);
			}
		}
		
		//send message part still pending.
		
	}
	
	
	
	
}
