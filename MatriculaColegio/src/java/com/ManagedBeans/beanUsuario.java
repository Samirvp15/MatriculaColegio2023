/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ManagedBeans;

/**/
import com.Entidades.*;
import com.Controlador.*;
import java.util.*;
import javax.swing.JOptionPane;

public class beanUsuario {

    private int idusuario;
    private String nombre;
    private String apellido;
    private String dni;
    private String usuario;
    private String contrasena;

    public beanUsuario() {
    }

    //metodos 
    public List<Usuario> listarUsuarios() {
        try {
            UsuarioJpaController usuctrl = new UsuarioJpaController();
            List<Usuario> list = usuctrl.findUsuarioEntities();
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la recuperacion de datos: " + e);
        }

        return null;
    }

    public void agregarUsuario() {
        try {
            UsuarioJpaController usuctrl = new UsuarioJpaController();
            usuctrl.create(new Usuario(idusuario, nombre, apellido, dni, usuario, contrasena));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede insertar: " + ex);
        }
    }

    public String controlLogin() {
        try {
            UsuarioJpaController usuctrl = new UsuarioJpaController();
            Usuario usuario1 = new Usuario();
            usuario1.setUsuario(usuario);
            usuario1.setContrasena(contrasena);

            Usuario usuario2 = usuctrl.verificarUsuario(usuario1);

            if (usuario2 != null) {
                //EL USUARIO INICIO SESION CORRECTAMENTE Y ACCDEDE AL SITIO WEB
                return "/Vistas/Estudiantes";
            } else {
                //RECARGA LA PAGINA PORQUE EL USUARIO NO ESTA REGISTRADO EN LA BD
                return null;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede insertar: " + ex);
        }
        return null;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
