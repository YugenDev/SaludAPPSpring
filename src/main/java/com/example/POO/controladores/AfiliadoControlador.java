package com.example.POO.controladores;

import com.example.POO.entidades.Afiliado;
import com.example.POO.servicios.AfiliadoServicio;
import com.example.POO.servicios.utilidades.Msj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sura/afiliados")
public class AfiliadoControlador {
    @Autowired
    AfiliadoServicio afiliadoServicio;

    //Agregar afiliado

    @PostMapping
    public ResponseEntity<?> agregarAfiliado(@RequestBody Afiliado afiliado){
        try {
         Afiliado respuestaServicio = this.afiliadoServicio.registrarAfiliado(afiliado);
         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(respuestaServicio);
        }catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }

    //Buscar afiliado

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAfiliado(@PathVariable Integer id){
        try {
            Afiliado respuestaAfiliado = this.afiliadoServicio.consultarAfiliado(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(respuestaAfiliado);
        }catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }
    @GetMapping
    public ResponseEntity<List<Afiliado>> consultarAfiliados() throws Exception{
        try{
         List<Afiliado> listaConsultada = this.afiliadoServicio.buscarTodosLosAfiliados();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(listaConsultada);
        }catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Afiliado> editarAfiliado (Integer id, @RequestBody Afiliado afiliado) throws Exception {
        try{
          Afiliado afiliadoRespuesta =  this.afiliadoServicio.editarAfiliado(id, afiliado);
           return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(afiliadoRespuesta);
        }catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

    }

    public ResponseEntity<?> eliminarAfiliado (@PathVariable Integer id) throws Exception{
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.afiliadoServicio.retirarAfiliado(id));
        }catch (Exception error){
            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

}
