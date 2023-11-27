package com.equipoQ.alquilerQuinchos.Entidades;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
@Entity
public class Publicacion {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;

   @Column(name = "titulo", length = 100)
   private String titulo;
   
   @Column(name = "descripcion", length = 500)
   private String descripcion;
     
   @Column(name = "preciofinal")
   private Float preciofinal;
   private String categoria;
   @OneToOne
   @JoinColumn(name = "id_propiedad")
   private Propiedad propiedad;

   // mappedBy indica el campo que mapea la relación inversa en la entidad Comentario
   // cascade indica que las operaciones se propagan a las entidades relacionadas   
   @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
   private List<Comentario> comentarios;
   //mappedBy para indicar el campo que mapea la relación inversa en la entidad Imagen
   //cascade indica que las operaciones se propagan a las entidades relacionadas
   @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
   private List<Imagen> imagenes;

   public Publicacion() {
   }

   public Publicacion(String id, String titulo, String descripcion, Float preciofinal, String categoria, Propiedad propiedad, List<Comentario> comentarios, List<Imagen> imagenes) {
      this.id = id;
      this.titulo = titulo;
      this.descripcion = descripcion;
      this.preciofinal = preciofinal;
      this.categoria = categoria;
      this.propiedad = propiedad;
      this.comentarios = comentarios;
      this.imagenes = imagenes;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitulo() {
      return titulo;
   }

   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }

   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public Float getPreciofinal() {
      return preciofinal;
   }

   public void setPreciofinal(Float preciofinal) {
      this.preciofinal = preciofinal;
   }

   public String getCategoria() {
      return categoria;
   }

   public void setCategoria(String categoria) {
      this.categoria = categoria;
   }

   public Propiedad getPropiedad() {
      return propiedad;
   }

   public void setPropiedad(Propiedad propiedad) {
      this.propiedad = propiedad;
   }

   public List<Comentario> getComentarios() {
      return comentarios;
   }

   public void setComentarios(List<Comentario> comentarios) {
      this.comentarios = comentarios;
   }

   public List<Imagen> getImagenes() {
      return imagenes;
   }

   public void setImagenes(List<Imagen> imagenes) {
      this.imagenes = imagenes;
   }
   
}
