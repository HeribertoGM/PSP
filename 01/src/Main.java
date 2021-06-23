/**
 * @description
 *  Clase Main desde aqui se corre el programa, se pide la entrada
 *  y se despliega la salida
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 22/02/2021
 */

package proyecto;

import java.util.Scanner;

//.i
public class Main {

    /**
    * @function funcion main
    * @param null
    * @return null
    */
    //.i
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Archivo arch = new Archivo();
        
        System.out.print("Nombre del archivo: ");
        String fileName = input.next();;
        
        arch.open(fileName);
        arch.parse();
        arch.close();
        
        switch(arch.getError()){
            case 0:
                System.out.println("-------------------------------------");
                System.out.println("Cantidad de líneas en blanco: "+arch.getFinalBlank());
                System.out.println("Cantidad de líneas con comentarios: "+arch.getFinalComment());
                System.out.println("Cantidad de líneas con código: "+arch.getFinalCode());
                System.out.println("-------------------------------------");
                System.out.println("Cantidad total de líneas: "+arch.getFinalTotal());
                break;
            case 1:
                System.out.println("-------------------------------------");
                System.out.println("Error: Archivo inexistente");
                break;
            case 2:
                System.out.println("-------------------------------------");
                System.out.println("Error: Archivo vacio");
                break;
            case 3:
                System.out.println("-------------------------------------");
                System.out.println("Error: Archivo contiene elementos \nmezclados");
                break;
            case 4:
                System.out.println("-------------------------------------");
                System.out.println("Error: Archivo contiene comentarios \nmultilinea en renglon simple");
                break;
            case 5:
                System.out.println("-------------------------------------");
                System.out.println("Error: Archivo contiene comentarios \nmultilinea con apertura o cierre doble");
                break;
        }
    }
}
