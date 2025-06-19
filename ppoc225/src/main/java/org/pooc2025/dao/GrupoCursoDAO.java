package org.pooc2025.dao;

import org.pooc2025.misc.Conexion;
import org.pooc2025.model.GrupoCurso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoCursoDAO {

    public boolean insertar(GrupoCurso relacion) {
        String sql = "INSERT INTO gerardo_grupo_curso (grupo_id, curso_id) VALUES (?, ?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, relacion.getGrupoId());
            stmt.setInt(2, relacion.getCursoId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al asignar grupo a curso: " + e.getMessage());
            return false;
        }
    }

    public List<String> listarAsignacionesConDetalle() {
        List<String> lista = new ArrayList<>();
        String sql = """
        SELECT gc.id, g.id AS grupo_id, g.nombre AS grupo, c.id AS curso_id, c.nombre AS curso
        FROM gerardo_grupo_curso gc
        JOIN gerardo_grupo g ON gc.grupo_id = g.id
        JOIN gerardo_curso c ON gc.curso_id = c.id
    """;
        try (Connection con = Conexion.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String mensaje = "Grupo \"" + rs.getString("grupo") + "\" (ID: " + rs.getInt("grupo_id") + ") fue asignado al curso \"" +
                        rs.getString("curso") + "\" (ID: " + rs.getInt("curso_id") + ")";
                lista.add(mensaje);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar asignaciones: " + e.getMessage());
        }
        return lista;
    }

    public List<String> listarGruposConNombres() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM gerardo_grupo";
        try (Connection con = Conexion.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add("ID: " + rs.getInt("id") + " | Nombre: " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar grupos: " + e.getMessage());
        }
        return lista;
    }

    public List<String> listarCursosConNombres() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM gerardo_curso";
        try (Connection con = Conexion.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add("ID: " + rs.getInt("id") + " | Nombre: " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar cursos: " + e.getMessage());
        }
        return lista;
    }
    public boolean yaExisteAsignacion(int grupoId, int cursoId) {
        String sql = "SELECT COUNT(*) FROM gerardo_grupo_curso WHERE grupo_id = ? AND curso_id = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, grupoId);
            stmt.setInt(2, cursoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al verificar duplicados: " + e.getMessage());
        }
        return false;
    }

}

