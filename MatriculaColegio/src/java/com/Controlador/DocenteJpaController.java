package com.Controlador;

import com.Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.Entidades.AreasDocente;
import com.Entidades.Docente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DocenteJpaController implements Serializable {

    public DocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public DocenteJpaController(){
        this.emf = Persistence.createEntityManagerFactory("MatriculaColegioPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docente docente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreasDocente idarea = docente.getIdarea();
            if (idarea != null) {
                idarea = em.getReference(idarea.getClass(), idarea.getIdArea());
                docente.setIdarea(idarea);
            }
            em.persist(docente);
            if (idarea != null) {
                idarea.getDocenteList().add(docente);
                idarea = em.merge(idarea);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docente docente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente persistentDocente = em.find(Docente.class, docente.getIdDocente());
            AreasDocente idareaOld = persistentDocente.getIdarea();
            AreasDocente idareaNew = docente.getIdarea();
            if (idareaNew != null) {
                idareaNew = em.getReference(idareaNew.getClass(), idareaNew.getIdArea());
                docente.setIdarea(idareaNew);
            }
            docente = em.merge(docente);
            if (idareaOld != null && !idareaOld.equals(idareaNew)) {
                idareaOld.getDocenteList().remove(docente);
                idareaOld = em.merge(idareaOld);
            }
            if (idareaNew != null && !idareaNew.equals(idareaOld)) {
                idareaNew.getDocenteList().add(docente);
                idareaNew = em.merge(idareaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = docente.getIdDocente();
                if (findDocente(id) == null) {
                    throw new NonexistentEntityException("The docente with id " + id + " no longer exists.");
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
            Docente docente;
            try {
                docente = em.getReference(Docente.class, id);
                docente.getIdDocente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docente with id " + id + " no longer exists.", enfe);
            }
            AreasDocente idarea = docente.getIdarea();
            if (idarea != null) {
                idarea.getDocenteList().remove(docente);
                idarea = em.merge(idarea);
            }
            em.remove(docente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Docente> listarDocentes(){
        EntityManager em =getEntityManager();
        Query query = em.createQuery("SELECT d FROM Docente d where d.ind='S'");
        List<Docente> list = (List<Docente>) query.getResultList();
        return list;
    }

    public List<Docente> findDocenteEntities() {
        return findDocenteEntities(true, -1, -1);
    }

    public List<Docente> findDocenteEntities(int maxResults, int firstResult) {
        return findDocenteEntities(false, maxResults, firstResult);
    }

    private List<Docente> findDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docente.class));
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

    public Docente findDocente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docente> rt = cq.from(Docente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
