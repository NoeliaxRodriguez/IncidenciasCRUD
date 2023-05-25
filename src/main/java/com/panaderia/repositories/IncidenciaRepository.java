package com.panaderia.repositories;

import com.panaderia.domains.Incidencia;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Locale;

public interface IncidenciaRepository extends CrudRepository <Incidencia, String>{
    List<Incidencia> findAll();
    List<Incidencia> findIncidenciaByMail(String mail);
}


