/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vboxuser
 */
@Entity
@Table(name = "apoderado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apoderado.findAll", query = "SELECT a FROM Apoderado a")
    , @NamedQuery(name = "Apoderado.findByIdApoderado", query = "SELECT a FROM Apoderado a WHERE a.idApoderado = :idApoderado")
    , @NamedQuery(name = "Apoderado.findByNombre", query = "SELECT a FROM Apoderado a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Apoderado.findByApellido", query = "SELECT a FROM Apoderado a WHERE a.apellido = :apellido")
    , @NamedQuery(name = "Apoderado.findByDni", query = "SELECT a FROM Apoderado a WHERE a.dni = :dni")
    , @NamedQuery(name = "Apoderado.findByFechaNac", query = "SELECT a FROM Apoderado a WHERE a.fechaNac = :fechaNac")
    , @NamedQuery(name = "Apoderado.findByDireccion", query = "SELECT a FROM Apoderado a WHERE a.direccion = :direccion")
    , @NamedQuery(name = "Apoderado.findByTelefono", query = "SELECT a FROM Apoderado a WHERE a.telefono = :telefono")
    , @NamedQuery(name = "Apoderado.findByInd", query = "SELECT a FROM Apoderado a WHERE a.ind = :ind")})
public class Apoderado implements Serializable {

    @OneToMany(mappedBy = "idapoderado")
    private List<Matricula> matriculaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_apoderado")
    private Integer idApoderado;
    @Size(max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 20)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 8)
    @Column(name = "dni")
    private String dni;
    @Column(name = "fecha_nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    @Size(max = 30)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 10)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ind")
    private Character ind;

    public Apoderado() {
    }

    public Apoderado(String nombre, String apellido, String dni, Date fechaNac, String direccion, String telefono, Character ind) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ind = ind;
    }

    public Apoderado(Integer idApoderado) {
        this.idApoderado = idApoderado;
    }

    public Apoderado(Integer idApoderado, Character ind) {
        this.idApoderado = idApoderado;
        this.ind = ind;
    }

    public Integer getIdApoderado() {
        return idApoderado;
    }

    public void setIdApoderado(Integer idApoderado) {
        this.idApoderado = idApoderado;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Character getInd() {
        return ind;
    }

    public void setInd(Character ind) {
        this.ind = ind;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idApoderado != null ? idApoderado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apoderado)) {
            return false;
        }
        Apoderado other = (Apoderado) object;
        if ((this.idApoderado == null && other.idApoderado != null) || (this.idApoderado != null && !this.idApoderado.equals(other.idApoderado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entidades.Apoderado[ idApoderado=" + idApoderado + " ]";
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }
    
}
