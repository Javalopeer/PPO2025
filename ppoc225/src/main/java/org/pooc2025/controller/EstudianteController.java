package org.pooc2025.controller;

import org.pooc2025.dao.EstudianteDAO;
import org.pooc2025.model.Estudiante;
import java.util.List;

public class EstudianteController {

    private final EstudianteDAO estudianteDAO = new EstudianteDAO();

    public boolean insertarEstudiante(String nombre, String identificacion, String email, String fechaNacimiento, String estado) {
        Estudiante e = new Estudiante(nombre, identificacion, email, fechaNacimiento, estado);
        return estudianteDAO.insertar(e);
    }

    public List<Estudiante> listarEstudiantes() {
        return estudianteDAO.listarTodos();
    }

    public boolean actualizarEstudiante(int id, String nombre, String identificacion, String email, String fechaNacimiento, String estado) {
        Estudiante e = new Estudiante(id, nombre, identificacion, email, fechaNacimiento, estado);
        return estudianteDAO.actualizar(e);
    }

    public boolean eliminarEstudiante(int id) {
        return estudianteDAO.eliminar(id);
    }

    public Estudiante buscarEstudiante(int id) {
        return estudianteDAO.buscarPorId(id);
    }
}
