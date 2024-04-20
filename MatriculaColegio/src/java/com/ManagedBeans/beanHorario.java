/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ManagedBeans;

import com.Controlador.HorarioJpaController;
import com.Entidades.Clase;
import com.Entidades.Horario;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class beanHorario {

    private Integer idHorario;
    private String diaSemana;
    private String horaInicio;
    private String horaFinal;
    private Character ind;
    private Clase idclase;
    private Horario horarioObj = new Horario();
    private boolean registrationSuccessful;

    public List<Horario> listarHorarios() {
        try {
            HorarioJpaController horarioJpaControllerctrl = new HorarioJpaController();
            List<Horario> list = horarioJpaControllerctrl.listarHorarios();
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron listar los datos. " + e);
        }
        return null;
    }

    public String agregarHorario() {
        try {
            HorarioJpaController horarioJpaControllerctrl = new HorarioJpaController();

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date fechainicio = sdf.parse(horaInicio);
            Date horafinal = sdf.parse(horaFinal);
            Horario horario = new Horario(idHorario, diaSemana, fechainicio, horafinal, 'S', idclase);
            horarioJpaControllerctrl.create(horario);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron insertar los datos. " + ex);
        }
        return null;
    }

    public void obtenerHorario(int id) {
        try {
            HorarioJpaController horarioJpaControllerctrl = new HorarioJpaController();
            this.horarioObj = horarioJpaControllerctrl.findHorario(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron recuperar los datos. " + e);
        }
    }

    public String actualizarHorario(Horario horario) {
        try {
            HorarioJpaController horarioJpaControllerctrl = new HorarioJpaController();
            
            horarioJpaControllerctrl.edit(horario);
            registrationSuccessful = true;
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudieron actualizar los datos. " + ex);
        }
        return null;
    }

    public void eliminarHorario(Horario horario) {
        try {
            HorarioJpaController horctrl = new HorarioJpaController();
            Horario horEliminado = horctrl.findHorario(horario.getIdHorario());
            horEliminado.setInd('N');
            horctrl.edit(horEliminado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo eliminar el registro seleccionado. " + ex);
        }
    }

    public Horario getHorarioObj() {
        return horarioObj;
    }

    public void setHorarioObj(Horario horarioObj) {
        this.horarioObj = horarioObj;
    }

    public boolean isRegistrationSuccessful() {
        return registrationSuccessful;
    }

    public void setRegistrationSuccessful(boolean registrationSuccessful) {
        this.registrationSuccessful = registrationSuccessful;
    }

    public void resetRegistrationStatus() {
        registrationSuccessful = false;
    }

    public beanHorario() {
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Character getInd() {
        return ind;
    }

    public void setInd(Character ind) {
        this.ind = ind;
    }

    public Clase getIdclase() {
        return idclase;
    }

    public void setIdclase(Clase idclase) {
        this.idclase = idclase;
    }

}
