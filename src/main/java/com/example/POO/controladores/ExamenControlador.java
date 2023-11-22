package com.example.POO.controladores;


import com.example.POO.entidades.Afiliado;
import com.example.POO.entidades.Examen;
import com.example.POO.servicios.ExamenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sura/Examen")
public class ExamenControlador {

    @Autowired
    ExamenServicio examenServicio;

    @PostMapping
    public ResponseEntity<?> agregarExamen(@RequestBody Examen examen){
        try {
           Examen respuestaServicio = this.examenServicio.registrarExamen(examen);
           return ResponseEntity
                   .status(HttpStatus.CREATED)
                   .body(respuestaServicio);
        }catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarExamen(@PathVariable Integer id){
        try {
            Examen respuestaAfiliado = this.examenServicio.consultarExamen(id);
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
    public ResponseEntity<List<Examen>> consultarExamenes() throws Exception{
        try{
            List<Examen> listaConsultada = this.examenServicio.buscarTodosLosExamenes();
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
    public ResponseEntity<Examen> editarExamen(Integer id, @RequestBody Examen examen) throws Exception {
        try{
            Examen examenRespuesta =  this.examenServicio.editarExamen(id, examen);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(examenRespuesta);
        }catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarExamen (@PathVariable Integer id) throws Exception{
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.examenServicio.retirarExamen(id));
        }catch (Exception error){
            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

}
