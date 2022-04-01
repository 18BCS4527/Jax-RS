package com.fisglobal.uxjaxrs.uxjaxrs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import com.fisglobal.uxjaxrs.exception.InvalidIdException;
import com.fisglobal.uxjaxrs.model.Employee;
import com.fisglobal.uxjaxrs.service.EmpService;
import com.fisglobal.uxjaxrs.service.EmployeeService;



@Path("/")
public class MyResource {

	@GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getAll() { 
    	EmployeeService es=new EmployeeService();
    	
    	List<Employee> emp=es.displayEmployees();
    	GenericEntity<List<Employee>>l=new GenericEntity<List<Employee>>(emp) {
    		
    	};
    	
        return Response.status(200).entity(l.getEntity()).build();
    }
    
    @POST
    @Path("add/{id}/{name}")
    @Produces(MediaType.TEXT_XML)
    public Response insertEmp(@PathParam("id") int id,@PathParam("name") String name) {
    	EmployeeService es=new EmployeeService();
    	return Response.ok(es.insertEmployee(new Employee(id,name))).build();
    }
    
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEmp(@PathParam("id") int id) {
    	EmployeeService es=new EmployeeService();
    	return Response.ok(es.deleteEmployee(id)).build();
    }
    
    @POST
    @Path("form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response formCheck(@FormParam("name") String name,@FormParam("id") int id) {
    	
    	return Response.ok(name).build();
    }
    
    @GET
    @Path("{orderId:[a-z]\\d{2,3}}")
    public Response reg(@PathParam("orderId") String order) {
    	
    	return Response.ok(order).build();
    }
    
    @GET
    @Path("query")
    public Response test(@QueryParam("name") String name,@QueryParam("id") String id) {
    	
    	return Response.ok(name).build();
    }
    
    @GET
    @Path("query1")
    public Response test1(@DefaultValue("Dinesh") @QueryParam("name") String name,
    		@DefaultValue("123456789") @QueryParam("id") String id) {
    	
    	return Response.ok(name).build();
    }
    
    @GET
    @Path("test1")
    public Response createCookies() {
        NewCookie cookie1 = new NewCookie("myStrCookie", "cookieStrVal");
        NewCookie cookie2 = new NewCookie("myDateCookie", "2017-03-28");
        NewCookie cookie3 = new NewCookie("myIntCookie", "100");
       
        
        return Response.status(200)
        		.entity("Cookies are added")
        		.cookie(cookie1,cookie2,cookie3)
        		.build();
        
    }
    
    @GET
    @Path("test2")
    public String readAllCookies(@Context HttpHeaders headers) {
        Map<String, Cookie> cookies = headers.getCookies();
        String str = cookies.entrySet()
                            .stream()
                            .map(e -> e.getKey() + " = " + e.getValue().getValue())
                            .collect(Collectors.joining("<br/>"));
        return str;
    }
    
    @GET
    @Path("test3")
    public String readCookie1(@CookieParam("myStrCookie") String strCookie) {
        return "myStrCookie value = " + strCookie;
    }

    @GET
    @Path("test4")
    public String readCookie2(@CookieParam("myIntCookie") int intCookie) {
        return "myIntCookie value  = " + intCookie;
    }

    @GET
    @Path("test5")
    public String readCookie3(@CookieParam("myIntCookie") BigDecimal bd) {
        return "myIntCookie value in BigDecimal = " + bd;
    }

    @GET
    @Path("test6")
    public String readCookie4(@CookieParam("myIntCookie") Long aLong) {
        return "myIntCookie  in Long :" + aLong;
    }

    @GET
    @Path("test7")
    public String readCookie5(@CookieParam("myDateCookie") Cookie cookie) {
        return "Cookie object :" + cookie;
    }

//    @GET
//    @Path("test8")
//    public String readCookie6(@CookieParam("myDateCookie") LocalDate date) {
//        return "myDateCookie as LocalDate :" + date;
//    }
//
//    @GET
//    @Path("test9")
//    public Response writeCookies() {
//        NewCookie cookie1 = new NewCookie("myCookie", "cookieStrVal");
//        NewCookie cookie2 = new NewCookie("myCookie", "cookieStrVal2");
//        Response.ResponseBuilder rb = Response.ok(" Multiple values of myCookie"
//                + " sent to the browser");
//        Response response = rb.cookie(cookie1, cookie2)
//                              .build();
//        return response;
//    }
//
//    @GET
//    @Path("test10")
//    public String readCookie7(@CookieParam("myCookie") List<String> list) {
//        String rv = "List size: " + list.size() +
//                "<br/>List values:<br/> ";
//        rv += list.stream()
//                  .collect(Collectors.joining("<br/>"));
//        return rv;
//    }
    
    
    @GET
    @Path("employee/{id}")
    public String exception(@PathParam("id") String id) {
    	    	
        return EmpService.Instance.getEmployeeById(id);
    }
    

}






