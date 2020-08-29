package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
@Path("animals_db")
public class AnimalFromDB {

    @Context
    private UriInfo context;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalFromDB
     * @return an instance of java.lang.String
     */
    
@Path("animals")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimals() {
  EntityManager em = emf.createEntityManager();
  try{
      TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
      List<Animal> animals = query.getResultList();
      return new Gson().toJson(animals);
   } finally {
          em.close();
   }
}

@Path("animalbyid/{id}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimalByID(@PathParam("id")int id) {
    EntityManager em = emf.createEntityManager();
    try {
    Animal animal = em.find(Animal.class,id);
    return animal.toString();
    }finally{
        em.close();
    }
}

@Path("animalbytype/{type}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimalByType(@PathParam("type") String type) {
    EntityManager em = emf.createEntityManager();
    try {
    TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type = :type", Animal.class);
    return query.setParameter("type", type).getSingleResult().toString();
    }finally{
        em.close();
    }
}

@Path("random_animal")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimalByRandom(@PathParam("id")int id) {
    EntityManager em = emf.createEntityManager();
    Random rand = new Random();
    int count;
    int random;
    try {
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
        List<Animal> animals = query.getResultList();
        count = animals.size();
        random = rand.nextInt(count) + 1;
        Animal animal = em.find(Animal.class,random);
        return animal.toString();
    }finally{
        em.close();
    }
}
    
    /**
     * PUT method for updating or creating an instance of AnimalFromDB
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
