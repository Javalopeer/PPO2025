package org.pooc2025.dao;

import org.pooc2025.misc.Conexion;
import org.pooc2025.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public boolean insertar(Curso curso) {
        String sql = "INSERT INTO curso (nombre, descripcion, estado) VALUES (?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setString(3, curso.getEstado());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar curso: " + e.getMessage());
            return false;
        }
    }

    public List<Curso> listarTodos() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso";

        try (Connection con = Conexion.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Curso c = new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("estado")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar cursos: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Curso curso) {
        String sql = "UPDATE curso SET nombre=?, descripcion=?, estado=? WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setString(3, curso.getEstado());
            stmt.setInt(4, curso.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar curso: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM curso WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar curso: " + e.getMessage());
            return false;
        }
    }

    public Curso buscarPorId(int id) {
        String sql = "SELECT * FROM curso WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar curso: " + e.getMessage());
        }
        return null;
    }
}
