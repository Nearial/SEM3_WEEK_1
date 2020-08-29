package dbfacade;

import entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Nikolaj Larsen
 */
public class customerFacadeTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final customerFacade FE = customerFacade.getCustomerFacade(ENF);
    private static final EntityManager em = ENF.createEntityManager();
    
    public customerFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        em.getTransaction().begin();
    }
    
    @AfterEach
    public void tearDown() {
        em.getTransaction().rollback();
    }

    /**
     * Test of findByID method, of class customerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testFindByID() {
        System.out.println("findByID");
        int id = 1;
        Customer expResult = new Customer("Sven", "Jensen");
        Customer result = FE.findByID(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of findByLastName method, of class customerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testFindByLastName() {
        System.out.println("findByLastName");
        String name = "Larsen";
        Customer custom = new Customer("Lars", "Larsen");
        List<Customer> expResult = new ArrayList();
        expResult.add(custom);
        List<Customer> result = FE.findByLastName(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfCustomers method, of class customerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testGetNumberOfCustomers() {
        System.out.println("getNumberOfCustomers");
        int expResult = 2;
        int result = FE.getNumberOfCustomers();
        assertEquals(expResult, result);
    }

    /**
     * Test of allCustomers method, of class customerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAllCustomers() {
        System.out.println("allCustomers");
        List<Customer> expResult = new ArrayList();
        Customer c1 = new Customer("Sven", "Jensen");
        Customer c2 = new Customer("Lars", "Larsen");
        expResult.add(c1);
        expResult.add(c2);
        List<Customer> result = FE.allCustomers();
        assertEquals(expResult, result);
    }

    /**
     * Test of addCustomer method, of class customerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        String fName = "Jon";
        String lName = "Jensen";
        Customer expResult = new Customer(fName, lName);
        Customer result = FE.addCustomer(fName, lName);
        assertEquals(expResult, result);
    }
    
}
