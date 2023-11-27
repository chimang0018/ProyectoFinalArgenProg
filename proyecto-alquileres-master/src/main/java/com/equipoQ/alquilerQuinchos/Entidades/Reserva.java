package com.equipoQ.alquilerQuinchos.Entidades;
import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
@Entity
public class Reserva {

@Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
//representa una fecha sin hora ni zona horaria, como 2023-11-23.
private LocalDate fecha;
//representa una fecha y hora sin zona horaria, como 2023-11-23T02:13:35.
//private DateTime hora;
private Date duracion;

//Reserva, y que tendría los atributos: id, fecha, hora, duración, estado, etc.

   public Reserva() {
   }

   public Reserva(String id, LocalDate fecha, Date duracion) {
      this.id = id;
      this.fecha = fecha;
      this.duracion = duracion;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public LocalDate getFecha() {
      return fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public Date getDuracion() {
      return duracion;
   }

   public void setDuracion(Date duracion) {
      this.duracion = duracion;
   }

}
