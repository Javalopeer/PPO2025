package org.pooc2025.controller;

import org.pooc2025.dao.GrupoCursoDAO;
import org.pooc2025.model.GrupoCurso;

import java.util.List;

public class GrupoCursoController {

    private final GrupoCursoDAO dao = new GrupoCursoDAO();

    public List<String> listarGrupos() {
        return dao.listarGruposConNombres();
    }

    public List<String> listarCursos() {
        return dao.listarCursosConNombres();
    }

    public List<String> listarAsignacionesConDetalle() {
        return dao.listarAsignacionesConDetalle();
    }

    public boolean asignar(int grupoId, int cursoId) {
        if (dao.yaExisteAsignacion(grupoId, cursoId)) {
            System.out.println("⚠️ Ya existe esta asignación.");
            return false;
        }
        return dao.insertar(new GrupoCurso(grupoId, cursoId));
    }
}
