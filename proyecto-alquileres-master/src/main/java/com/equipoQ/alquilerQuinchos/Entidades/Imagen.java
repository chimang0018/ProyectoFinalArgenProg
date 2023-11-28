package com.equipoQ.alquilerQuinchos.Entidades;
import javax.persistence.*;
import org.hibernate.annotations.*;
import javax.persistence.Entity;
import lombok.Data;
@Entity
@Data
public class Imagen {
   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
   private String mime;
   private String nombre;
//   Con las siguientes anotaciones decimos que puede ocupar mas espacio y su tipo de carga va a ser perezosa
//   que solo se carga si se hace un get
   @Lob @Basic(fetch = FetchType.LAZY)
   private byte[] contenido;
    @ManyToOne
   private Publicacion publicacion;

  

}