package com.equipoQ.alquilerQuinchos.Entidades;
import javax.persistence.*;
import lombok.Data;
@Entity
@Data
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

  
}
