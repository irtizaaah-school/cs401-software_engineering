import java.io.Serializable;
import java.util.Vector;

public class ChatLog implements Serializable{

	//MEMBER VARIABLES
	private String name;
	private Vector<Message> messages = new Vector<Message>();
	private Vector<String> members  = new Vector<String>();


	//CONSTRUCTOR
	public ChatLog(){
		this.setName("NONE");
	}

	public ChatLog(String name, Vector<String> members){
		this.setName(name);
		this.setMembers(members);
	}

	public ChatLog(String name, Vector<String> members, Vector<Message> messages){
		this.setName(name);
		this.setMessages(messages);
		this.setMembers(members);
	}

	//SETTERS
  	public void setName(String name){
  		this.name = name.toLowerCase();
  	}
  
  	public void setMessages(Vector<Message> messages){
  		this.messages = messages;
  	}
  
  	public void setMembers(Vector<String> members){
  		this.members = members;
  	}
  

	//GETTERS
  	public String getName(){
  		return this.name;
  	}
  
  	public Vector<Message> getMessages(){
  		return this.messages;
  	}
  
  	public Vector<String> getMembers(){
  		return this.members;
  	}

    // MEMBER FUNCTIONS
    public void addMessage(Message message){
    	message.setStatus("DELIVERED");
        this.messages.add(message);
    }
  
    public String toString()
    {
      return name;  
    }  
}