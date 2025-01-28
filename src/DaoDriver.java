import java.util.List;
import java.util.Scanner;

import com.jdbc.dao.EmployeeDAOImpl;
import com.jdbc.dto.Employee;

public class DaoDriver {

	public static void main(String[] args) {
		EmployeeDAOImpl empiDAOImpl = new EmployeeDAOImpl();
		
		List<Employee> employees = empiDAOImpl.getEmployees();
		
		for(Employee e : employees ) {
			System.out.println(e);
		}
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the Employee ID to be updated: ");
		int id = scan.nextInt();
		
		Employee e = empiDAOImpl.getEmployee(id);
		System.out.println(e);
		
		System.out.println("Enter the salary to be updated:");
		int newSalary = scan.nextInt();
		
		e.setSalary(newSalary);
	    System.out.println(empiDAOImpl.updateEmployee(e));
	}

}
