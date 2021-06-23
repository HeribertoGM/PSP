/**
 * @description
 *  Clase Main desde aqui se corre el programa, se pide la entrada
 *  y se despliega la salida
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 10/05/2021
 */

package lab07;

//.b=37
import java.util.Hashtable;
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
		String fileName = input.next();
		
		arch.open(fileName);
		arch.parse();
		arch.close();
                
                Hashtable result = arch.getRes();
		
		switch(arch.getError()){
			case 0:
				System.out.println("-------------------------------------");
				System.out.println("N  = "+ String.format("%.0f", result.get("N"))); //.m
				System.out.println("wk = "+ String.format("%.5f", result.get("wk"))); //.m
				System.out.println("xk = "+ String.format("%.5f", result.get("xk"))); //.m
				System.out.println("yk = "+ String.format("%.5f", result.get("yk"))); //.m
				System.out.println("-------------------------------------");
				System.out.println("b0 = "+ String.format("%.5f", result.get("b0"))); //.m
				System.out.println("b1 = "+ String.format("%.5f", result.get("b1"))); //.m
				System.out.println("b2 = "+ String.format("%.5f", result.get("b2"))); //.m
                                System.out.println("b3 = "+ String.format("%.5f", result.get("b3"))); //.m
				System.out.println("-------------------------------------");
                                System.out.println("zk = "+ String.format("%.5f", result.get("zk"))); //.m
                                //.d=2
				break;
			case 1:
				System.out.println("-------------------------------------");
				System.out.println("Error: Archivo ingresado no fue encontrado");
				break;
			case 2:
				System.out.println("-------------------------------------");
				System.out.println("Error: Archivo ingresado esta vacio");
				break;
			case 3:
				System.out.println("-------------------------------------");
				System.out.println("Error: Archivo ingresado no tiene el formato correcto"); 
				break;
		}
	}
}