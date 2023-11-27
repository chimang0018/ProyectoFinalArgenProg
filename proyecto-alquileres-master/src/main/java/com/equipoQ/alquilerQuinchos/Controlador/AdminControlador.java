package com.equipoQ.alquilerQuinchos.Controlador;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/admin")
public class AdminControlador {
@GetMapping("/dashboard")
public String panelAdministrativo(){
   return "panel.html"; 
}


}
