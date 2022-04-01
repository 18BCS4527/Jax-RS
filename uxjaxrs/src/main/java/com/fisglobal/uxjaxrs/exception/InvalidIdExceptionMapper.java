package com.fisglobal.uxjaxrs.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidIdExceptionMapper implements ExceptionMapper<InvalidIdException> {


	  @Override
	  public Response toResponse(InvalidIdException exception) {
	      return Response.status(Response.Status.NOT_FOUND)
	                     .entity(prepareMessage(exception))
	                     .type("text/plain")
	                     .build();
	  }

	  private String prepareMessage(InvalidIdException exception) {

	      String m = exception.getMessage();
	      String r = "Invalid request.\n";
	      r += String.format("Error Message: %s.%nError Type: %s.%n"
	                      + "You may contact admin@example.com for more questions.",
	              m, exception.getClass());
	      return r;
	  }
	}
