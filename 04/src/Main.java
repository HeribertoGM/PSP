/**
 * @description
 *  Clase Main desde aqui se corre el programa, se pide la entrada
 *  y se despliega la salida
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 22/03/2021
 */

package lab04;

//.b=34
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
                    //.d=1

                    System.out.println("Teclea en pantalla valores para:");
                    
                    System.out.print("-> x (real no negativo): "); //.m
                    double x = Double.parseDouble(input.next()); //.m
                    if(x < 0){
                        System.out.println("-------------------------------------");
                        System.out.println("Error: Numero ingresado para x\ndebe de ser mayor o igual a 0");
                        return;
                    }

                    System.out.print("-> dof (entero mayor a 0): ");
                    double dof = Double.parseDouble(input.next());
                    if(dof <= 0 || Math.floor(dof) != dof){
                        System.out.println("-------------------------------------");
                        System.out.println("Error: Numero ingresado para dof\ndebe de ser un entero mayor a 0");
                        return;
                    }
		
                    Simpson simp = new Simpson(x, dof);

                    //.d=5
                    System.out.println("-------------------------------------");
                    System.out.println("  x = " + String.format("%.5f", x)); //.m
                    System.out.println("dof = "+ String.format("%.0f", dof)); //.m
                    System.out.println("  p = "+ String.format("%.5f", simp.procedure())); //.m
                    //.d=14
                } catch (Exception e){
                    System.out.println("-------------------------------------");
                    System.out.println("Error: Dato ingresado debe de ser numerico");
                }
	}
}