package com.equipoQ.alquilerQuinchos.Entidades;
import com.sun.istack.*;
import java.io.*;
import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import lombok.Data;
import org.hibernate.annotations.*;
@Entity
@Data
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

}