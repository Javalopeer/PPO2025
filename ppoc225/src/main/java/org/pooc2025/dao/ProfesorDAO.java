package org.pooc2025.dao;

import org.pooc2025.misc.Conexion;
import org.pooc2025.model.Profesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {

    public boolean insertar(Profesor p) {
        String sql = "INSERT INTO gerardo_profesor (nombre, identificacion, email, departamento, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getIdentificacion());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getDepartamento());
            stmt.setString(5, p.getEstado());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar profesor: " + e.getMessage());
            return false;
        }
    }

    public List<Profesor> listarTodos() {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM gerardo_profesor";

        try (Connection con = Conexion.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Profesor p = new Profesor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("email"),
                        rs.getString("departamento"),
                        rs.getString("estado")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar profesores: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Profesor p) {
        String sql = "UPDATE gerardo_profesor SET nombre=?, identificacion=?, email=?, departamento=?, estado=? WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getIdentificacion());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getDepartamento());
            stmt.setString(5, p.getEstado());
            stmt.setInt(6, p.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar profesor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM gerardo_profesor WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar profesor: " + e.getMessage());
            return false;
        }
    }

    public Profesor buscarPorId(int id) {
        String sql = "SELECT * FROM gerardo_profesor WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Profesor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("email"),
                        rs.getString("departamento"),
                        rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar profesor: " + e.getMessage());
        }
        return null;
    }
}
