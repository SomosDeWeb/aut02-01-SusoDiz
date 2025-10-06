import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String verde="\033[32m"; // Fuente: https://dryant.com/programacion/como-poner-el-texto-de-color-en-la-consola-o-terminal-ejemplo-en-java/
        String reset="\u001B[0m";

        StringBuilder gestorEstudiantes = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        String titulo = verde + "===== Gestor de Estudiantes =====" + reset;
            gestorEstudiantes.append(titulo);
            gestorEstudiantes.append("\n");
            gestorEstudiantes.append("1. Añadir estudiante");
            gestorEstudiantes.append("\n");
            gestorEstudiantes.append("2. Listar estudiantes");
            gestorEstudiantes.append("\n");
            gestorEstudiantes.append("3. Buscar estudiantes");
            gestorEstudiantes.append("\n");
            gestorEstudiantes.append("4. Calcular media de estudiantes");
            gestorEstudiantes.append("\n");
            gestorEstudiantes.append("5. Mostrar estudiante con mejor nota");
            gestorEstudiantes.append("\n");
            gestorEstudiantes.append("6. Salir");
            gestorEstudiantes.append("\n");

        String opcion, nombreEstudiante;
        int notaDSW, notaDEW, notaDPL;

        do {

            System.out.println(gestorEstudiantes);
            System.out.print("Opción: ");

            opcion = sc.nextLine();

            switch (opcion)
                {
                case "1":
                    System.out.print("Introduce los datos del estudiante. \nNombre: ");
                    nombreEstudiante = sc.next();
                    System.out.print("Nota de DSW: ");
                    notaDSW = sc.nextInt();
                    System.out.print("Nota de DEW: ");
                    notaDEW = sc.nextInt();
                    System.out.print("Nota de DPL: ");
                    notaDPL = sc.nextInt();

                    anadirEstudiante(nombreEstudiante, notaDSW, notaDEW, notaDPL);

                case "2":
                    buscarEstudiantes();

                case "3":
                    listarEstudiantes();

                case "4":
                    calcularMedia();

                case "5":estudianteMejorNota();
                }

        } while (!opcion.equals("6"));

    }

    public static void anadirEstudiante(String nombre, int notaDSW, int notaDEW, int notaDPL){

    }

    public static void listarEstudiantes(){}

    public static void buscarEstudiantes(){}

    public static void calcularMedia(){}

    public static void estudianteMejorNota(){}


}