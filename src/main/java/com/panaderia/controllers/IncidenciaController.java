package com.panaderia.controllers;

import com.panaderia.domains.Incidencia;
import com.panaderia.repositories.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class IncidenciaController {
    // CRUD de incidencias
    @Autowired
    IncidenciaRepository incidenciaRepository;

    // GET - Extraer info de bbdd (listar todos las incidencias)
    @RequestMapping(value = "/incidencias", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Incidencia>> getAllProducts() {
        List<Incidencia> incidenciaList = null;
        try {
            incidenciaList = incidenciaRepository.findAll();
            if (incidenciaList == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(incidenciaList, HttpStatus.OK);
    }

    // GET (by id) - Extraer info de bbdd (listar una incidencia por su id)
    @RequestMapping(value = "/incidencia/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Incidencia> getIncidenciaId(@PathVariable String id) {
        Incidencia fromDbInc = null;
        try {
            fromDbInc = incidenciaRepository.findById(id).orElse(null);
            if(fromDbInc == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(fromDbInc, HttpStatus.OK);
    }

    // GET (by mail) - Extraer info de bbdd (listar incidencias por su mail)
    @RequestMapping(value = "/incidencia/mail/{mail}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Incidencia>> getIncidenciaMail(@PathVariable String mail) {
        List<Incidencia> incidenciaList = null;
        try {
            incidenciaList = incidenciaRepository.findIncidenciaByMail(mail);
            if (incidenciaList == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(incidenciaList, HttpStatus.OK);
    }

    //POST
    @PostMapping(value = "/incidencias", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Incidencia> insertUser(@RequestBody Incidencia i) {
        try {
            if(i.equals(null)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            incidenciaRepository.save(i);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    // PUT - Actualización de info en BBDD
    @RequestMapping(value = "/incidencia/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Incidencia> updateUser(@PathVariable String id, @RequestBody Incidencia toUpdate) {
        Incidencia actInc = null;
        try {
            if((id == null) || (toUpdate == null)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Buscamos por nombre de usuario y en caso de no encontrar nada, obtenemos null
            Incidencia fromDBInc = incidenciaRepository.findById(id).orElse(null);

            if (fromDBInc != null) {
                incidenciaRepository.save(toUpdate);
                actInc = toUpdate;
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(actInc, HttpStatus.OK);
    }

    // DELETE - Eliminación de info en BBDD
    @RequestMapping(value ="/incidencias/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Incidencia> deleteOne(@PathVariable String id) {
        Incidencia toDeleteInc = null;
        try {
            if (id == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Buscamos por nombre de usuario y en caso de no encontrar nada, obtenemos null
            toDeleteInc = incidenciaRepository.findById(id).orElse(null);
            incidenciaRepository.delete(toDeleteInc);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(toDeleteInc, HttpStatus.OK);
    }
}
