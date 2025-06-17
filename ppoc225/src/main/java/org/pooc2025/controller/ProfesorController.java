package org.pooc2025.controller;

import org.pooc2025.dao.ProfesorDAO;
import org.pooc2025.model.Profesor;

import java.util.List;

public class ProfesorController {

    private final ProfesorDAO dao = new ProfesorDAO();

    public boolean insertar(String nombre, String identificacion, String email, String departamento, String estado) {
        return dao.insertar(new Profesor(nombre, identificacion, email, departamento, estado));
    }

    public boolean actualizar(int id, String nombre, String identificacion, String email, String departamento, String estado) {
        return dao.actualizar(new Profesor(id, nombre, identificacion, email, departamento, estado));
    }

    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }

    public List<Profesor> listar() {
        return dao.listarTodos();
    }

    public Profesor buscar(int id) {
        return dao.buscarPorId(id);
    }
}
