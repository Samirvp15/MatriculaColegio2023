/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Controlador.exceptions.NonexistentEntityException;
import com.Entidades.Clase;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.Entidades.Salon;
import com.Entidades.Curso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vboxuser
 */
public class ClaseJpaController implements Serializable {

    public ClaseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ClaseJpaController(){
        this.emf = Persistence.createEntityManagerFactory("MatriculaColegioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clase clase) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salon idsalon = clase.getIdsalon();
            if (idsalon != null) {
                idsalon = em.getReference(idsalon.getClass(), idsalon.getIdSalon());
                clase.setIdsalon(idsalon);
            }
            Curso idcurso = clase.getIdcurso();
            if (idcurso != null) {
                idcurso = em.getReference(idcurso.getClass(), idcurso.getIdCurso());
                clase.setIdcurso(idcurso);
            }
            em.persist(clase);
            if (idsalon != null) {
                idsalon.getClaseList().add(clase);
                idsalon = em.merge(idsalon);
            }
            if (idcurso != null) {
                idcurso.getClaseList().add(clase);
                idcurso = em.merge(idcurso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clase clase) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clase persistentClase = em.find(Clase.class, clase.getIdClase());
            Salon idsalonOld = persistentClase.getIdsalon();
            Salon idsalonNew = clase.getIdsalon();
            Curso idcursoOld = persistentClase.getIdcurso();
            Curso idcursoNew = clase.getIdcurso();
            if (idsalonNew != null) {
                idsalonNew = em.getReference(idsalonNew.getClass(), idsalonNew.getIdSalon());
                clase.setIdsalon(idsalonNew);
            }
            if (idcursoNew != null) {
                idcursoNew = em.getReference(idcursoNew.getClass(), idcursoNew.getIdCurso());
                clase.setIdcurso(idcursoNew);
            }
            clase = em.merge(clase);
            if (idsalonOld != null && !idsalonOld.equals(idsalonNew)) {
                idsalonOld.getClaseList().remove(clase);
                idsalonOld = em.merge(idsalonOld);
            }
            if (idsalonNew != null && !idsalonNew.equals(idsalonOld)) {
                idsalonNew.getClaseList().add(clase);
                idsalonNew = em.merge(idsalonNew);
            }
            if (idcursoOld != null && !idcursoOld.equals(idcursoNew)) {
                idcursoOld.getClaseList().remove(clase);
                idcursoOld = em.merge(idcursoOld);
            }
            if (idcursoNew != null && !idcursoNew.equals(idcursoOld)) {
                idcursoNew.getClaseList().add(clase);
                idcursoNew = em.merge(idcursoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clase.getIdClase();
                if (findClase(id) == null) {
                    throw new NonexistentEntityException("The clase with id " + id + " no longer exists.");
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
            Clase clase;
            try {
                clase = em.getReference(Clase.class, id);
                clase.getIdClase();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clase with id " + id + " no longer exists.", enfe);
            }
            Salon idsalon = clase.getIdsalon();
            if (idsalon != null) {
                idsalon.getClaseList().remove(clase);
                idsalon = em.merge(idsalon);
            }
            Curso idcurso = clase.getIdcurso();
            if (idcurso != null) {
                idcurso.getClaseList().remove(clase);
                idcurso = em.merge(idcurso);
            }
            em.remove(clase);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Clase> listarClases(){
        EntityManager em =getEntityManager();
        Query query = em.createQuery("SELECT c FROM Clase c where c.ind='S'");
        List<Clase> list = (List<Clase>) query.getResultList();
        return list;
    }

    public List<Clase> findClaseEntities() {
        return findClaseEntities(true, -1, -1);
    }

    public List<Clase> findClaseEntities(int maxResults, int firstResult) {
        return findClaseEntities(false, maxResults, firstResult);
    }

    private List<Clase> findClaseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clase.class));
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

    public Clase findClase(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clase.class, id);
        } finally {
            em.close();
        }
    }

    public int getClaseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clase> rt = cq.from(Clase.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
