/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.Entidades.Estudiante;
import com.Entidades.Apoderado;
import com.Entidades.Matricula;
import com.Entidades.Salon;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vboxuser
 */
public class MatriculaJpaController implements Serializable {

    public MatriculaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public MatriculaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MatriculaColegioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Matricula matricula) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante idestudiante = matricula.getIdestudiante();
            if (idestudiante != null) {
                idestudiante = em.getReference(idestudiante.getClass(), idestudiante.getIdEstudiante());
                matricula.setIdestudiante(idestudiante);
            }
            Apoderado idapoderado = matricula.getIdapoderado();
            if (idapoderado != null) {
                idapoderado = em.getReference(idapoderado.getClass(), idapoderado.getIdApoderado());
                matricula.setIdapoderado(idapoderado);
            }
            Salon idsalon = matricula.getIdsalon();
            if (idsalon != null) {
                idsalon = em.getReference(idsalon.getClass(), idsalon.getIdSalon());
                matricula.setIdsalon(idsalon);
            }
            em.persist(matricula);
            if (idestudiante != null) {
                idestudiante.getMatriculaList().add(matricula);
                idestudiante = em.merge(idestudiante);
            }
            if (idapoderado != null) {
                idapoderado.getMatriculaList().add(matricula);
                idapoderado = em.merge(idapoderado);
            }
            if (idsalon != null) {
                idsalon.getMatriculaList().add(matricula);
                idsalon = em.merge(idsalon);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Matricula matricula) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Matricula persistentMatricula = em.find(Matricula.class, matricula.getIdMatricula());
            Estudiante idestudianteOld = persistentMatricula.getIdestudiante();
            Estudiante idestudianteNew = matricula.getIdestudiante();
            Apoderado idapoderadoOld = persistentMatricula.getIdapoderado();
            Apoderado idapoderadoNew = matricula.getIdapoderado();
            Salon idsalonOld = persistentMatricula.getIdsalon();
            Salon idsalonNew = matricula.getIdsalon();
            if (idestudianteNew != null) {
                idestudianteNew = em.getReference(idestudianteNew.getClass(), idestudianteNew.getIdEstudiante());
                matricula.setIdestudiante(idestudianteNew);
            }
            if (idapoderadoNew != null) {
                idapoderadoNew = em.getReference(idapoderadoNew.getClass(), idapoderadoNew.getIdApoderado());
                matricula.setIdapoderado(idapoderadoNew);
            }
            if (idsalonNew != null) {
                idsalonNew = em.getReference(idsalonNew.getClass(), idsalonNew.getIdSalon());
                matricula.setIdsalon(idsalonNew);
            }
            matricula = em.merge(matricula);
            if (idestudianteOld != null && !idestudianteOld.equals(idestudianteNew)) {
                idestudianteOld.getMatriculaList().remove(matricula);
                idestudianteOld = em.merge(idestudianteOld);
            }
            if (idestudianteNew != null && !idestudianteNew.equals(idestudianteOld)) {
                idestudianteNew.getMatriculaList().add(matricula);
                idestudianteNew = em.merge(idestudianteNew);
            }
            if (idapoderadoOld != null && !idapoderadoOld.equals(idapoderadoNew)) {
                idapoderadoOld.getMatriculaList().remove(matricula);
                idapoderadoOld = em.merge(idapoderadoOld);
            }
            if (idapoderadoNew != null && !idapoderadoNew.equals(idapoderadoOld)) {
                idapoderadoNew.getMatriculaList().add(matricula);
                idapoderadoNew = em.merge(idapoderadoNew);
            }
            if (idsalonOld != null && !idsalonOld.equals(idsalonNew)) {
                idsalonOld.getMatriculaList().remove(matricula);
                idsalonOld = em.merge(idsalonOld);
            }
            if (idsalonNew != null && !idsalonNew.equals(idsalonOld)) {
                idsalonNew.getMatriculaList().add(matricula);
                idsalonNew = em.merge(idsalonNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = matricula.getIdMatricula();
                if (findMatricula(id) == null) {
                    throw new NonexistentEntityException("The matricula with id " + id + " no longer exists.");
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
            Matricula matricula;
            try {
                matricula = em.getReference(Matricula.class, id);
                matricula.getIdMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The matricula with id " + id + " no longer exists.", enfe);
            }
            Estudiante idestudiante = matricula.getIdestudiante();
            if (idestudiante != null) {
                idestudiante.getMatriculaList().remove(matricula);
                idestudiante = em.merge(idestudiante);
            }
            Apoderado idapoderado = matricula.getIdapoderado();
            if (idapoderado != null) {
                idapoderado.getMatriculaList().remove(matricula);
                idapoderado = em.merge(idapoderado);
            }
            Salon idsalon = matricula.getIdsalon();
            if (idsalon != null) {
                idsalon.getMatriculaList().remove(matricula);
                idsalon = em.merge(idsalon);
            }
            em.remove(matricula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Matricula> listarMatriculas(){
        EntityManager em =getEntityManager();
        Query query = em.createQuery("SELECT m FROM Matricula m where m.estado='S'");
        List<Matricula> list = (List<Matricula>) query.getResultList();
        return list;
    }

    public List<Matricula> findMatriculaEntities() {
        return findMatriculaEntities(true, -1, -1);
    }

    public List<Matricula> findMatriculaEntities(int maxResults, int firstResult) {
        return findMatriculaEntities(false, maxResults, firstResult);
    }

    private List<Matricula> findMatriculaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Matricula.class));
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

    public Matricula findMatricula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Matricula.class, id);
        } finally {
            em.close();
        }
    }

    public int getMatriculaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Matricula> rt = cq.from(Matricula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
