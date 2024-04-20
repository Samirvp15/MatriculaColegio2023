package com.ManagedBeans;

import com.Controlador.MatriculaJpaController;
import com.Entidades.Apoderado;
import com.Entidades.Estudiante;
import com.Entidades.Matricula;
import com.Entidades.Salon;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class beanMatricula {

    private int idMatricula;
    private String escProcedencia;
    private String esRepitente;
    private String costoMatricula;
    private Date fechaMatricula;
    private String estado;
    private Estudiante idestudiante;
    private Apoderado idapoderado;
    private Salon idsalon;
    private Matricula mat=new Matricula();
    private boolean registrationSuccessful;
    
    public beanMatricula(){}
    
    public List<Matricula> listarMatriculas() {
        try {
            MatriculaJpaController matctrl = new MatriculaJpaController();
            List<Matricula> list = matctrl.listarMatriculas();
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron listar los datos. " + e);
        }
        return null;
    }

    public String agregarMatricula() {
        try {
            MatriculaJpaController matctrl = new MatriculaJpaController();
            Matricula matricula = new Matricula(escProcedencia, esRepitente, costoMatricula, fechaMatricula, "S" , idestudiante, idapoderado, idsalon );
            matctrl.create(matricula);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron insertar los datos. " + ex);
        }
        return null;
    }

    public void obtenerMatricula(int id) {
        try {
            MatriculaJpaController matctrl = new MatriculaJpaController();
            this.mat = matctrl.findMatricula(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron recuperar los datos. " + e);
        }
    }

    public String actualizarMatricula(Matricula matricula) {
        try {
            MatriculaJpaController estctrl = new MatriculaJpaController();
            estctrl.edit(matricula);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron actualizar los datos. " + ex);
        }
        return null;
    }

    public void eliminarMatricula(Matricula matricula) {
        try {
            MatriculaJpaController matctrl = new MatriculaJpaController();
            Matricula matEliminado = matctrl.findMatricula(matricula.getIdMatricula());
            matEliminado.setEstado("N");
            matctrl.edit(matEliminado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo eliminar el registro seleccionado. " + ex);
        }
    }

    public void resetRegistrationStatus() {
        registrationSuccessful = false;
    }  
    public int getIdMatricula() {
        return idMatricula;
    }
    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }
    public String getEscProcedencia() {
        return escProcedencia;
    }
    public void setEscProcedencia(String escProcedencia) {
        this.escProcedencia = escProcedencia;
    }
    public String getEsRepitente() {
        return esRepitente;
    }
    public void setEsRepitente(String esRepitente) {
        this.esRepitente = esRepitente;
    }
    public String getCostoMatricula() {
        return costoMatricula;
    }
    public void setCostoMatricula(String costoMatricula) {
        this.costoMatricula = costoMatricula;
    }
    public Date getFechaMatricula() {
        return fechaMatricula;
    }
    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Estudiante getIdestudiante() {
        return idestudiante;
    }
    public void setIdestudiante(Estudiante idestudiante) {
        this.idestudiante = idestudiante;
    }
    public Apoderado getIdapoderado() {
        return idapoderado;
    }
    public void setIdapoderado(Apoderado idapoderado) {
        this.idapoderado = idapoderado;
    }
    public Salon getIdsalon() {
        return idsalon;
    }
    public void setIdsalon(Salon idsalon) {
        this.idsalon = idsalon;
    }
    public Matricula getMat() {
        return mat;
    }
    public void setMat(Matricula mat) {
        this.mat = mat;
    }
    public boolean isRegistrationSuccessful() {
        return registrationSuccessful;
    }
    public void setRegistrationSuccessful(boolean registrationSuccessful) {
        this.registrationSuccessful = registrationSuccessful;
    }  
}
