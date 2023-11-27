package com.equipoQ.alquilerQuinchos.Entidades;
import com.sun.istack.*;
import java.io.*;
import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import org.hibernate.annotations.*;
@Entity
public class Propiedad implements Serializable {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;

   private String direccion;
   private String localidad;
   
   @Column(name = "provincia")
   private String provincia;

   @Temporal(TemporalType.DATE)
   private Date alta;

   @ManyToOne
   @JoinColumn(name = "usuario_id")
   private Usuario usuario;

   @OneToOne(mappedBy = "propiedad", cascade = CascadeType.ALL)
   private Publicacion publicacion;

   @Column(name = "precio_base")
// @NotNull para validar que el campo no sea nulo
   @NotNull
   private Float precio_base;
   private Boolean wifi;
   private Boolean pileta;
   private Integer capacidad;

   public Propiedad() {
   }

   public Propiedad(String id, String direccion, String localidad, String provincia, Date alta, Usuario usuario, Publicacion publicacion, Float precio_base, Boolean wifi, Boolean pileta, Integer capacidad) {
      this.id = id;
      this.direccion = direccion;
      this.localidad = localidad;
      this.provincia = provincia;
      this.alta = alta;
      this.usuario = usuario;
      this.publicacion = publicacion;
      this.precio_base = precio_base;
      this.wifi = wifi;
      this.pileta = pileta;
      this.capacidad = capacidad;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getDireccion() {
      return direccion;
   }

   public void setDireccion(String direccion) {
      this.direccion = direccion;
   }

   public String getLocalidad() {
      return localidad;
   }

   public void setLocalidad(String localidad) {
      this.localidad = localidad;
   }

   public String getProvincia() {
      return provincia;
   }

   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }

   public Date getAlta() {
      return alta;
   }

   public void setAlta(Date alta) {
      this.alta = alta;
   }

   public Usuario getUsuario() {
      return usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public Publicacion getPublicacion() {
      return publicacion;
   }

   public void setPublicacion(Publicacion publicacion) {
      this.publicacion = publicacion;
   }

   public Float getPrecio_base() {
      return precio_base;
   }

   public void setPrecio_base(Float precio_base) {
      this.precio_base = precio_base;
   }

   public Boolean getWifi() {
      return wifi;
   }

   public void setWifi(Boolean wifi) {
      this.wifi = wifi;
   }

   public Boolean getPileta() {
      return pileta;
   }

   public void setPileta(Boolean pileta) {
      this.pileta = pileta;
   }

   public Integer getCapacidad() {
      return capacidad;
   }

   public void setCapacidad(Integer capacidad) {
      this.capacidad = capacidad;
   }
}