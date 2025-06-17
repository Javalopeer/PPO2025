package org.pooc2025.model;

public class GrupoCurso {

    private int id;
    private int grupoId;
    private int cursoId;

    public GrupoCurso(int grupoId, int cursoId) {
        this.grupoId = grupoId;
        this.cursoId = cursoId;
    }

    public GrupoCurso(int id, int grupoId, int cursoId) {
        this(grupoId, cursoId);
        this.id = id;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
