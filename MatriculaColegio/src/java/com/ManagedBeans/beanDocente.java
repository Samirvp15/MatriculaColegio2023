package com.ManagedBeans;

import java.util.Date;
import com.Entidades.Docente;
import com.Entidades.AreasDocente;
import com.Controlador.DocenteJpaController;
import java.util.List;
import javax.swing.JOptionPane;

public class beanDocente {
    private int idDocente;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaNac;
    private String direccion;
    private String correo;
    private String telefono;
    private AreasDocente idArea;
    private Character ind;
    private Docente doc=new Docente();
    private boolean registrationSuccessful;
    
    public beanDocente(){}
    
    public List<Docente> listarDocentes() {
        try {
            DocenteJpaController docctrl = new DocenteJpaController();
            List<Docente> list = docctrl.listarDocentes();
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron listar los datos. " + e);
        }
        return null;
    }

    public String agregarDocente() {
        try {
            DocenteJpaController docctrl = new DocenteJpaController();
            Docente docente = new Docente(nombre, apellido, dni, fechaNac, direccion, correo, telefono, 'S', idArea);
            docctrl.create(docente);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron insertar los datos. " + ex);
        }
        return null;
    }

    public void obtenerDocente(int id) {
        try {
            DocenteJpaController docctrl = new DocenteJpaController();
            this.doc = docctrl.findDocente(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron recuperar los datos. " + e);
        }
    }

    public String actualizarDocente(Docente docente) {
        try {
            DocenteJpaController estctrl = new DocenteJpaController();
            estctrl.edit(docente);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron actualizar los datos. " + ex);
        }
        return null;
    }

    public void eliminarDocente(Docente docente) {
        try {
            DocenteJpaController docctrl = new DocenteJpaController();
            Docente docEliminado = docctrl.findDocente(docente.getIdDocente());
            docEliminado.setInd('N');
            docctrl.edit(docEliminado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo eliminar el registro seleccionado. " + ex);
        }
    }

    public void resetRegistrationStatus() {
        registrationSuccessful = false;
    }

    public int getIdDocente() {return idDocente;}
    public void setIdDocente(int idDocente) {this.idDocente = idDocente;}
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
    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public AreasDocente getIdArea() {return idArea;}
    public void setIdArea(AreasDocente idArea) {this.idArea = idArea;}
    public Character getInd() {return ind;}
    public void setInd(Character ind) {this.ind = ind;}
    public Docente getDoc() {return doc;}
    public void setDoc(Docente doc) {this.doc = doc;}
    public boolean isRegistrationSuccessful() {return registrationSuccessful;}
    public void setRegistrationSuccessful(boolean registrationSuccessful) {this.registrationSuccessful = registrationSuccessful;}
}