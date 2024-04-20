/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vboxuser
 */
@Entity
@Table(name = "matricula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m")
    , @NamedQuery(name = "Matricula.findByIdMatricula", query = "SELECT m FROM Matricula m WHERE m.idMatricula = :idMatricula")
    , @NamedQuery(name = "Matricula.findByEscProcedencia", query = "SELECT m FROM Matricula m WHERE m.escProcedencia = :escProcedencia")
    , @NamedQuery(name = "Matricula.findByEsRepitente", query = "SELECT m FROM Matricula m WHERE m.esRepitente = :esRepitente")
    , @NamedQuery(name = "Matricula.findByCostoMatricula", query = "SELECT m FROM Matricula m WHERE m.costoMatricula = :costoMatricula")
    , @NamedQuery(name = "Matricula.findByFechaMatricula", query = "SELECT m FROM Matricula m WHERE m.fechaMatricula = :fechaMatricula")
    , @NamedQuery(name = "Matricula.findByEstado", query = "SELECT m FROM Matricula m WHERE m.estado = :estado")})
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_matricula")
    private Integer idMatricula;
    @Size(max = 50)
    @Column(name = "esc_procedencia")
    private String escProcedencia;
    @Size(max = 2)
    @Column(name = "esRepitente")
    private String esRepitente;
    @Size(max = 10)
    @Column(name = "costo_matricula")
    private String costoMatricula;
    @Column(name = "fecha_matricula")
    @Temporal(TemporalType.DATE)
    private Date fechaMatricula;
    @Size(max = 10)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "idestudiante", referencedColumnName = "id_estudiante")
    @ManyToOne
    private Estudiante idestudiante;
    @JoinColumn(name = "idapoderado", referencedColumnName = "id_apoderado")
    @ManyToOne
    private Apoderado idapoderado;
    @JoinColumn(name = "idsalon", referencedColumnName = "id_salon")
    @ManyToOne
    private Salon idsalon;

    public Matricula() {
    }

    public Matricula(String escProcedencia, String esRepitente, String costoMatricula, Date fechaMatricula, String estado, Estudiante idestudiante, Apoderado idapoderado, Salon idsalon) {
        this.escProcedencia = escProcedencia;
        this.esRepitente = esRepitente;
        this.costoMatricula = costoMatricula;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
        this.idestudiante = idestudiante;
        this.idapoderado = idapoderado;
        this.idsalon = idsalon;
    }
    
    

    public Matricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatricula != null ? idMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.idMatricula == null && other.idMatricula != null) || (this.idMatricula != null && !this.idMatricula.equals(other.idMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entidades.Matricula[ idMatricula=" + idMatricula + " ]";
    }
    
}
