package com.fisglobal.uxjaxrs.service;

import java.util.ArrayList;
import java.util.List;

import com.fisglobal.uxjaxrs.exception.InvalidIdException;
import com.fisglobal.uxjaxrs.model.Employee;


public class EmployeeService {

	static List<Employee>emps;
	
	static {
		emps=new ArrayList<Employee>();
    	Employee e=new Employee(1234,"Dinesh");
    	Employee e1=new Employee(1256,"Ramesh");
    	emps.add(e);emps.add(e1);
	}
	
	public List<Employee> displayEmployees(){
		return emps;
	}
	
	public Employee insertEmployee(Employee e) {
		emps.add(e);
		return e;
	}
	
	public String deleteEmployee(int id) {
		boolean found=false;
		for(Employee e:emps) {
			if(e.getId()==id) {
				found=true;
				emps.remove(e);
			}
		}
		
		return found?"Record Deleted":"Task Failed";
		
	}
	
	
	
}

