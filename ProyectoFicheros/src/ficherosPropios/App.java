package ficherosPropios;

import java.util.Scanner;

public class App {
    private static Scanner input;
    private static FicheroRegTexto fichReg;

    public static void Main(String[] args) {
        input = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            salir = menu();
        }
        System.out.println("Fin del programa.");
    }

    private static boolean menu() {
        int accion;
        boolean salir = false;

        System.out.println("----------------MENU----------------" +
                "1. Establecer fichero." +
                "2. Mostrar número de registros." +
                "3. Imprimir los registros." +
                "4. Agregar registro." +
                "5. Borrar registro." +
                "6. Borrar fichero de datos." +
                "7. Buscar registro." +
                "0. Salir." +
                "------------------------------------");
        accion = input.nextInt();
        input.next();
        switch (accion) {
            case 1:
                establecerFichero();
                break;
            case 2:
                numeroRegistros();
                break;
            case 3:
                imprimirRegistros();
                break;
            case 4:
                anadirRegistro();
                break;
            case 5:
                borrarRegistro();
                break;
            case 6:
                borrarFichero();
                break;
            case 7:
                buscarRegistro();
                break;
            case 0:
                System.out.println("Saliendo........");
                salir = true;
                break;
            default:
                System.out.println("El número no corresponde con ninguna acción.");
                break;
        }

        return salir;
    }

    private static void establecerFichero() {
        System.out.println("Pasa la ruta del fichero. (Ej. ./prueba.txt)");
        String rutaF = input.nextLine();
        fichReg = new FicheroRegTexto(rutaF);
        System.out.println("Fichero creado.");
    }

    private static void numeroRegistros() {
        System.out.println("El número de registros es: " +fichReg.numeroDeRegistros());
    }

    private static void imprimirRegistros() {
        fichReg.mostrarRegistros();
    }

    private static void anadirRegistro() {
        System.out.println("Vamos a crear un empleado. Dime su nombre.");
        String name = input.nextLine();
        System.out.println("Escribe sus apellidos.");
        String apell = input.nextLine();
        System.out.println("Dime su edad.");
        int edad = input.nextInt();
        input.next();
        System.out.println("Su cargo.");
        String cargo = input.nextLine();
        System.out.println("La duración de su contrato.");
        int durC = input.nextInt();
        input.next();
        System.out.println("Su sexo (F/M).");
        char sexo = input.next().charAt(0);

        Empleado empleado = new Empleado(name, apell, edad, cargo, durC, sexo);
        boolean bool = fichReg.escribirRegistro(empleado);

        if (bool)
            System.out.println("Empleado añadido con éxito.");
        else
            System.out.println("El empleado no pudo ser añadido.");


        /*
        Se puede implementar la agregación de un empleado con el mismo método
         */
    }
}