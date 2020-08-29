package dbfacade;

import com.google.gson.Gson;
import entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nikolaj Larsen
 */
public class customerFacade {
 private static EntityManagerFactory emf;
    private static customerFacade instance;

    private customerFacade() {}
    
    public static customerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new customerFacade();
        }
        return instance;
    }
    
    public static void main(String[] args) {
        
    EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("pu");      
    customerFacade facade = customerFacade.getCustomerFacade(emf1);
    Customer b1 = facade.addCustomer("Sven", "Jensen");
    Customer b2 = facade.addCustomer("Lars", "Larsen");
    //Find book by ID
    System.out.println(facade.findByID(b1.getId()).getFirstName());
    System.out.println(facade.findByID(b2.getId()).getFirstName());
    //Find all books
    System.out.println(facade.allCustomers().toString());
    }
    
    public Customer findByID(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Customer customer = em.find(Customer.class,id);
            return customer;
        }finally {
            em.close();
        }
    }
    
    public List<Customer> findByLastName(String name){
        List<Customer> customers = new ArrayList();
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :lastname",Customer.class);
            query.setParameter("lastname", name);
            List<Customer> result = query.getResultList();
            return result;
        }finally{
            em.close();
        }
    }
    
    public int getNumberOfCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c",Customer.class);
            int i = query.getResultList().size();
            return i;
        }finally {
            em.close();
        }
    }
    
    public List<Customer> allCustomers(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            List<Customer> customers = query.getResultList();
            return customers;
        }finally {
            em.close();
        }
    }
    
    public Customer addCustomer(String fName, String lName){
        Customer customer = new Customer (fName, lName);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally{
            em.close();
        }
    }

}
