
package com.equipoQ.alquilerQuinchos.Entidades;
import javax.persistence.*;
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;

   public Comentario() {
   }

   public Comentario(Long id, String texto, Publicacion publicacion) {
      this.id = id;
      this.texto = texto;
      this.publicacion = publicacion;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTexto() {
      return texto;
   }

   public void setTexto(String texto) {
      this.texto = texto;
   }

   public Publicacion getPublicacion() {
      return publicacion;
   }

   public void setPublicacion(Publicacion publicacion) {
      this.publicacion = publicacion;
   }


}



