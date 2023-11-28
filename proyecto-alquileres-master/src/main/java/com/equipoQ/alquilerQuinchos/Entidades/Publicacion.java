package com.equipoQ.alquilerQuinchos.Entidades;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import lombok.Data;
@Entity
@Data
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

   
   
}
