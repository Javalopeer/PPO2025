package org.pooc2025.model;

public class Profesor {

    private int id;
    private String nombre;
    private String identificacion;
    private String email;
    private String departamento;
    private String estado;

    public Profesor(String nombre, String identificacion, String email, String departamento, String estado) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.departamento = departamento;
        this.estado = estado;
    }

    public Profesor(int id, String nombre, String identificacion, String email, String departamento, String estado) {
        this(nombre, identificacion, email, departamento, estado);
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
