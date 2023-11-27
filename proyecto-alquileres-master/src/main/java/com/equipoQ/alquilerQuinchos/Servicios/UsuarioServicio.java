package com.equipoQ.alquilerQuinchos.Servicios;
import com.equipoQ.alquilerQuinchos.Entidades.*;
import com.equipoQ.alquilerQuinchos.Enumeraciones.*;
import com.equipoQ.alquilerQuinchos.Excepciones.*;
import com.equipoQ.alquilerQuinchos.Repositorios.*;
import java.util.*;
import javax.servlet.http.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.context.request.*;
import org.springframework.web.multipart.*;
@Service
public class UsuarioServicio implements UserDetailsService {

   @Autowired
   private UsuarioRepositorio usuarioRepositorio;

   @Autowired
   private ImagenServicio imagenServicio;

   @Autowired
   private PropiedadServicio propiedadServicio;

   @Transactional
   public Usuario registrar(MultipartFile archivo, String nombre, String email, String password, String password2, Rol rol) throws MiExcepcion {
      validar(nombre, email, password, password2);
      Usuario usuario = new Usuario();
      usuario.setNombre(nombre);
      usuario.setEmail(email);
      usuario.setPassword(new BCryptPasswordEncoder().encode(password));
      usuario.setRol(rol);
      Imagen imagen = imagenServicio.guardar(archivo);
      usuario.setImagen(imagen);
      usuarioRepositorio.save(usuario);
      return usuario;
   }

   @Transactional
   public void actualizar(MultipartFile archivo, String idUsuario, String nombre, String email, String password, String password2, Rol rol) throws MiExcepcion {
      // He añadido el parámetro rol para que puedas cambiar el rol del usuario al actualizarlo
      validar(nombre, email, password, password);
      Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);
      if (respuesta.isPresent()) {
         Usuario usuario = respuesta.get();
         usuario.setNombre(nombre);
         usuario.setEmail(email);
         usuario.setPassword(new BCryptPasswordEncoder().encode(password));
         usuario.setRol(rol); // He cambiado esto para que use el rol que se pasa como parámetro
         String idImagen = null;
         if (usuario.getImagen() != null) {
            idImagen = usuario.getImagen().getId();
            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            usuario.setImagen(imagen);
            usuarioRepositorio.save(usuario);
         }
      }
   }

   private void validar(String nombre, String email, String password, String password2) throws MiExcepcion {
      if (nombre.isEmpty() || nombre == null) {
         throw new MiExcepcion("El nombre no puede estar vacio");
      }
      if (email.isEmpty() || email == null) {
         throw new MiExcepcion("El email no puede estar vacio");
      }
      if (password.isEmpty() || password == null || password.length() <= 5) {
         throw new MiExcepcion("La contraseña debe tener mas de 5 digitos");
      }
      if (!password.equals(password2)) {
         throw new MiExcepcion("Las contraseñas ingresadas deben ser iguales");
      }
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
      if (usuario != null) {
         List<GrantedAuthority> permisos = new ArrayList<>();
         GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
         permisos.add(p);
         ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
         HttpSession session = attr.getRequest().getSession();
         session.setAttribute("usuariosession", usuario);
         return new User(usuario.getEmail(), usuario.getPassword(), permisos);
      }
      return null;
   }

   public Usuario getOne(String id) {
      return usuarioRepositorio.getOne(id);
   }
}
