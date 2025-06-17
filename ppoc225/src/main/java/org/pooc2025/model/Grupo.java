package org.pooc2025.model;

public class Grupo {

    private int id;
    private String nombre;
    private String descripcion;
    private String estado;

    public Grupo(String descripcion, String estado, String nombre, int id) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.nombre = nombre;
        this.id = id;
    }

    public Grupo(String descripcion, String estado, String nombre) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.id = 0;
        this.nombre = nombre;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
