package org.pooc2025.controller;

import org.pooc2025.dao.GrupoDAO;
import org.pooc2025.model.Grupo;

import java.util.List;

public class GrupoController {

    private final GrupoDAO grupoDAO = new GrupoDAO();

    public List<Grupo> listarGrupos() {
        return grupoDAO.listarTodos();
    }

    public boolean insertarGrupo(String nombre, String descripcion, String estado) {
        Grupo grupo = new Grupo(nombre, descripcion, estado);
        return grupoDAO.insertar(grupo);
    }

    public boolean actualizarGrupo(int id, String nombre, String descripcion, String estado) {
        Grupo grupo = new Grupo(descripcion, estado, nombre, id);
        return grupoDAO.actualizar(grupo);
    }

    public boolean eliminarGrupo(int id) {
        return grupoDAO.eliminar(id);
    }

    public Grupo buscarGrupo(int id) {
        return grupoDAO.buscarPorId(id);
    }
}
