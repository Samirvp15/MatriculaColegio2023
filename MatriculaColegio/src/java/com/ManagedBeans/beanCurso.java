package com.ManagedBeans;

import com.Controlador.CursoJpaController;
import com.Entidades.Curso;
import java.util.List;
import javax.swing.JOptionPane;

public class beanCurso {
    private Integer idCurso;
    private String nombreCurso;
    private Character ind;
    private Curso cur;
    private boolean registrationSuccessful;
    
    public beanCurso(){}
    
    public List<Curso> listarCursos() {
        try {
            CursoJpaController docctrl = new CursoJpaController();
            List<Curso> list = docctrl.listarCursos();
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron listar los datos. " + e);
        }
        return null;
    }

    public String agregarCurso() {
        try {
            CursoJpaController curctrl = new CursoJpaController();
            Curso curso = new Curso(nombreCurso, 'S');
            curctrl.create(curso);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron insertar los datos. " + ex);
        }
        return null;
    }

    public void obtenerCurso(int id) {
        try {
            CursoJpaController curctrl = new CursoJpaController();
            this.cur = curctrl.findCurso(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron recuperar los datos. " + e);
        }
    }

    public String actualizarCurso(Curso curso) {
        try {
            CursoJpaController curctrl = new CursoJpaController();
            curctrl.edit(curso);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron actualizar los datos. " + ex);
        }
        return null;
    }

    public void eliminarCurso(Curso curso) {
        try {
            CursoJpaController curctrl = new CursoJpaController();
            Curso curEliminado = curctrl.findCurso(curso.getIdCurso());
            curEliminado.setInd('N');
            curctrl.edit(curEliminado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo eliminar el registro seleccionado. " + ex);
        }
    }

    public void resetRegistrationStatus() {
        registrationSuccessful = false;
    }

    public Integer getIdCurso() {return idCurso;}
    public void setIdCurso(Integer idCurso) {this.idCurso = idCurso;}
    public String getNombreCurso() {return nombreCurso;}
    public void setNombreCurso(String nombreCurso) {this.nombreCurso = nombreCurso;}
    public Character getInd() {return ind;}
    public void setInd(Character ind) {this.ind = ind;}
    public Curso getCur() {return cur;}
    public void setCur(Curso cur) {this.cur = cur;}
    public boolean isRegistrationSuccessful() {return registrationSuccessful;}
    public void setRegistrationSuccessful(boolean registrationSuccessful) {this.registrationSuccessful = registrationSuccessful;}
}