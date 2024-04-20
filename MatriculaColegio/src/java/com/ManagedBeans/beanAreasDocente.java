package com.ManagedBeans;

import java.util.List;
import com.Entidades.AreasDocente;
import com.Controlador.AreasDocenteJpaController;
import javax.swing.JOptionPane;

public class beanAreasDocente {
    private int idArea;
    private String nombreArea;
    private String estado;

    public beanAreasDocente() {
    }

    public beanAreasDocente(int idArea, String nombreArea, String estado) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.estado = estado;
    }
    
    public List<AreasDocente> listarAreasDocentes() {
        try {
            AreasDocenteJpaController adoctrl = new AreasDocenteJpaController();
            List<AreasDocente> list = adoctrl.findAreasDocenteEntities();
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron listar los datos. " + e);
        }
        return null;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
}
