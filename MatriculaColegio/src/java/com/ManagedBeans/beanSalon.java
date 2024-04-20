package com.ManagedBeans;

import com.Entidades.Salon;
import com.Controlador.SalonJpaController;
import java.util.List;
import javax.swing.JOptionPane;

public class beanSalon {
    private String idSalon;
    private String grado;
    private String estado;
    private Salon sal;
    private boolean registrationSuccessful;
    
    public beanSalon(){}
    
    public List<Salon> listarSalones() {
        try {
            SalonJpaController salctrl = new SalonJpaController();
            List<Salon> list = salctrl.listarSalones();
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron listar los datos. " + e);
        }
        return null;
    }

    public String agregarSalon() {
        try {
            SalonJpaController salctrl = new SalonJpaController();
            Salon salon = new Salon(idSalon,grado,"S");
            salctrl.create(salon);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron insertar los datos. " + ex);
        }
        return null;
    }

    public void obtenerSalon(String id) {
        try {
            SalonJpaController salctrl = new SalonJpaController();
            this.sal = salctrl.findSalon(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron recuperar los datos. " + e);
        }
    }

    public String actualizarSalon(Salon salon) {
        try {
            SalonJpaController salctrl = new SalonJpaController();
            salctrl.edit(salon);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron actualizar los datos. " + ex);
        }
        return null;
    }

    public void eliminarSalon(Salon salon) {
        try {
            SalonJpaController salctrl = new SalonJpaController();
            Salon salEliminado = salctrl.findSalon(salon.getIdSalon());
            salEliminado.setEstado("N");
            salctrl.edit(salEliminado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo eliminar el registro seleccionado. " + ex);
        }
    }

    public void resetRegistrationStatus() {
        registrationSuccessful = false;
    }

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
    public String getIdSalon() {return idSalon;}
    public void setIdSalon(String idSalon) {this.idSalon = idSalon;}
    public String getGrado() {return grado;}
    public void setGrado(String grado) {this.grado = grado;}
    public Salon getSal() {return sal;}
    public void setSal(Salon sal) {this.sal = sal;}
    public boolean isRegistrationSuccessful() {return registrationSuccessful;}
    public void setRegistrationSuccessful(boolean registrationSuccessful) {this.registrationSuccessful = registrationSuccessful;}
}