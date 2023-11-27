package com.equipoQ.alquilerQuinchos.Entidades;
import com.equipoQ.alquilerQuinchos.Enumeraciones.*;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;
import org.hibernate.annotations.*;
//import lombok.Data; crea el geter and seter automaticamente

@Entity
public class Usuario {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;   
   private String nombre;
   @Column(name = "email", length = 50, unique = true)
   private String email;
   @Column(name = "password")
   private String password;  
   @Enumerated(EnumType.STRING)
   private Rol rol;
   // @JoinColumn indica nombre de la columna que hace referencia a la entidad relacionada
   @OneToOne
   @JoinColumn(name = "imagen_id")
   private Imagen imagen;
//   (mappedBy = "usuario", cascade = CascadeType.ALL) tendria que ver para aplicarlo si borro un usuario se borra todo lo que tiene 
   @OneToMany   
   private List<Propiedad> propiedades;

   public Usuario() {
   }

   public Usuario(String id, String nombre, String email, String password, Rol rol, Imagen imagen, List<Propiedad> propiedades) {
      this.id = id;
      this.nombre = nombre;
      this.email = email;
      this.password = password;
      this.rol = rol;
      this.imagen = imagen;
      this.propiedades = propiedades;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Rol getRol() {
      return rol;
   }

   public void setRol(Rol rol) {
      this.rol = rol;
   }

   public Imagen getImagen() {
      return imagen;
   }

   public void setImagen(Imagen imagen) {
      this.imagen = imagen;
   }

   public List<Propiedad> getPropiedades() {
      return propiedades;
   }

   public void setPropiedades(List<Propiedad> propiedades) {
      this.propiedades = propiedades;
   }
   
}

