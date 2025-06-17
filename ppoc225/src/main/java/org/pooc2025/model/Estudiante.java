package org.pooc2025.model;

public class Estudiante {

    private int id;
    private String nombre;
    private String identificacion;
    private String email;
    private String fechaNacimiento;
    private String estado;

    public Estudiante(String nombre, String identificacion, String email, String fechaNacimiento, String estado) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public Estudiante(int id, String nombre, String identificacion, String email, String fechaNacimiento, String estado) {
        this(nombre, identificacion, email, fechaNacimiento, estado);
        this.id = id;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
