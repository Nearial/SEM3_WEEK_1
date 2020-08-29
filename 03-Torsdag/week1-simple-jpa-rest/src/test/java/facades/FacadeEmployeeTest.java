package facades;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.Employe;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacadeEmployeeTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final FacadeEmployee FE = FacadeEmployee.getEmployeeFacade(ENF);
    private static final EntityManager em = ENF.createEntityManager();
    
    List<Employe> expectedList = new ArrayList();

    
    public FacadeEmployeeTest() {
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
     * Test a method here.
     */
    @Test
    public void testGetEmployeeById() {
        int id = 1;
        Employe expected = new Employe("Sven", "Gaden", 99999999);
        expected.setId(id);
        
        Employe actual = FE.getEmployeeById(id);
      
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEmployeesByName() {
        List<Employe> expected = new ArrayList();
        String name = "Ilse";
        Employe emp = new Employe ("Ilse", "Overlanggade 2", 13);
        expected.add(emp);
        
        List<Employe> actual = FE.getEmployeesByName(name);
        
        assertEquals(expected, actual);
    }
     
//    @Test
//    public void testGetAllEmployees() {
//        Employe emp1 = new Employe ("Sven", "Gaden", 99999999);
//        Employe emp2 = new Employe ("Egon", "Svendborggade 58", 100);
//        Employe emp3 = new Employe ("Ilse", "Overlanggade 2", 13);
//        Employe emp4 = new Employe ("Per", "Kertindevej 5", 10000);
//        Employe emp5 = new Employe("Jens", "Afrika", 0);
//        expectedList.add(emp1);
//        expectedList.add(emp2);
//        expectedList.add(emp3);
//        expectedList.add(emp4);
//        expectedList.add(emp5);
//        
//        List<Employe> actual = FE.getAllEmployees();
//        
//        assertEquals(expectedList.size(), actual.size());
//    }
    
        @Test
    public void testGetEmployeeWithHighestSalary() {
        Employe expected = new Employe("Sven", "Gaden", 99999999);
        
        Employe actual = FE.getEmployeeWithHighestSalary();
        
        assertEquals(expected, actual);
    }
     
      @Test
    public void testCreateEmployee() {
        int count = FE.getAllEmployees().size() + 1;
        Employe expected = new Employe("Jens", "Afrika", 0);
        Employe emp = FE.createEmployee("Jens", "Afrika", 0);
        
        Employe actual1 = FE.getEmployeeById(count);
        expectedList.add(emp);
        
        assertEquals(expected, actual1);
    }
    
}
