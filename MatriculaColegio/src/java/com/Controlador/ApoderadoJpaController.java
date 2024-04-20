package com.Controlador;

import com.Controlador.exceptions.NonexistentEntityException;
import com.Entidades.Apoderado;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author vboxuser
 */
public class ApoderadoJpaController implements Serializable {

    public ApoderadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ApoderadoJpaController(){
        this.emf = Persistence.createEntityManagerFactory("MatriculaColegioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Apoderado apoderado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(apoderado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Apoderado apoderado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            apoderado = em.merge(apoderado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = apoderado.getIdApoderado();
                if (findApoderado(id) == null) {
                    throw new NonexistentEntityException("The apoderado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Apoderado apoderado;
            try {
                apoderado = em.getReference(Apoderado.class, id);
                apoderado.getIdApoderado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The apoderado with id " + id + " no longer exists.", enfe);
            }
            em.remove(apoderado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Apoderado> listarApoderados(){
        EntityManager em =getEntityManager();
        Query query = em.createQuery("SELECT a FROM Apoderado a where a.ind='S'");
        List<Apoderado> list = (List<Apoderado>) query.getResultList();
        return list;
    }

    public List<Apoderado> findApoderadoEntities() {
        return findApoderadoEntities(true, -1, -1);
    }

    public List<Apoderado> findApoderadoEntities(int maxResults, int firstResult) {
        return findApoderadoEntities(false, maxResults, firstResult);
    }

    private List<Apoderado> findApoderadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Apoderado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Apoderado findApoderado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Apoderado.class, id);
        } finally {
            em.close();
        }
    }

    public int getApoderadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Apoderado> rt = cq.from(Apoderado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
