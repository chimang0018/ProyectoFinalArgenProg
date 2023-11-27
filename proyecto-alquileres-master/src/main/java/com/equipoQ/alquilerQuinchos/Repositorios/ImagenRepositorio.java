package com.equipoQ.alquilerQuinchos.Repositorios;
import com.equipoQ.alquilerQuinchos.Entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen,String> {
   
}
