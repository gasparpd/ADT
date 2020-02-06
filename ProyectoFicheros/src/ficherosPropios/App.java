package ficherosPropios;

import java.util.Scanner;

public class App {
    private static Scanner input;
    private static FicheroRegTexto fichReg;

    public static void main(String[] args) {
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

        System.out.println("----------------MENU----------------\n" +
                "1. Establecer fichero.\n" +
                "2. Mostrar número de registros.\n" +
                "3. Imprimir los registros.\n" +
                "4. Agregar registro.\n" +
                "5. Borrar registro.\n" +
                "6. Borrar fichero de datos.\n" +
                "7. Buscar registro.\n" +
                "0. Salir.\n" +
                "------------------------------------");
        accion = input.nextInt();

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

    private static void buscarRegistro() {
        //Pedimos que rellene los datos de un empleado
        Empleado emp = pedirEmpleado();

        int posicion = fichReg.buscarRegistro(emp);

        if (posicion == -1){
            System.out.println("El empleado no ha sudo encontrado.");
        } else {
            System.out.println("El empleado está en la posición: " +posicion);
        }
    }

    private static void borrarFichero() {
        System.out.println("Dame la ruta del fichero a eliminar.");
        String ruta = input.nextLine();
        boolean resul = fichReg.borrarFicheroDatos(ruta);

        if (resul){
            System.out.println("El fichero ha sido eliminado.");
        }
        else {
            System.out.println("El fichero no ha podido ser eliminado.");
        }
    }

    private static void borrarRegistro() {
        System.out.println("Dame el apellido del empleado a eliminar.");
        String apell = input.nextLine();

        boolean resultado = fichReg.borrarRegistro(apell);
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
        //Pedimos la ruta del fichero
        System.out.println("Introduce la ruta del fichero.");
        String ruta = input.nextLine();
        //Preguntamos si quiere sobreescribir
        char sobr;
        do {
            System.out.println("¿Quieres sobreescribir (S) el fichero o añadir a continuación (A)?");
            sobr = input.nextLine().charAt(0);
        }while (sobr != 'S' && sobr != 's' && sobr != 'A' && sobr != 'a');
        boolean append = true;

        if (sobr == 'S' || sobr == 's'){
            append = false;
        }
        //Pedimos que rellene los datos de un empleado
        Empleado empleado = pedirEmpleado();

        boolean bool = fichReg.escribirRegistro(empleado, ruta, append);

        if (bool)
            System.out.println("Empleado añadido con éxito.");
        else
            System.out.println("El empleado no pudo ser añadido.");
    }

    private static Empleado pedirEmpleado() {
        //Creamos el empleado
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

        Empleado emp = new Empleado(name, apell, edad, cargo, durC, sexo);

        return emp;
    }
}