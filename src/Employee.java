import java.io.Serializable;

public class Employee implements Serializable{

	//MEMBER VARIABLES
	private String username;
	private String password;
	private boolean isSystemAdmin;
	private boolean isOnline;

	//CONSTRUCTOR
	public Employee(){
		this.setUsername("NONE");
		this.setPassword("NONE");
		this.setIsSystemAdmin(false);
		this.setIsOnline(false);
	}

	public Employee(String username, String password, boolean isSystemAdmin){
		this.setUsername(username);
		this.setPassword(password);
		this.setIsSystemAdmin(isSystemAdmin);
		this.setIsOnline(false);
	}

	//SETTERS
  	public void setUsername(String username){
  		this.username = username.toLowerCase();
  	}
  
  	public void setPassword(String password){
  		this.password = password;
  	}
  
  	public void setIsSystemAdmin(boolean isSystemAdmin){
  		this.isSystemAdmin = isSystemAdmin;
  	}
  	
  	public void setIsOnline(boolean isOnline){
  		this.isOnline = isOnline;
  	}
  

	//GETTERS
  	public String getUsername(){
  		return this.username;
  	}
  
  	public String getPassword(){
  		return this.password;
  	}
  
  	public boolean getIsSystemAdmin(){
  		return this.isSystemAdmin;
  	}
  	
  	public boolean getIsOnline(){
  		return this.isOnline;
  	}
    
    // MEMBER FUNCTIONS
    public boolean validate( String password){ // check if password arg matches stored password
        if(password.equals(this.password)){
        	this.setIsOnline(true);
			return true;
		}
		return false;
    }

}
