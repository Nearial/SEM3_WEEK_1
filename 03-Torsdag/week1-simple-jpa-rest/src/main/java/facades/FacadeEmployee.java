/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import entities.Employe;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nikolaj Larsen
 */
public class FacadeEmployee {
    
    private static EntityManagerFactory emf;
    private static FacadeEmployee instance;

    private FacadeEmployee() {}

    public static FacadeEmployee getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeEmployee();
        }
        return instance;
    }
    
    public Employe getEmployeeById(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Employe employee = em.find(Employe.class,id);
            return employee;
        }finally {
            em.close();
        }
    }

    public List<Employe> getEmployeesByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM Employe e WHERE e.name = :name");
            query.setParameter("name", name);
            List<Employe> result = query.getResultList();
            return result;
        }finally {
            em.close();
        }
    }
    
    public List<Employe> getAllEmployees (){
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM Employe e");
            List<Employe> result = query.getResultList();
            return result;
        }finally {
            em.close();
        }
    }
    
    public Employe getEmployeeWithHighestSalary (){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.salary = (SELECT MAX(e.salary) FROM Employe e)",Employe.class);
            Employe result = query.getSingleResult();
            return result;
        }finally {
            em.close();
        }
    }
    
    public Employe createEmployee(String name, String address, int salary) {
        Employe employee = new Employe (name, address, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        }finally {
            em.close();
        }
    }
    
        protected Employe createEmployeeWithId(int id,String name, String address, int salary) {
        Employe employee = new Employe (id, name, address, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        }finally {
            em.close();
        }
    }
    
}
