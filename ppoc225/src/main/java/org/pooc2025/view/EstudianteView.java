package org.pooc2025.view;

import org.pooc2025.controller.EstudianteController;
import org.pooc2025.model.Estudiante;

import java.util.List;
import java.util.Scanner;

public class EstudianteView {

    static EstudianteController controller = new EstudianteController();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n--- MENÚ ESTUDIANTE ---");
            System.out.println("1. Listar estudiantes");
            System.out.println("2. Insertar estudiante");
            System.out.println("3. Actualizar estudiante");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Buscar estudiante por ID");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> listar();
                case 2 -> insertar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 5 -> buscar();
            }

        } while (opcion != 0);
    }

    public static void listar() {
        List<Estudiante> lista = controller.listarEstudiantes();
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay estudiantes registrados.");
        } else {
            for (Estudiante e : lista) {
                System.out.println("\nID: " + e.getId()
                        + "\nNombre: " + e.getNombre()
                        + "\nIdentificación: " + e.getIdentificacion()
                        + "\nEmail: " + e.getEmail()
                        + "\nFecha nacimiento: " + e.getFechaNacimiento()
                        + "\nEstado: " + e.getEstado());
            }
        }
    }

    public static void insertar() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("❌ El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Identificación: ");
        String identificacion = sc.nextLine().trim();
        if (identificacion.isEmpty()) {
            System.out.println("❌ La identificación no puede estar vacía.");
            return;
        }

        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        if (!esEmailValido(email)) {
            System.out.println("❌ Email inválido. Debe tener formato nombre@dominio.com");
            return;
        }

        System.out.print("Fecha nacimiento (YYYY-MM-DD): ");
        String fecha = sc.nextLine().trim();

        System.out.print("Estado (activo/inactivo): ");
        String estado = sc.nextLine().trim().toLowerCase();
        if (!(estado.equals("activo") || estado.equals("inactivo"))) {
            System.out.println("❌ Estado inválido.");
            return;
        }

        if (controller.insertarEstudiante(nombre, identificacion, email, fecha, estado)) {
            System.out.println("✅ Estudiante insertado.");
        } else {
            System.out.println("❌ Error al insertar estudiante.");
        }
    }

    public static void actualizar() {
        System.out.print("ID a actualizar: ");
        int id = sc.nextInt(); sc.nextLine();

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Nueva identificación: ");
        String identificacion = sc.nextLine().trim();

        System.out.print("Nuevo email: ");
        String email = sc.nextLine().trim();
        if (!esEmailValido(email)) {
            System.out.println("❌ Email inválido. Debe tener formato nombre@dominio.com");
            return;
        }

        System.out.print("Nueva fecha nacimiento (YYYY-MM-DD): ");
        String fecha = sc.nextLine().trim();

        System.out.print("Nuevo estado: ");
        String estado = sc.nextLine().trim().toLowerCase();
        if (!(estado.equals("activo") || estado.equals("inactivo"))) {
            System.out.println("❌ Estado inválido.");
            return;
        }

        if (controller.actualizarEstudiante(id, nombre, identificacion, email, fecha, estado)) {
            System.out.println("✅ Estudiante actualizado.");
        } else {
            System.out.println("❌ Error al actualizar estudiante.");
        }
    }

    public static void eliminar() {
        System.out.print("ID a eliminar: ");
        int id = sc.nextInt(); sc.nextLine();
        if (controller.eliminarEstudiante(id)) {
            System.out.println("✅ Estudiante eliminado.");
        } else {
            System.out.println("❌ No se pudo eliminar.");
        }
    }

    public static void buscar() {
        System.out.print("ID a buscar: ");
        int id = sc.nextInt(); sc.nextLine();
        Estudiante e = controller.buscarEstudiante(id);
        if (e != null) {
            System.out.println("📄 Estudiante encontrado:");
            System.out.println("ID: " + e.getId());
            System.out.println("Nombre: " + e.getNombre());
            System.out.println("Identificación: " + e.getIdentificacion());
            System.out.println("Email: " + e.getEmail());
            System.out.println("Fecha nacimiento: " + e.getFechaNacimiento());
            System.out.println("Estado: " + e.getEstado());
        } else {
            System.out.println("⚠️ Estudiante no encontrado.");
        }
    }

    public static boolean esEmailValido(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
