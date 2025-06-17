package org.pooc2025.view;


import org.pooc2025.controller.GrupoCursoController;
import org.pooc2025.model.GrupoCurso;

import java.util.List;
import java.util.Scanner;

public class GrupoCursoView {

    static GrupoCursoController controller = new GrupoCursoController();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n--- MENÚ GRUPO CURSO ---");
            System.out.println("1. Asignar grupo a curso");
            System.out.println("2. Ver asignaciones");
            System.out.println("3. Ver grupos disponibles");
            System.out.println("4. Ver cursos disponibles");
            System.out.println("0. Salir");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> asignar();
                case 2 -> listar();
                case 3 -> verGrupos();
                case 4 -> verCursos();
            }
        } while (opcion != 0);
    }

    public static void asignar() {
        System.out.print("ID del grupo: ");
        int grupoId = sc.nextInt(); sc.nextLine();

        System.out.print("ID del curso: ");
        int cursoId = sc.nextInt(); sc.nextLine();

        if (controller.asignar(grupoId, cursoId)) {
            System.out.println("✅ Grupo asignado al curso.");
        } else {
            System.out.println("❌ Error al asignar.");
        }
    }

    public static void listar() {
        System.out.println("\n📋 Asignaciones:");
        List<String> asignaciones = controller.listarAsignacionesConDetalle();
        if (asignaciones.isEmpty()) {
            System.out.println("⚠️ No hay asignaciones.");
        } else {
            asignaciones.forEach(System.out::println);
        }
    }

    public static void verGrupos() {
        System.out.println("\n📋 Grupos disponibles:");
        List<String> grupos = controller.listarGrupos();
        if (grupos.isEmpty()) {
            System.out.println("⚠️ No hay grupos.");
        } else {
            grupos.forEach(System.out::println);
        }
    }

    public static void verCursos() {
        System.out.println("\n📋 Cursos disponibles:");
        List<String> cursos = controller.listarCursos();
        if (cursos.isEmpty()) {
            System.out.println("⚠️ No hay cursos.");
        } else {
            cursos.forEach(System.out::println);
        }
    }
}
