package com.panaderia.domains;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "incidencias")
@JsonPropertyOrder({"id", "mail", "titulo", "clasificacion", "descripcion", "estado"})
public class Incidencia {
    @Id
    private String id;
    private String mail;
    private String titulo;
    private String clasificion;
    private String descripcion;
    private String estado;

    public Incidencia() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClasificion() {
        return clasificion;
    }

    public void setClasificion(String clasificion) {
        this.clasificion = clasificion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
