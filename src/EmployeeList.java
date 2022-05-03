import java.io.Serializable;
import java.util.Vector;

public class EmployeeList implements Serializable{

	//MEMBER VARIABLES
	private Vector<Employee> employees = new Vector<Employee>();


	//CONSTRUCTOR
	public EmployeeList(){
	}

	public EmployeeList(Vector<Employee> employees){
		this.employees = employees;
	}

	//SETTERS
  	public void setEmployees(Vector<Employee> employees){
  		this.employees = employees;
  	}
  

	//GETTERS
  	public Vector<Employee> getEmployees(){
  		return this.employees;
  	}

    //MEMBER FUNCTIONS
	public int size(){
        return employees.size();
    }

    public void add(Employee employee){
		employees.add(employee);
    }

    public void remove(String username){ 
		if(contains(username)){ 
			for(int i = 0; i < employees.size(); i++){ 

				String employeeUsername = employees.get(i).getUsername();

				if(employeeUsername.equals(username)){ 
					employees.remove(employees.get(i)); 
				}
			}
		}
    }

    public boolean contains(String username){ // does employee with username arg exist?
        for(int i = 0; i < employees.size(); i++){ 

			String employeeUsername = employees.get(i).getUsername();

			if(employeeUsername.equals(username)){ 
				return true;
			}
		}
		return false;
    }

	public Employee get(String username){
		if(contains(username)){ 
			for(int i = 0; i < employees.size(); i++){ 

				String employeeUsername = employees.get(i).getUsername();

				if(employeeUsername.equals(username)){ 
					return employees.get(i); 
				}
			}
		}

		return new Employee();
    }

	public Employee get(int index){ // overloaded to get employee with index 
		for(int i = 0; i < employees.size(); i++){ 
			if(i == index){ 
				return employees.get(i);
			}
		}

		return new Employee();
    }
  
}