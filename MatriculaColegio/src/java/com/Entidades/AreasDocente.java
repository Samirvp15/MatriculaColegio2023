/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vboxuser
 */
@Entity
@Table(name = "areas_docente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreasDocente.findAll", query = "SELECT a FROM AreasDocente a")
    , @NamedQuery(name = "AreasDocente.findByIdArea", query = "SELECT a FROM AreasDocente a WHERE a.idArea = :idArea")
    , @NamedQuery(name = "AreasDocente.findByNombreArea", query = "SELECT a FROM AreasDocente a WHERE a.nombreArea = :nombreArea")
    , @NamedQuery(name = "AreasDocente.findByEstado", query = "SELECT a FROM AreasDocente a WHERE a.estado = :estado")})
public class AreasDocente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area")
    private Integer idArea;
    @Size(max = 20)
    @Column(name = "nombre_area")
    private String nombreArea;
    @Size(max = 10)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "idarea")
    private List<Docente> docenteList;

    public AreasDocente() {
    }

    public AreasDocente(Integer idArea) {
        this.idArea = idArea;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Docente> getDocenteList() {
        return docenteList;
    }

    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreasDocente)) {
            return false;
        }
        AreasDocente other = (AreasDocente) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entidades.AreasDocente[ idArea=" + idArea + " ]";
    }
    
}
