/**
 * @description
 *  Clase Main desde aqui se corre el programa, se pide la entrada
 *  y se despliega la salida
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 05/04/2021
 */

package lab05;

//.b=27

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
                try{
                    Scanner input = new Scanner(System.in);

                    System.out.println("Teclea en pantalla valores para:");
                    
                    System.out.print("-> p (real entre 0 y 0.5): "); //.m
                    double p = Double.parseDouble(input.next()); //.m
                    if(p < 0 || p > 0.5){ //.m
                        System.out.println("-------------------------------------");
                        System.out.println("Error: Numero ingresado para p\ndebe de ser un numero real entre 0 y 0.5"); //.m
                        return;
                    }

                    System.out.print("-> dof (entero mayor a 0): ");
                    double dof = Double.parseDouble(input.next());
                    if(dof <= 0 || Math.floor(dof) != dof){
                        System.out.println("-------------------------------------");
                        System.out.println("Error: Numero ingresado para dof\ndebe de ser un entero mayor a 0");
                        return;
                    }
		
                    Simpson simp = new Simpson(p, dof); //.m

                    System.out.println("-------------------------------------");
                    System.out.println("  p = " + String.format("%.5f", p)); //.m
                    System.out.println("dof = "+ String.format("%.0f", dof));
                    System.out.println("  x = "+ String.format("%.5f", simp.findX())); //.m
                } catch (Exception e){
                    System.out.println("-------------------------------------");
                    System.out.println("Error: Dato ingresado debe de ser numerico");
                }
	}
}