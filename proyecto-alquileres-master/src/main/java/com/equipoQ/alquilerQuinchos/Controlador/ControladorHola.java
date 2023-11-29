package com.equipoQ.alquilerQuinchos.Controlador;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.equipoQ.alquilerQuinchos.Entidades.Usuario;
import com.equipoQ.alquilerQuinchos.Enumeraciones.Rol;
import com.equipoQ.alquilerQuinchos.Excepciones.MiExcepcion;
import com.equipoQ.alquilerQuinchos.Servicios.UsuarioServicio;

// Usar @RequestMapping a nivel de clase para indicar la ruta base del controlador

@Controller
@RequestMapping("/")
public class ControladorHola {

   @Autowired
   private UsuarioServicio usuarioServicio;

   @GetMapping("/")
   public String index(HttpSession session) {
      Usuario logueado = (Usuario) session.getAttribute("usuariosession");
      return "index.html";
   }

   // Usar @GetMapping con rutas relativas a la base
   @GetMapping("/registrar")
   public String registrar() {
      return "registro.html";
   }
  
   // Usar @ModelAttribute para vincular los atributos del modelo con los parámetros del método
@PostMapping("/registro")
public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, @RequestParam String password2, @RequestParam Rol rol, ModelMap modelo, MultipartFile archivo, HttpSession session) throws MiExcepcion {

    try {

        Usuario usuario = usuarioServicio.registrar(archivo, nombre, email, password, password2, rol);
        session.setAttribute("usuariosession", usuario);

        return "redirect:/inicio";

    } catch (MiExcepcion e) {

        modelo.put("error", e.getMessage());
        modelo.put("nombre", nombre);
        modelo.put("email", email);
        modelo.put("password", password);
        modelo.put("password2", password2);

        return "registro.html";
    }

}


   @GetMapping("/login")
   public String login(@RequestParam(required = false) String error, ModelMap modelo) {

      if (error != null) {

         modelo.put("error", "Usuario o Contraseña incorrecto.");
      }

      return "login.html";
   }

   @GetMapping("/inicio")
    public String inicio(HttpSession session) {
      Usuario logueado = (Usuario) session.getAttribute("usuariosession");
      // Usar @ModelAttribute para obtener el usuario de la sesión
    if (logueado.getRol().toString().equals("ADMIN")) {
         return "redirect:/admin/dashboard";
      }
      return "inicio.html";
   }

   @PreAuthorize("hasAnyRole('ROLE_CLIENTE', 'ROLE_ADMIN','ROLE_PROPIETARIO')")
   @GetMapping("/perfil")
   public String perfil(ModelMap modelo, HttpSession session) {
      Usuario usuario = (Usuario) session.getAttribute("usuariosession");
      modelo.put("usuario", usuario);
      return "usuario_modify.html";
   }

   @PreAuthorize("hasAnyRole('ROLE_CLIENTE', 'ROLE_PROPIETARIO', 'ROLE_ADMIN')")
   @PostMapping("/perfil/{id}")
   public String actualizar(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo, MultipartFile archivo) throws MiExcepcion {
      {

         try {
            usuarioServicio.actualizar(archivo, password2, nombre, email, password, password2, Rol.ADMIN);
            modelo.put("exito", "Usuario actualizado correctamente!");

            return "inicio.html";

        } catch (MiExcepcion ex) {

         modelo.put("error", ex.getMessage());
         modelo.put("nombre", nombre);
         modelo.put("email", email);

         return "usuario_modify.html";
         }

      }

   }
}
