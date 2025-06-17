package org.pooc2025.view;

import org.pooc2025.controller.ProfesorController;
import org.pooc2025.model.Profesor;

import java.util.List;
import java.util.Scanner;

public class ProfesorView {

    static ProfesorController controller = new ProfesorController();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PROFESOR ---");
            System.out.println("1. Listar profesores");
            System.out.println("2. Insertar profesor");
            System.out.println("3. Actualizar profesor");
            System.out.println("4. Eliminar profesor");
            System.out.println("5. Buscar profesor por ID");
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
        List<Profesor> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay profesores.");
        } else {
            for (Profesor p : lista) {
                System.out.println("\nID: " + p.getId() +
                        "\nNombre: " + p.getNombre() +
                        "\nIdentificación: " + p.getIdentificacion() +
                        "\nEmail: " + p.getEmail() +
                        "\nDepartamento: " + p.getDepartamento() +
                        "\nEstado: " + p.getEstado());
            }
        }
    }

    public static void insertar() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) return;

        System.out.print("Identificación: ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) return;

        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        if (!esEmailValido(email)) {
            System.out.println("❌ Email inválido. Debe tener formato nombre@dominio.com");
            return;
        }

        System.out.print("Departamento: ");
        String dep = sc.nextLine().trim();
        if (dep.isEmpty()) return;

        System.out.print("Estado (activo/inactivo): ");
        String estado = sc.nextLine().trim().toLowerCase();
        if (!(estado.equals("activo") || estado.equals("inactivo"))) {
            System.out.println("❌ Estado inválido.");
            return;
        }

        if (controller.insertar(nombre, id, email, dep, estado)) {
            System.out.println("✅ Profesor insertado.");
        } else {
            System.out.println("❌ Error al insertar.");
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

        System.out.print("Nuevo departamento: ");
        String dep = sc.nextLine().trim();

        System.out.print("Nuevo estado: ");
        String estado = sc.nextLine().trim().toLowerCase();

        if (controller.actualizar(id, nombre, identificacion, email, dep, estado)) {
            System.out.println("✅ Profesor actualizado.");
        } else {
            System.out.println("❌ Error al actualizar.");
        }
    }

    public static void eliminar() {
        System.out.print("ID a eliminar: ");
        int id = sc.nextInt(); sc.nextLine();

        if (controller.eliminar(id)) {
            System.out.println("✅ Profesor eliminado.");
        } else {
            System.out.println("❌ No se pudo eliminar.");
        }
    }

    public static void buscar() {
        System.out.print("ID a buscar: ");
        int id = sc.nextInt(); sc.nextLine();

        Profesor p = controller.buscar(id);
        if (p != null) {
            System.out.println("📄 Profesor encontrado:");
            System.out.println("ID: " + p.getId());
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Identificación: " + p.getIdentificacion());
            System.out.println("Email: " + p.getEmail());
            System.out.println("Departamento: " + p.getDepartamento());
            System.out.println("Estado: " + p.getEstado());
        } else {
            System.out.println("⚠️ Profesor no encontrado.");
        }
    }

    public static boolean esEmailValido(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
