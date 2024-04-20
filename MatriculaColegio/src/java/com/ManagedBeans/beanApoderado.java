package com.ManagedBeans;

import com.Controlador.ApoderadoJpaController;
import com.Entidades.Apoderado;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class beanApoderado {
    private Integer idApoderado;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaNac;
    private String direccion;
    private String telefono;
    private Character ind;
    private Apoderado apo;
    private boolean registrationSuccessful;
    
    public beanApoderado(){}
    
    public List<Apoderado> listarApoderados() {
        try {
            ApoderadoJpaController apoctrl = new ApoderadoJpaController();
            List<Apoderado> list = apoctrl.listarApoderados();
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la recuperacion de datos: " + e);
        }
        return null;
    }

    public String agregarApoderado() {
        try {
            ApoderadoJpaController apoctrl = new ApoderadoJpaController();
            Apoderado apoderado = new Apoderado(nombre, apellido, dni, fechaNac, direccion,telefono, 'S');
            apoctrl.create(apoderado);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede insertar.." + ex);
        }
        return null;

    }

    public void obtenerApoderado(int id) {
        try {
            ApoderadoJpaController estctrl = new ApoderadoJpaController();
            this.apo = estctrl.findApoderado(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la recuperacion de datos: " + e);
        }
    }

    public String actualizarApoderado(Apoderado apoderado) {
        try {
            ApoderadoJpaController apoctrl = new ApoderadoJpaController();
            apoctrl.edit(apoderado);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede actualizar.." + ex);
        }
        return null;
    }

    public void eliminarApoderado(Apoderado apoderado) {
        try {
            ApoderadoJpaController apoctrl = new ApoderadoJpaController();
            Apoderado apoEliminado = apoctrl.findApoderado(apoderado.getIdApoderado());
            apoEliminado.setInd('N');
            apoctrl.edit(apoEliminado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede eliminar.." + ex);
        }
    }

    public void resetRegistrationStatus() {
        registrationSuccessful = false;
    }

    public Integer getIdApoderado() {return idApoderado;}
    public void setIdApoderado(Integer idApoderado) {this.idApoderado = idApoderado;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}
    public Date getFechaNac() {return fechaNac;}
    public void setFechaNac(Date fechaNac) {this.fechaNac = fechaNac;}
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public Character getInd() {return ind;}
    public void setInd(Character ind) {this.ind = ind;}
    public Apoderado getApo() {return apo;}
    public void setApo(Apoderado apo) {this.apo = apo;}
    public boolean isRegistrationSuccessful() {return registrationSuccessful;}
    public void setRegistrationSuccessful(boolean registrationSuccessful) {this.registrationSuccessful = registrationSuccessful;}
}
