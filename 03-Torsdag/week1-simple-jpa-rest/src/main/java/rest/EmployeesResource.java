package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Employe;
import facades.FacadeEmployee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Nikolaj Larsen
 */
@Path("employee")
public class EmployeesResource {

    @Context
    private UriInfo context;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static FacadeEmployee FE = FacadeEmployee.getEmployeeFacade(emf);
    private static Gson Gson = new GsonBuilder().setPrettyPrinting().create();

    
    /**
     * Creates a new instance of EmployeesResource
     */
    public EmployeesResource() {
    }

    /**
     * Retrieves representation of an instance of rest.EmployeesResource
     * @return an instance of java.lang.String
     */
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
       List<Employe> eList = new ArrayList();
       eList.addAll(FE.getAllEmployees());
       String jsonString = Gson.toJson(eList);
       return jsonString;
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@PathParam("id")int id) {
       Employe emp = FE.getEmployeeById(id);
       String jsonString = Gson.toJson(emp);
       return jsonString;
    }
    
    @Path("highestpaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPay(@PathParam("id")int id) {
       Employe emp = FE.getEmployeeWithHighestSalary();
       String jsonString = Gson.toJson(emp);
       return jsonString;
    }
    @Path("name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPay(@PathParam("name")String name) {
        List<Employe> eList = new ArrayList();
        eList.addAll(FE.getEmployeesByName(name));
       String jsonString = Gson.toJson(eList);
       return jsonString;
    }

    /**
     * PUT method for updating or creating an instance of EmployeesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJSON(String content) {
    }
}
