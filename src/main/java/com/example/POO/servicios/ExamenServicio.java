package com.example.POO.servicios;


import com.example.POO.entidades.Afiliado;
import com.example.POO.entidades.Examen;
import com.example.POO.repositorios.ExamenRepositorio;
import com.example.POO.servicios.utilidades.Msj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenServicio {

    @Autowired
    ExamenRepositorio ExamenRepositorio;

    public Examen registrarExamen(Examen examen) throws Exception{

        try{
            return this.ExamenRepositorio.save(examen);
        }catch(Exception error){
            throw new Exception(Msj.ERROR_REGISTRO.getMensaje());
        }

    }

    public Examen consultarExamen (Integer idExamen ) throws Exception{
        try {
            Optional<Examen> examenBuscado = this.ExamenRepositorio.findById(idExamen);
            return examenBuscado.get();
        }catch (Exception error){
            throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
        }
    }

    public List<Examen> buscarTodosLosExamenes() throws Exception{
        try{
            List<Examen> listaConsultada = this.ExamenRepositorio.findAll();
            return listaConsultada;
        }catch (Exception error){
            throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
        }
    }

    public Examen editarExamen (Integer id, Examen examen) throws Exception{
        try {
            Optional<Examen> examenBuscado = this.ExamenRepositorio.findById(id);
            if (examenBuscado.isPresent()){
                //  Afiliado afiliadoEditado = this.afiliadoRepositorio.save(afiliado);
                //  return afiliadoEditado;  ESTO CAMBIA todito el afiliado


                //aqui guardas selectivamente lo que quieras de la entidad

                Examen examenExistente = examenBuscado.get();
                //puedes poner logica con condicionales para que si dejas un valor nulo lo deje como está
                examenExistente.setNombreExamen(examen.getNombreExamen());


                return this.ExamenRepositorio.save(examenExistente);

            }else{
                throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
            }
        }catch (Exception error){
            throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
        }
    }

}
