package org.pooc2025.controller;

import org.pooc2025.dao.CursoDAO;
import org.pooc2025.model.Curso;
import java.util.List;

public class CursoController {

    private final CursoDAO cursoDAO = new CursoDAO();

    public boolean insertarCurso(String nombre, String descripcion, String estado) {
        return cursoDAO.insertar(new Curso(nombre, descripcion, estado));
    }

    public List<Curso> listarCursos() {
        return cursoDAO.listarTodos();
    }

    public boolean actualizarCurso(int id, String nombre, String descripcion, String estado) {
        return cursoDAO.actualizar(new Curso(id, nombre, descripcion, estado));
    }

    public boolean eliminarCurso(int id) {
        return cursoDAO.eliminar(id);
    }

    public Curso buscarCurso(int id) {
        return cursoDAO.buscarPorId(id);
    }
}
