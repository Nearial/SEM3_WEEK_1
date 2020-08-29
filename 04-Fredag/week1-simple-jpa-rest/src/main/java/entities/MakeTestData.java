package entities;

import entities.BankCustomer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nikolaj Larsen
 */
public class MakeTestData {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        BankCustomer bc1 = new BankCustomer ("Sven", "Wonnegurt", "1", 999999999);
        BankCustomer bc2 = new BankCustomer ("Ilse", "Hjerring", "2", 27);
        BankCustomer bc3 = new BankCustomer ("Bob", "Dagob", "3", 3);
        BankCustomer bc4 = new BankCustomer ("Jens", "Mensch", "4", 0);
        try{
            em.getTransaction().begin();
            em.persist(bc1);
            em.persist(bc2);
            em.persist(bc3);
            em.persist(bc4);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        
    }
    
}
