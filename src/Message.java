import java.io.Serializable;

public class Message implements Serializable{

	//MEMBER VARIABLES
	private String content;
	private String sender;
	private String status; // "SENT","DELIVERED","READ"


	//CONSTRUCTOR
	public Message(){
		this.setContent("NONE");
		this.setSender("NONE");
		this.setStatus("NONE"); 
	}
	
	public Message(String content){
		this.setContent(content);
		this.setSender("NONE");
		this.setStatus("SENT"); 
	}

	public Message(String content, String sender){
		this.setContent(content);
		this.setSender(sender);
		this.setStatus("SENT"); 
	}
	
	public Message(String content, String sender, String status){
        this.setContent(content);
        this.setSender(sender);
        this.setStatus(status); 
    }

	//SETTERS
  	public void setContent(String content){
  		this.content = content;
  	}
  
  	public void setSender(String sender){
  		this.sender = sender;
  	}
  
  	public void setStatus(String status){
		status = status.toUpperCase();

		if(status == "SENT"){
			this.status = status;
		}
		else if(status == "DELIVERED"){
			this.status = status;
		}
		else if(status == "READ"){
			this.status = status;
		}
		else{
			this.status = "NONE";
		}
  	}
  

	//GETTERS
  	public String getContent(){
  		return this.content;
  	}
  
  	public String getSender(){
  		return this.sender;
  	}
  
  	public String getStatus(){
  		return this.status;
  	}
  
}