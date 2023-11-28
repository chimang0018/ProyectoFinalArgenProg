
package com.equipoQ.alquilerQuinchos.Entidades;
import javax.persistence.*;
import lombok.Data;
@Data
@Entity

public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;

}