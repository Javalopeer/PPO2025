package org.pooc2025.view;

import org.pooc2025.controller.CursoController;
import org.pooc2025.model.Curso;

import java.util.List;
import java.util.Scanner;

public class CursoView {

    static CursoController controller = new CursoController();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n--- MENÚ CURSO ---");
            System.out.println("1. Listar cursos");
            System.out.println("2. Insertar curso");
            System.out.println("3. Actualizar curso");
            System.out.println("4. Eliminar curso");
            System.out.println("5. Buscar curso por ID");
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
        List<Curso> lista = controller.listarCursos();
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay cursos registrados.");
        } else {
            for (Curso c : lista) {
                System.out.println("\nID: " + c.getId()
                        + "\nNombre: " + c.getNombre()
                        + "\nDescripción: " + c.getDescripcion()
                        + "\nEstado: " + c.getEstado());
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

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine().trim();
        if (descripcion.isEmpty()) {
            System.out.println("❌ La descripción no puede estar vacía.");
            return;
        }

        System.out.print("Estado (activo/inactivo): ");
        String estado = sc.nextLine().trim().toLowerCase();
        if (!(estado.equals("activo") || estado.equals("inactivo"))) {
            System.out.println("❌ Estado inválido.");
            return;
        }

        if (controller.insertarCurso(nombre, descripcion, estado)) {
            System.out.println("✅ Curso insertado.");
        } else {
            System.out.println("❌ Error al insertar curso.");
        }
    }

    public static void actualizar() {
        System.out.print("ID a actualizar: ");
        int id = sc.nextInt(); sc.nextLine();

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Nueva descripción: ");
        String descripcion = sc.nextLine().trim();

        System.out.print("Nuevo estado: ");
        String estado = sc.nextLine().trim().toLowerCase();
        if (!(estado.equals("activo") || estado.equals("inactivo"))) {
            System.out.println("❌ Estado inválido.");
            return;
        }

        if (controller.actualizarCurso(id, nombre, descripcion, estado)) {
            System.out.println("✅ Curso actualizado.");
        } else {
            System.out.println("❌ Error al actualizar curso.");
        }
    }

    public static void eliminar() {
        System.out.print("ID a eliminar: ");
        int id = sc.nextInt(); sc.nextLine();
        if (controller.eliminarCurso(id)) {
            System.out.println("✅ Curso eliminado.");
        } else {
            System.out.println("❌ No se pudo eliminar.");
        }
    }

    public static void buscar() {
        System.out.print("ID a buscar: ");
        int id = sc.nextInt(); sc.nextLine();
        Curso c = controller.buscarCurso(id);
        if (c != null) {
            System.out.println("📄 Curso encontrado:");
            System.out.println("ID: " + c.getId());
            System.out.println("Nombre: " + c.getNombre());
            System.out.println("Descripción: " + c.getDescripcion());
            System.out.println("Estado: " + c.getEstado());
        } else {
            System.out.println("⚠️ Curso no encontrado.");
        }
    }
}
