package com.equipoQ.alquilerQuinchos.Servicios;
import com.equipoQ.alquilerQuinchos.Excepciones.*;
import com.equipoQ.alquilerQuinchos.Entidades.*;
import com.equipoQ.alquilerQuinchos.Repositorios.*;
import java.util.*;
import static java.util.Collections.list;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
@Service
public class PropiedadServicio {

   @Autowired
   private PropiedadRepositorio propiedadRepositorio;

  @Transactional
public void crearPropiedad(Usuario usuario, String id, String direccion, String localidad, String provincia, Publicacion publicacion, Float precio_base, Boolean wifi, Boolean pileta, Integer capacidad) throws MiExcepcion {
   try {
      validar(usuario, id, direccion, localidad, provincia, publicacion, precio_base, wifi, pileta, capacidad);

      Propiedad propiedad = new Propiedad();

      propiedad.setId(id);
      propiedad.setDireccion(direccion);
      propiedad.setAlta(new Date());
      propiedad.setLocalidad(localidad);
      propiedad.setProvincia(provincia);
      propiedad.setUsuario(usuario);
      propiedad.setPublicacion(publicacion);
      propiedad.setPrecio_base(precio_base);
      propiedad.setWifi(wifi);
      propiedad.setPileta(pileta);
      propiedad.setCapacidad(capacidad);
      propiedadRepositorio.save(propiedad);
   } catch (MiExcepcion ex) {
      // Lanzar la excepción para que sea capturada por el bloque catch en el método registro
      throw ex;
   }

}

   public List<Propiedad> listarPropiedades() {
      List<Propiedad> propiedades = new ArrayList();
      propiedades = propiedadRepositorio.findAll();
      return propiedades;
   }

   public void modificarPropiedad(Usuario usuario, String id, String direccion, String localidad, String provincia, Publicacion publicacion, Float precio_base, Boolean wifi, Boolean pileta, Integer capacidad) throws MiExcepcion {
      Propiedad propiedad = propiedadRepositorio.findById(id).get();

      validar(usuario, id, direccion, localidad, provincia, publicacion, Float.MIN_VALUE, Boolean.TRUE, Boolean.TRUE, Integer.MAX_VALUE);
      Optional<Propiedad> respuesta = propiedadRepositorio.findById(id);

      if (respuesta.isPresent()) {
         propiedad = respuesta.get();
         propiedad.setDireccion(direccion);
         propiedad.setLocalidad(localidad);
         propiedad.setProvincia(provincia);
         propiedad.setPrecio_base(precio_base);
         propiedad.setWifi(wifi);
         propiedad.setPileta(pileta);
         propiedad.setCapacidad(capacidad);
         propiedadRepositorio.save(propiedad);
      }
   }

   public Propiedad getOne(String id) {

      return propiedadRepositorio.getOne(id);
   }

   public void eliminar(String id) {

      propiedadRepositorio.deleteById(id);

   }

   private void validar(Usuario usuario, String id, String direccion, String localidad, String provincia, Publicacion publicacion, Float precio_base, Boolean wifi, Boolean pileta, Integer capacidad) throws MiExcepcion {
      if (usuario == null) {
         throw new MiExcepcion("El propietario no pueden ser nulo");
      }
      if (id == null) {
         throw new MiExcepcion("El id no puede ser nulo");
      }
      if (direccion == null) {
         throw new MiExcepcion("La direccion no puede ser nulo");
      }

      if (localidad == null) {
         throw new MiExcepcion("La localidad no pueden ser nula");
      }

      if (provincia == null) {
         throw new MiExcepcion("La provincia no pueden ser nula");
      }
   }
}
