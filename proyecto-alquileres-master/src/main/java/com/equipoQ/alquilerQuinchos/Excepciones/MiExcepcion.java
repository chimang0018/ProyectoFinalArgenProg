package com.equipoQ.alquilerQuinchos.Excepciones;

public class MiExcepcion extends Exception {
//constructor que recibe mensaje llama al super y pasarle al constructor el msg recibido.
//Diferencia los errores que tengamos en logica del negocio de los errores y bugs del sistema.

   public MiExcepcion(String msg) {
      super(msg);
   }

}
