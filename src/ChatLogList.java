import java.io.Serializable;
import java.util.Vector;

public class ChatLogList implements Serializable{

	//MEMBER VARIABLES
	private Vector<ChatLog> chatLogs = new Vector<ChatLog>();


	//CONSTRUCTOR
	public ChatLogList(){
	}

	public ChatLogList(Vector<ChatLog> chatLogs){
		this.chatLogs = chatLogs;
	}

	//SETTERS
  	public void setChatLogs(Vector<ChatLog> chatLogs){
  		this.chatLogs = chatLogs;
  	}
  

	//GETTERS
  	public Vector<ChatLog> getChatLogs(){
  		return this.chatLogs;
  	}

    //MEMBER FUNCTIONS
	public int size(){
        return chatLogs.size();
    }

    public void add(ChatLog chatlog){
        chatLogs.add(chatlog);
    }

	public boolean contains(String name){ // does chatlog with name exist?
		name = name.toLowerCase();
		
		for(int i = 0; i < chatLogs.size(); i++){
			if(chatLogs.get(i).getName().equals(name)){
				return true;
			}
		}

		return false;
    }

    public void remove(String name){
    	name = name.toLowerCase();
    	
		if(contains(name)){ 
			for(int i = 0; i < chatLogs.size(); i++){ 

				String chatLogName = chatLogs.get(i).getName();

				if(chatLogName.equals(name)){ 
					chatLogs.remove(chatLogs.get(i)); 
				}
			}
		}
    }

    public ChatLog get(String name){
    	name = name.toLowerCase();
    	
		if(contains(name)){ 
			for(int i = 0; i < chatLogs.size(); i++){ 

				String chatLogName = chatLogs.get(i).getName();

				if(chatLogName.equals(name)){ 
					return chatLogs.get(i);
				}
			}
		}

		return new ChatLog("NONE", new Vector<String>(), new Vector<Message>()); // else return empty chatlog
    }

	public ChatLog get(int index){ // overloaded to get chatlog with index 
		for(int i = 0; i < chatLogs.size(); i++){ 
			if(i == index){ 
				return chatLogs.get(i);
			}
		}

		return new ChatLog();
    }

    public ChatLogList getMemberChatLogs(String username){
    	username = username.toLowerCase();
    	
		ChatLogList employeeChatlog = new ChatLogList();

		for(int i = 0; i < chatLogs.size(); i++){ 
			for(int j = 0; j < chatLogs.get(i).getMembers().size(); j++){ 

				String chatLogMemberUsername = chatLogs.get(i).getMembers().get(j);

				if(chatLogMemberUsername.equals(username)){ 
					employeeChatlog.add(chatLogs.get(i)); 
				}
			}
		}

		return employeeChatlog; // else return empty chatlog
    }
}