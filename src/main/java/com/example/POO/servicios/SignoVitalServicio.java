package com.example.POO.servicios;

import com.example.POO.entidades.Afiliado;
import com.example.POO.entidades.Examen;
import com.example.POO.entidades.SignoVital;
import com.example.POO.repositorios.SignoVitalRepositorio;
import com.example.POO.servicios.utilidades.Msj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignoVitalServicio {

    @Autowired
    SignoVitalRepositorio signoVitalRepositorio;

    public SignoVital registrarSignovital(SignoVital signoVital) throws Exception{
        try {
            return this.signoVitalRepositorio.save(signoVital);
        }catch (Exception error){
            throw new Exception(Msj.ERROR_REGISTRO.getMensaje());
        }
    }

    public SignoVital consultarSignoVital (Integer idSignoVital) throws Exception{
        try {
            Optional<SignoVital> signoVitalBuscado = this.signoVitalRepositorio.findById(idSignoVital);
            return signoVitalBuscado.get();
        }catch (Exception error){
                throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
        }
    }

    public List<SignoVital> buscarTodosLosSignosVitales() throws Exception{
        try{
            List<SignoVital> listaConsultada = this.signoVitalRepositorio.findAll();
            return listaConsultada;
        }catch (Exception error){
            throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
        }
    }

    public SignoVital editarSignoVital (Integer id, SignoVital signoVital) throws Exception{
        try {
            Optional<SignoVital> signoVitalBuscado = this.signoVitalRepositorio.findById(id);
            if (signoVitalBuscado.isPresent()){
                //  Afiliado afiliadoEditado = this.afiliadoRepositorio.save(afiliado);
                //  return afiliadoEditado;  ESTO CAMBIA todito el afiliado


                //aqui guardas selectivamente lo que quieras de la entidad

                SignoVital signoVitalExistente = signoVitalBuscado.get();
                //puedes poner logica con condicionales para que si dejas un valor nulo lo deje como está
                signoVitalExistente.setNombre(signoVital.getNombre());


                return this.signoVitalRepositorio.save(signoVitalExistente);

            }else{
                throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
            }
        }catch (Exception error){
            throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
        }
    }

    public Boolean retirarSignoVital (Integer id) throws Exception{
        try {
            Boolean signoVitalEncontrado = this.signoVitalRepositorio.existsById(id);
            if (signoVitalEncontrado){
                this.signoVitalRepositorio.deleteById(id);
                return true;
            }else{
                throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
            }
        }catch (Exception error){
            throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
        }
    }

}
