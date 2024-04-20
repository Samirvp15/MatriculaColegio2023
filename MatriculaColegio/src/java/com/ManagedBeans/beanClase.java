package com.ManagedBeans;

import com.Entidades.Clase;
import com.Entidades.Curso;
import com.Entidades.Salon;
import com.Controlador.ClaseJpaController;
import java.util.List;
import javax.swing.JOptionPane;

public class beanClase {
    private Integer idClase;
    private Character ind;
    private Salon idsalon;
    private Curso idcurso;
    private Clase clas=new Clase();
    private boolean registrationSuccessful;
    
    public beanClase(){}
    
    public List<Clase> listarClases() {
        try {
            ClaseJpaController clasctrl = new ClaseJpaController();
            List<Clase> list = clasctrl.listarClases();
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron listar los datos. " + e);
        }
        return null;
    }

    public String agregarClase() {
        try {
            ClaseJpaController clasctrl = new ClaseJpaController();
            Clase clase = new Clase('S',idsalon,idcurso);
            clasctrl.create(clase);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron insertar los datos. " + ex);
        }
        return null;
    }

    public void obtenerClase(int id) {
        try {
            ClaseJpaController clasctrl = new ClaseJpaController();
            this.clas = clasctrl.findClase(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron recuperar los datos. " + e);
        }
    }

    public String actualizarClase(Clase clase) {
        try {
            ClaseJpaController clasctrl = new ClaseJpaController();
            clasctrl.edit(clase);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron actualizar los datos. " + ex);
        }
        return null;
    }

    public void eliminarDocente(Clase clase) {
        try {
            ClaseJpaController clasctrl = new ClaseJpaController();
            Clase clasEliminado = clasctrl.findClase(clase.getIdClase());
            clasEliminado.setInd('N');
            clasctrl.edit(clasEliminado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo eliminar el registro seleccionado. " + ex);
        }
    }

    public void resetRegistrationStatus() {
        registrationSuccessful = false;
    }

    public Integer getIdClase() {return idClase;}
    public void setIdClase(Integer idClase) {this.idClase = idClase;}
    public Character getInd() {return ind;}
    public void setInd(Character ind) {this.ind = ind;}
    public Salon getIdsalon() {return idsalon;}
    public void setIdsalon(Salon idsalon) {this.idsalon = idsalon;}
    public Curso getIdcurso() {return idcurso;}
    public void setIdcurso(Curso idcurso) {this.idcurso = idcurso;}
    public Clase getClas() {return clas;}
    public void setClas(Clase clas) {this.clas = clas;}
    public boolean isRegistrationSuccessful() {return registrationSuccessful;}
    public void setRegistrationSuccessful(boolean registrationSuccessful) {this.registrationSuccessful = registrationSuccessful;}
}
