package com.example.POO.controladores;

import com.example.POO.entidades.Afiliado;
import com.example.POO.entidades.SignoVital;
import com.example.POO.servicios.SignoVitalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sura/SignoVital")
public class SignoVitalControlador {

    @Autowired
    SignoVitalServicio signoVitalServicio;

    @PostMapping
    public ResponseEntity<?> agregarSignoVital(@RequestBody SignoVital signoVital){
        try {
            SignoVital respuestaServicio = this.signoVitalServicio.registrarSignovital(signoVital);
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
    public ResponseEntity<?> buscarControlador(@PathVariable Integer id){
        try {
            SignoVital respuestaAfiliado = this.signoVitalServicio.consultarSignoVital(id);
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
    public ResponseEntity<List<SignoVital>> consultarSignosVitales() throws Exception{
        try{
            List<SignoVital> listaConsultada = this.signoVitalServicio.buscarTodosLosSignosVitales();
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
    public ResponseEntity<SignoVital> editarSignoVital (Integer id, @RequestBody SignoVital signoVital) throws Exception {
        try{
            SignoVital signoVitalRespuesta =  this.signoVitalServicio.editarSignoVital(id, signoVital);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(signoVitalRespuesta);
        }catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSignoVital (@PathVariable Integer id) throws Exception{
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.signoVitalServicio.retirarSignoVital(id));
        }catch (Exception error){
            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

}
