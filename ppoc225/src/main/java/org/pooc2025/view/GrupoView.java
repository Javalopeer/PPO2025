package org.pooc2025.view;

import org.pooc2025.controller.GrupoController;
import org.pooc2025.model.Grupo;

import java.util.List;
import java.util.Scanner;

public class GrupoView {

    static GrupoController controller = new GrupoController();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n--- MENÚ GRUPO ---");
            System.out.println("1. Listar grupos");
            System.out.println("2. Insertar grupo");
            System.out.println("3. Actualizar grupo");
            System.out.println("4. Eliminar grupo");
            System.out.println("5. Buscar grupo por ID");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> listarGrupos();
                case 2 -> insertarGrupo();
                case 3 -> actualizarGrupo();
                case 4 -> eliminarGrupo();
                case 5 -> buscarGrupo();
            }

        } while (opcion != 0);
    }

    public static void listarGrupos() {
        List<Grupo> lista = controller.listarGrupos();
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay grupos registrados.");
        } else {
            for (Grupo grupo : lista) {
                System.out.println("\nID: " + grupo.getId() +
                        "\nNombre: " + grupo.getNombre() +
                        "\nDescripción: " + grupo.getDescripcion() +
                        "\nEstado: " + grupo.getEstado());
            }
        }
    }

    public static void insertarGrupo() {
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
            System.out.println("❌ Estado inválido. Solo se permite 'activo' o 'inactivo'.");
            return;
        }

        if (controller.insertarGrupo(nombre, descripcion, estado)) {
            System.out.println("✅ Grupo insertado correctamente.");
        } else {
            System.out.println("❌ Error al insertar el grupo.");
        }
    }

    public static void actualizarGrupo() {
        System.out.print("ID del grupo a actualizar: ");
        int id = sc.nextInt(); sc.nextLine();

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("❌ El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Nueva descripción: ");
        String descripcion = sc.nextLine().trim();
        if (descripcion.isEmpty()) {
            System.out.println("❌ La descripción no puede estar vacía.");
            return;
        }

        System.out.print("Nuevo estado (activo/inactivo): ");
        String estado = sc.nextLine().trim().toLowerCase();
        if (!(estado.equals("activo") || estado.equals("inactivo"))) {
            System.out.println("❌ Estado inválido.");
            return;
        }

        if (controller.actualizarGrupo(id, nombre, descripcion, estado)) {
            System.out.println("✅ Grupo actualizado correctamente.");
        } else {
            System.out.println("❌ No se pudo actualizar el grupo.");
        }
    }

    public static void eliminarGrupo() {
        System.out.print("ID del grupo a eliminar: ");
        int id = sc.nextInt(); sc.nextLine();

        if (controller.eliminarGrupo(id)) {
            System.out.println("✅ Grupo eliminado correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar el grupo.");
        }
    }

    public static void buscarGrupo() {
        System.out.print("ID del grupo a buscar: ");
        int id = sc.nextInt(); sc.nextLine();

        Grupo grupo = controller.buscarGrupo(id);
        if (grupo != null) {
            System.out.println("\n📄 Grupo encontrado:");
            System.out.println("ID: " + grupo.getId());
            System.out.println("Nombre: " + grupo.getNombre());
            System.out.println("Descripción: " + grupo.getDescripcion());
            System.out.println("Estado: " + grupo.getEstado());
        } else {
            System.out.println("⚠️ Grupo no encontrado.");
        }
    }

}
