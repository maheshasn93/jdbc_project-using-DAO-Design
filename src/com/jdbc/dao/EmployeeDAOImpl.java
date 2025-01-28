package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.connector.ConnectorFactory;
import com.jdbc.dto.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public List getEmployees() {
		ArrayList<Employee> emplist = null;
		
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "select * from emp";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			emplist = new ArrayList<Employee>();
			
			while(res.next() == true) {
				int id = res.getInt(1);
				String name = res.getString(2);
				String designation = res.getString(3);
				int salary = res.getInt(4);
				
		        Employee e1	= new Employee(id, name, designation, salary);
		        emplist.add(e1);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return emplist;
	}

	@Override
	public Employee getEmployee(int id) {
		Employee e = null;
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "select * from emp where id = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();
			res.next();
			
			e = new  Employee(res.getInt(1),res.getString(2), res.getString(3), res.getInt(4));
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		return e;
		
	}

	@Override
	public boolean insertEmployee(int id, String name, String designation, int salary) {
		return false;
		
	}

	@Override
	public boolean updateEmployee(Employee e) { 
		int i = 0;
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "update emp set salary = ? where id = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1,  e.getSalary());
			pstmt.setInt(2, e.getId());
			i=pstmt.executeUpdate();
			
		} catch(Exception e2) {
			e2.printStackTrace();
		}
		if(i == 1) {
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteEmployee(int id) {
		return false;
	}
	
}
