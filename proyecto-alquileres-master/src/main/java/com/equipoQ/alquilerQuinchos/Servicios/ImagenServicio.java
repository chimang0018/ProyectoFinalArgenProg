package com.equipoQ.alquilerQuinchos.Servicios;
import com.equipoQ.alquilerQuinchos.Entidades.*;
import com.equipoQ.alquilerQuinchos.Excepciones.*;
import com.equipoQ.alquilerQuinchos.Repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;
@Service
public class ImagenServicio {

   @Autowired
   private ImagenRepositorio imagenRepositorio;

   public Imagen guardar(MultipartFile archivo)throws MiExcepcion{
      if (archivo != null) {
         try {
            Imagen imagen = new Imagen();
//            con getcontentype se llama al tipo de contenido
            imagen.setMime(archivo.getContentType());
            imagen.setNombre(archivo.getName());
            imagen.setContenido(archivo.getBytes());
            return imagenRepositorio.save(imagen);
         } catch (Exception e) {
            System.err.println(e.getMessage());
         }
      }
      return null;
   }
   public Imagen actualizar(MultipartFile archivo, String idImagen)throws MiExcepcion{
            if (archivo != null) {
         try {
            Imagen imagen = new Imagen();
            if (idImagen !=null){
               Optional<Imagen> respuesta= imagenRepositorio.findById(idImagen);
               if (respuesta.isPresent()){
                  imagen=respuesta.get();
               }
            }
            imagen.setMime(archivo.getContentType());
            imagen.setNombre(archivo.getName());
            imagen.setContenido(archivo.getBytes());
            return imagenRepositorio.save(imagen);
         } catch (Exception e) {
            System.err.println(e.getMessage());
         }
      }
      return null;
   }

}
