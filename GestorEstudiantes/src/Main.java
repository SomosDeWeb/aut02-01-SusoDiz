import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorEstudiantes manager = new GestorEstudiantes();
        String opcion;

        manager.limpiarConsola(); // limpiar consola

        do {

            System.out.append("===== Gestor de Estudiantes =====\n")
                .append("1. Añadir estudiante\n")
                .append("2. Listar estudiantes\n")
                .append("3. Buscar por nombre\n")
                .append("4. Calcular nota media general\n")
                .append("5. Mostrar mejor estudiante\n")
                .append("6. Salir\n")
                .append("Seleccione opción: ");
            opcion = sc.nextLine();

            manager.limpiarConsola();

            switch (opcion) {
                case "1":
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine().trim();
                    if (nombre.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.\n");
                        break;
                    }
                    System.out.print("Edad (>=0): ");
                    String edadStr = sc.nextLine().trim();
                    int edad;
                    try {
                        edad = Integer.parseInt(edadStr);
                        if (edad < 0) {
                            System.out.println("Edad no puede ser negativa.\n");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Edad inválida.\n");
                        break;
                    }
                    System.out.print("Nota media (0-10): ");
                    String notaStr = sc.nextLine().trim();
                    double notaMedia;
                    try {
                        notaMedia = Double.parseDouble(notaStr);
                        if (notaMedia < 0 || notaMedia > 10) {
                            System.out.println("Nota fuera de rango.\n");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Nota inválida.\n");
                        break;
                    }
                    System.out.print("¿Está matriculado? (true/false): ");
                    String matStr = sc.nextLine().trim().toLowerCase();
                    Boolean matriculado = null;
                    if (matStr.isEmpty()) {
                        System.out.println("El valor no puede estar vacío.\n");
                        break;
                    } else if (matStr.equals("true")) {
                        matriculado = true;
                    } else if (matStr.equals("false")) {
                        matriculado = false;
                    } else {
                        System.out.println("Solo se admite true o false.\n");
                        break;
                    }
                    manager.anadirEstudiante(nombre, edad, notaMedia, matriculado);
                    manager.limpiarConsola(); // limpiar consola
                    System.out.println("Estudiante añadido.\n");
                    break;

                case "2":
                    System.out.println("Lista de estudiantes:");
                    List<Estudiante> lista = manager.listarEstudiantes();
                    for (Estudiante e : lista) {
                        System.out.append(e.getNombre())
                        .append(", ")
                        .append(String.valueOf(e.getEdad()))
                        .append(", Nota media: ")
                        .append(String.valueOf(e.getNotaMedia()))
                        .append(", Matriculado: ")
                        .append(String.valueOf(e.isMatriculado()))
                        .append("\n");
                    }
                    System.out.println();
                    break;

                case "3":
                    System.out.print("Nombre a buscar: ");
                    String buscar = sc.nextLine();
                    List<Estudiante> encontrados = manager.buscarEstudiantesPorNombre(buscar);
                    if (!encontrados.isEmpty()) {
                        for (Estudiante e : encontrados) {
                            System.out.append(e.getNombre())
                                .append(", ")
                                .append(String.valueOf(e.getEdad()))
                                .append(", Nota media: ")
                                .append(String.valueOf(e.getNotaMedia()))
                                .append(", Matriculado: ")
                                .append(String.valueOf(e.isMatriculado()))
                                .append("\n\n");
                        }
                    } else {
                        System.out.println("No encontrado.\n");
                    }
                    break;

                case "4":
                    double media = manager.calcularMediaGeneral();
                    System.out.println("Media general: " + media + "\n");
                    break;

                case "5":
                    Estudiante mejor = manager.estudianteMejorNota();
                    if (mejor != null) {
                        System.out.append("Mejor estudiante: ")
                            .append(mejor.getNombre())
                            .append(" con nota media ")
                            .append(String.valueOf(mejor.getNotaMedia()))
                            .append("\n\n");
                    } else {
                        System.out.println("No hay estudiantes.\n");
                    }
                    break;

                case "6":
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.\n");
            }

        } while (!opcion.equals("6"));

        sc.close(); // falla aleatoriamente si no se cierra el scanner 
    }
}

class Estudiante {
    private String nombre;
    private int edad;
    private double notaMedia;
    private boolean matriculado;

    public Estudiante(String nombre, int edad, double notaMedia, boolean matriculado) {
        this.nombre = nombre;
        this.edad = edad;
        this.notaMedia = notaMedia;
        this.matriculado = matriculado;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public double getNotaMedia() { return notaMedia; }
    public boolean isMatriculado() { return matriculado; }
}

class GestorEstudiantes {

    private List<Estudiante> listaEstudiantes = new ArrayList<>();
    public void anadirEstudiante(String nombre, int edad, double notaMedia, boolean matriculado) {
        listaEstudiantes.add(new Estudiante(nombre, edad, notaMedia, matriculado));
    }

    public List<Estudiante> listarEstudiantes() {
        return listaEstudiantes;
    }

    public List<Estudiante> buscarEstudiantesPorNombre(String nombre) {
        List<Estudiante> encontrados = new ArrayList<>();
        for (Estudiante e : listaEstudiantes) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                encontrados.add(e);
            }
        }
        return encontrados;
    }

    public double calcularMediaGeneral() {
        if (listaEstudiantes.isEmpty()) return 0;
        double suma = 0;
        for (Estudiante e : listaEstudiantes) {
            suma += e.getNotaMedia();
        }
        return suma / listaEstudiantes.size();
    }

    public Estudiante estudianteMejorNota() {
        if (listaEstudiantes.isEmpty()) return null;
        Estudiante mejor = listaEstudiantes.get(0);
        for (Estudiante e : listaEstudiantes) {
            if (e.getNotaMedia() > mejor.getNotaMedia()) {
                mejor = e;
            }
        }
        return mejor;
    }

    public void limpiarConsola() { // Fuente: https://es.stackoverflow.com/a/529860
        System.out.print("\033[H\033[2J"); 
        System.out.flush(); 
        /*
         * Parece no funcionar en todas las consolas, deben soportar los códigos ANSI.
         * En IntelliJ IDEA no funciona.
         * 
        */
    }
}
