package com.equipoQ.alquilerQuinchos.Entidades;
import javax.persistence.*;
@Entity
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;

    @ManyToOne
    @JoinColumn(name = "emisor_id")
    private Usuario emisor;

    @ManyToOne
    @JoinColumn(name = "receptor_id")
    private Usuario receptor;

   public Mensaje() {
   }

   public Mensaje(Long id, String texto, Usuario emisor, Usuario receptor) {
      this.id = id;
      this.texto = texto;
      this.emisor = emisor;
      this.receptor = receptor;
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

   public Usuario getEmisor() {
      return emisor;
   }

   public void setEmisor(Usuario emisor) {
      this.emisor = emisor;
   }

   public Usuario getReceptor() {
      return receptor;
   }

   public void setReceptor(Usuario receptor) {
      this.receptor = receptor;
   }
   
}
