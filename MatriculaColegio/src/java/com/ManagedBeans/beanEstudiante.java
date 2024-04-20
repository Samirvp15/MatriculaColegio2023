package com.ManagedBeans;

import com.Entidades.Estudiante;
import com.Controlador.EstudianteJpaController;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class beanEstudiante implements Serializable {
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaNac;
    private String direccion;
    private Character ind;
    private Estudiante est;
    private boolean registrationSuccessful;

    public beanEstudiante() {
    }

    //metodos CRUD JPA
    public List<Estudiante> listarEstudiantes() {
        try {
            EstudianteJpaController estctrl = new EstudianteJpaController();
            List<Estudiante> list = estctrl.listarEstudiantes();
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la recuperacion de datos: " + e);
        }
        return null;
    }

    public String agregarEstudiante() {
        try {
            EstudianteJpaController estctrl = new EstudianteJpaController();
            Estudiante estudiante = new Estudiante(nombre, apellido, dni, fechaNac, direccion, 'S');
            estctrl.create(estudiante);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede insertar.." + ex);
        }
        return null;

    }

    public void obtenerEstudiante(int id) {
        try {
            EstudianteJpaController estctrl = new EstudianteJpaController();
            this.est = estctrl.findEstudiante(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la recuperacion de estudiante: " + e);
        }
    }

    public String actualizarEstudiante(Estudiante estudiante) {
        try {
            EstudianteJpaController estctrl = new EstudianteJpaController();
            estctrl.edit(estudiante);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede actualizar.." + ex);
        }
        return null;
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        try {
            EstudianteJpaController estctrl = new EstudianteJpaController();
            Estudiante estEliminado = estctrl.findEstudiante(estudiante.getIdEstudiante());
            estEliminado.setInd('N');
            estctrl.edit(estEliminado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede eliminar.." + ex);
        }
    }

    public void resetRegistrationStatus() {
        registrationSuccessful = false;
    }

    public boolean isRegistrationSuccessful() {
        return registrationSuccessful;
    }

    public void setRegistrationSuccessful(boolean registrationSuccessful) {
        this.registrationSuccessful = registrationSuccessful;
    }

    public Estudiante getEst() {
        return est;
    }

    public void setEst(Estudiante est) {
        this.est = est;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Character getInd() {
        return ind;
    }

    public void setInd(Character ind) {
        this.ind = ind;
    }

}
