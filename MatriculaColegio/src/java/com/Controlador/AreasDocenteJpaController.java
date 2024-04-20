package com.Controlador;

import com.Controlador.exceptions.NonexistentEntityException;
import com.Entidades.AreasDocente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.Entidades.Docente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AreasDocenteJpaController implements Serializable {

    public AreasDocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public AreasDocenteJpaController(){
        this.emf = Persistence.createEntityManagerFactory("MatriculaColegioPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AreasDocente areasDocente) {
        if (areasDocente.getDocenteList() == null) {
            areasDocente.setDocenteList(new ArrayList<Docente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : areasDocente.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getIdDocente());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            areasDocente.setDocenteList(attachedDocenteList);
            em.persist(areasDocente);
            for (Docente docenteListDocente : areasDocente.getDocenteList()) {
                AreasDocente oldIdareaOfDocenteListDocente = docenteListDocente.getIdarea();
                docenteListDocente.setIdarea(areasDocente);
                docenteListDocente = em.merge(docenteListDocente);
                if (oldIdareaOfDocenteListDocente != null) {
                    oldIdareaOfDocenteListDocente.getDocenteList().remove(docenteListDocente);
                    oldIdareaOfDocenteListDocente = em.merge(oldIdareaOfDocenteListDocente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AreasDocente areasDocente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreasDocente persistentAreasDocente = em.find(AreasDocente.class, areasDocente.getIdArea());
            List<Docente> docenteListOld = persistentAreasDocente.getDocenteList();
            List<Docente> docenteListNew = areasDocente.getDocenteList();
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getIdDocente());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            areasDocente.setDocenteList(docenteListNew);
            areasDocente = em.merge(areasDocente);
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    docenteListOldDocente.setIdarea(null);
                    docenteListOldDocente = em.merge(docenteListOldDocente);
                }
            }
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    AreasDocente oldIdareaOfDocenteListNewDocente = docenteListNewDocente.getIdarea();
                    docenteListNewDocente.setIdarea(areasDocente);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                    if (oldIdareaOfDocenteListNewDocente != null && !oldIdareaOfDocenteListNewDocente.equals(areasDocente)) {
                        oldIdareaOfDocenteListNewDocente.getDocenteList().remove(docenteListNewDocente);
                        oldIdareaOfDocenteListNewDocente = em.merge(oldIdareaOfDocenteListNewDocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = areasDocente.getIdArea();
                if (findAreasDocente(id) == null) {
                    throw new NonexistentEntityException("The areasDocente with id " + id + " no longer exists.");
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
            AreasDocente areasDocente;
            try {
                areasDocente = em.getReference(AreasDocente.class, id);
                areasDocente.getIdArea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areasDocente with id " + id + " no longer exists.", enfe);
            }
            List<Docente> docenteList = areasDocente.getDocenteList();
            for (Docente docenteListDocente : docenteList) {
                docenteListDocente.setIdarea(null);
                docenteListDocente = em.merge(docenteListDocente);
            }
            em.remove(areasDocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AreasDocente> findAreasDocenteEntities() {
        return findAreasDocenteEntities(true, -1, -1);
    }

    public List<AreasDocente> findAreasDocenteEntities(int maxResults, int firstResult) {
        return findAreasDocenteEntities(false, maxResults, firstResult);
    }

    private List<AreasDocente> findAreasDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AreasDocente.class));
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

    public AreasDocente findAreasDocente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AreasDocente.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreasDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AreasDocente> rt = cq.from(AreasDocente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
