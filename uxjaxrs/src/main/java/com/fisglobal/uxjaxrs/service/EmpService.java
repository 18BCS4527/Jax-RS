package com.fisglobal.uxjaxrs.service;

import com.fisglobal.uxjaxrs.exception.InvalidIdException;

public enum EmpService {
	Instance;
	
	public String getEmployeeById(String id)  {
	      try {
	          long l = Long.parseLong(id);
	          return "employee" + l;
	      } catch (NumberFormatException e) {
	    	  System.out.println(e);
	          throw new InvalidIdException("Here Employee id is not valid, " + id);
	      }

	  }
}
