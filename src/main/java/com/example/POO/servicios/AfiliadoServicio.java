package com.example.POO.servicios;

import com.example.POO.entidades.Afiliado;
import com.example.POO.repositorios.AfiliadoRepositorio;
import com.example.POO.servicios.utilidades.Msj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AfiliadoServicio {

    @Autowired //se conecta automaticamente con la clase sin llamar al constructor, faiclita la sintaxis.
    AfiliadoRepositorio afiliadoRepositorio;

    //Registrar un afiliado
    public Afiliado registrarAfiliado(Afiliado afiliado) throws Exception{

        try{
          return this.afiliadoRepositorio.save(afiliado);
        }catch(Exception error){
            throw new Exception(Msj.ERROR_REGISTRO.getMensaje());
        }

    }

    //Consultar un afiliado

    public Afiliado consultarAfiliado(Integer idAfiliado) throws Exception{
        try {
            Optional<Afiliado> afiliadoBuscado = this.afiliadoRepositorio.findById(idAfiliado); // La logica nos dice que puede estar o no, asi que no puede retornar tal cual porque puede no encontrar nada, para eso es el optional.
            if(afiliadoBuscado.isPresent()){ //se encontró en la base de datos
                return afiliadoBuscado.get();
            }else {
                throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
            }
        }catch (Exception error){
            throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
        }
    }

    //Consultar afiliados (todos)

    public List<Afiliado> buscarTodosLosAfiliados() throws Exception{
        try{
          List<Afiliado> listaConsultada = this.afiliadoRepositorio.findAll();
            return listaConsultada;
        }catch (Exception error){
            throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
        }
    }

    //Modificar datos de afiliado

    public Afiliado editarAfiliado (Integer id, Afiliado afiliado) throws Exception{
        try {
           Optional<Afiliado> afiliadoBuscado = this.afiliadoRepositorio.findById(id);
            if (afiliadoBuscado.isPresent()){
             //  Afiliado afiliadoEditado = this.afiliadoRepositorio.save(afiliado);
                //  return afiliadoEditado;  ESTO CAMBIA todito el afiliado


                //aqui guardas selectivamente lo que quieras de la entidad

              Afiliado afiliadoExistente = afiliadoBuscado.get();
              afiliadoExistente.setCorreo(afiliado.getCorreo());  //puedes poner logica con condicionales para que si dejas un valor nulo lo deje como está
              afiliadoExistente.setNombre(afiliado.getNombre());
              afiliadoExistente.setCiudad(afiliado.getCiudad());

             return this.afiliadoRepositorio.save(afiliadoExistente);

            }else{
                throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
            }
        }catch (Exception error){
            throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
        }
    }

    //Borrar afiliado

    public Boolean retirarAfiliado (Integer id) throws Exception{
        try {
         Boolean afiliadoEncontrado = this.afiliadoRepositorio.existsById(id);
            if (afiliadoEncontrado){
                this.afiliadoRepositorio.deleteById(id);
                return true;
            }else{
                throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
            }
        }catch (Exception error){
            throw new Exception(Msj.ERROR_NO_ENCUENTRA.getMensaje());
        }
    }

}
