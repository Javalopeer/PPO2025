package org.pooc2025.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

//    private static final String URL = "jdbc:mysql://srv863.hstgr.io:3306/u484426513_ppoc225";
//    private static final String USER = "u484426513_ppoc225";
//    private static final String PASSWORD = "jXc7w:|7Gy";

    private static final String URL = "jdbc:mysql://localhost:3306/ppoc";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Connection con = obtenerConexion();

        if (con != null) {
            System.out.println("Conectado a la base de datos.");

            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al conectar: " + e.getMessage());
            }
        }
    }
}
