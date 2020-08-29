package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import dto.CustomerDTO;
import java.util.List;
import javax.persistence.Query;
import entities.BankCustomer;

public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BankCustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    CustomerDTO getCustomerByID(int id) {
        EntityManager em = emf.createEntityManager();
        try{
            CustomerDTO cdto = em.find(CustomerDTO.class,id);
            return cdto;
        }finally{
            em.close();
        }        
    }
    
    List<CustomerDTO> getCustomerByName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            Query query = em.createQuery("SELECT c FROM BANKCUSTOMER c WHERE c.firstname = :firstname");
            query.setParameter("firstname", name);
            List<CustomerDTO> result = query.getResultList();
            return result;
        }finally{
            em.close();
        }
    }

    BankCustomer addCustomer(BankCustomer customer){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(em);
            em.getTransaction().commit();
            return customer;
        }finally{
            em.close();
        }
    }
    
    List<BankCustomer> getAllBankCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            Query query = em.createQuery("SELECT c FROM BANKCUSTOMER c");
            List<BankCustomer> result = query.getResultList();
            return result;
        }finally{
            em.close();
        }
    }
    
}
