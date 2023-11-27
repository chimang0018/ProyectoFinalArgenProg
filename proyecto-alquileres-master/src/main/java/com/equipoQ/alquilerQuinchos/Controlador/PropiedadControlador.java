package com.equipoQ.alquilerQuinchos.Controlador;
import com.equipoQ.alquilerQuinchos.Entidades.*;
import com.equipoQ.alquilerQuinchos.Enumeraciones.*;
import com.equipoQ.alquilerQuinchos.Excepciones.*;
import com.equipoQ.alquilerQuinchos.Servicios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/propiedad")
public class PropiedadControlador {

   @Autowired
   private PropiedadServicio propiedadServicio;
   @Autowired
   private UsuarioServicio usuarioServicio;

   @GetMapping("/registrar")
   public String registrar(ModelMap modelo, @SessionAttribute("usuariosession") Usuario usuario) {
      // Obtener el usuario actual de la sesión
      // Comprobar si el usuario tiene el rol de AGENTE
      if (usuario.getRol() == Rol.PROPIETARIO) {
         modelo.put("usuario", usuario);
         return "propiedad_form.html";
      } else {
         // Si no, mostrar un mensaje de error y redirigir al inicio
         modelo.put("error", "Solo los agentes pueden registrar propiedades");
         return "redirect:/";
      }
   }

   @PostMapping("/registro")
   public String registro(@RequestParam String usuarioId, @RequestParam(required = false) String id, @RequestParam String direccion, @RequestParam String localidad,
           @RequestParam String provincia, @RequestParam Float precio_base, @RequestParam Boolean wifi, @RequestParam Boolean pileta, @RequestParam Integer capacidad, ModelMap modelo) {
      try {
         // Busca al propietario por su ID de usuario
         Usuario usuario = usuarioServicio.getOne(usuarioId);

         // Crea la publicación
         Publicacion publicacion = new Publicacion();
         // Aquí puedes establecer los atributos de la publicación según sea necesario

         // Crea la propiedad
         propiedadServicio.crearPropiedad(usuario, id, direccion, localidad, provincia, publicacion, precio_base, wifi, pileta, capacidad);

         modelo.put("Exito", "La propiedad fue cargada correctamente");
      } catch (MiExcepcion ex) {
         modelo.put("error", ex.getMessage());
         return "propiedad_form.html";
      }
      return "index";
   }

   @GetMapping("/listar")
   public String listar(ModelMap modelo) {
      List<Propiedad> propiedades = propiedadServicio.listarPropiedades();
      modelo.addAttribute("propiedades", propiedades);
      return "propiedad_list";
   }

   @GetMapping("/modificar/{id}")
   public String modificar(@PathVariable String id, ModelMap modelo) {
      // Obtener el usuario actual de la sesión
      Usuario usuario = (Usuario) modelo.get("usuariosession");
      // Obtener la propiedad por su ID
      Propiedad propiedad = propiedadServicio.getOne(id);
      // Comprobar si el usuario tiene el rol de AGENTE o es el propietario de la propiedad
      if (usuario.getRol() == Rol.PROPIETARIO || usuario.getId().equals(propiedad.getUsuario().getId())) {
         // Si sí, mostrar el formulario de modificación
         modelo.put("propiedad", propiedad);
         return "propiedad_modify.html";
      } else {
         // Si no, mostrar un mensaje de error y redirigir al inicio
         modelo.put("error", "No tienes permiso para modificar esta propiedad");
         return "redirect:/";
      }
   }

   @PostMapping("/modificar/{id}")
   public String modificar(@RequestParam Usuario usuario, String id, String direccion, String localidad, String provincia, Publicacion publicacion, ModelMap modelo) {

      try {
         propiedadServicio.modificarPropiedad(usuario, id, direccion, localidad, provincia, publicacion, Float.MIN_VALUE, Boolean.TRUE, Boolean.TRUE, Integer.MAX_VALUE);

         return "redirect:../listar";

      } catch (MiExcepcion e) {

         modelo.put("error", e.getMessage());

         return "propiedad_modify.html";
      }
   }

   @GetMapping("/eliminar/{id}")
   public String eliminar(@PathVariable String id, ModelMap modelo) {
      // Obtener el usuario actual de la sesión
      Usuario usuario = (Usuario) modelo.get("usuariosession");
      // Obtener la propiedad por su ID
      Propiedad propiedad = propiedadServicio.getOne(id);
      // Comprobar si el usuario tiene el rol de PROPIETARIO o es el propietario de la propiedad
      if (usuario.getRol() == Rol.PROPIETARIO || usuario.getId().equals(propiedad.getUsuario().getId())) {
         // Si sí, eliminar la propiedad
         propiedadServicio.eliminar(id);
         return "redirect:../listar";
      } else {
         // Si no, mostrar un mensaje de error y redirigir al inicio
         modelo.put("error", "No tienes permiso para eliminar esta propiedad");
         return "redirect:/";
      }
   }
}
