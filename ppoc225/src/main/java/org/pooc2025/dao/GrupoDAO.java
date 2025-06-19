package org.pooc2025.dao;

import org.pooc2025.misc.Conexion;
import org.pooc2025.model.Grupo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    public List<Grupo> listarTodos() {
        List<Grupo> lista = new ArrayList<>();
        String sql = "SELECT * FROM gerardo_grupo";

        try (Connection con = Conexion.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Grupo items = new Grupo(
                        rs.getString("estado"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id")
                );
                lista.add(items);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar grupos: " + e.getMessage());
        }

        return lista;
    }

    public boolean insertar(Grupo grupo) {
        String sql = "INSERT INTO gerardo_grupo (nombre, descripcion, estado) VALUES (?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, grupo.getNombre());
            stmt.setString(2, grupo.getDescripcion());
            stmt.setString(3, grupo.getEstado());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar grupo: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Grupo grupo) {
        String sql = "UPDATE gerardo_grupo SET nombre=?, descripcion=?, estado=? WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, grupo.getNombre());
            stmt.setString(2, grupo.getDescripcion());
            stmt.setString(3, grupo.getEstado());
            stmt.setInt(4, grupo.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar grupo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM gerardo_grupo WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar grupo: " + e.getMessage());
            return false;
        }
    }

    public Grupo buscarPorId(int id) {
        String sql = "SELECT * FROM gerardo_grupo WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Grupo(
                            rs.getString("descripcion"),
                            rs.getString("estado"),
                            rs.getString("nombre"),
                            rs.getInt("id")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar grupo: " + e.getMessage());
        }
        return null;
    }
}