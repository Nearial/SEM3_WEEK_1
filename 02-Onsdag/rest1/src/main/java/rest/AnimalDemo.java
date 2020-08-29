/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;

/**
 * REST Web Service
 *
 * @author Nikolaj Larsen
 */
@Path("animals")
public class AnimalDemo {

    @Context
    private UriInfo context;
    
    private static Gson Gson = new GsonBuilder().setPrettyPrinting().create();
    private static List<String> animals = new ArrayList();
    
    
    
    /**
     * Creates a new instance of AnimalDemo
     */
    public AnimalDemo() {
          if(animals.isEmpty()){
            animals.add("Dog");
            animals.add("Cat");
            animals.add("Mouse");
            animals.add("Bird");
        }
    }

    /**
     * Retrieves representation of an instance of rest.AnimalDemo
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        return "Vuf… (Message from a dog)";
    }
    
    @Path ("animals_list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson1() {
    String jsonString = Gson.toJson(animals);
    return jsonString;
    }
    
    @Path ("animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2() {
    AnimalNoDB Animal = new AnimalNoDB("Dog", "Quack");
    return new Gson().toJson(Animal);
    }

    /**
     * PUT method for updating or creating an instance of AnimalDemo
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
