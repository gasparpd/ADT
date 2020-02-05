package ficherosPropios;

import java.util.Scanner;

public class App {
    public static void Main (String [] args) {
        Scanner input = new Scanner(System.in);
        boolean salir = false;
        int accion;
        do {
            System.out.println("----------------MENU----------------" +
                    "1. Establecer fichero." +
                    "2. Mostrar número de registros." +
                    "3. Imprimir los registros." +
                    "4. Escribir registro a continuación." +
                    "5. Escribir registro sobreescribiendo." +
                    "6. Borrar registro." +
                    "7. Borrar fichero de datos." +
                    "8. Buscar registro." +
                    "0. Salir." +
                    "------------------------------------");
            accion = input.nextInt();
            switch (accion){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("El número no corresponde con ninguna acción.");
                    break;

            }
        }while(!salir);
    }
}
