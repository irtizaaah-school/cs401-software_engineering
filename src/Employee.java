import java.io.Serializable;

public class Employee implements Serializable{

	//MEMBER VARIABLES
	private String username;
	private String password;
	private boolean isSystemAdmin;

	//CONSTRUCTOR
	public Employee(){
		this.setUsername("NONE");
		this.setPassword("NONE");
		this.setIsSystemAdmin(false);
	}

	public Employee(String username, String password, boolean isSystemAdmin){
		this.setUsername(username);
		this.setPassword(password);
		this.setIsSystemAdmin(isSystemAdmin);
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
    
    // MEMBER FUNCTIONS
    public boolean validate( String password){ // check if password arg matches stored password
        if(password.equals(this.password)){
			return true;
		}
		return false;
    }

}