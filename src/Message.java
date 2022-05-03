import java.io.Serializable;
import java.util.Vector;

public class Message implements Serializable{

	//MEMBER VARIABLES
	private String content;
	private String sender;
	private String status; // "SENT","DELIVERED","READ"
	private Vector<String> readers = new Vector<String>();


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

		if(status.equals("SENT")){
			this.status = status;
		}
		else if(status.equals("DELIVERED")){
			this.status = status;
		}
		else if(status.equals("READ")){
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
  	
  	public Vector<String> getReaders(){
  		return this.readers;
  	}
  	
  	
  	//MEMBER FUNCTIONS
  	public void addReader(String reader){
  		if(!this.readers.contains(reader)) {
  			this.readers.add(reader);
  		}
  	}
  
}