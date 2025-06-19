package org.pooc2025.dao;

import org.pooc2025.misc.Conexion;
import org.pooc2025.model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    public boolean insertar(Estudiante e) {

        String sql = "INSERT INTO gerardo_estudiante (nombre, identificacion, email, fecha_nacimiento, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, e.getNombre());
            stmt.setString(2, e.getIdentificacion());
            stmt.setString(3, e.getEmail());
            stmt.setString(4, e.getFechaNacimiento());
            stmt.setString(5, e.getEstado());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("❌ Error al insertar estudiante: " + ex.getMessage());
            return false;
        }
    }

    public List<Estudiante> listarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM gerardo_estudiante";

        try (Connection con = Conexion.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estudiante e = new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("email"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("estado")
                );
                lista.add(e);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar estudiantes: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Estudiante e) {
        String sql = "UPDATE gerardo_estudiante SET nombre=?, identificacion=?, email=?, fecha_nacimiento=?, estado=? WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, e.getNombre());
            stmt.setString(2, e.getIdentificacion());
            stmt.setString(3, e.getEmail());
            stmt.setString(4, e.getFechaNacimiento());
            stmt.setString(5, e.getEstado());
            stmt.setInt(6, e.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("❌ Error al actualizar estudiante: " + ex.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM gerardo_estudiante WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("❌ Error al eliminar estudiante: " + ex.getMessage());
            return false;
        }
    }

    public Estudiante buscarPorId(int id) {
        String sql = "SELECT * FROM gerardo_estudiante WHERE id=?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("email"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("estado")
                );
            }
        } catch (SQLException ex) {
            System.out.println("❌ Error al buscar estudiante: " + ex.getMessage());
        }
        return null;
    }
}
