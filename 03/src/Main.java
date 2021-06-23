/**
 * @description
 *  Clase Main desde aqui se corre el programa, se pide la entrada
 *  y se despliega la salida
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 15/03/2021
 */

package lab03;

//.b=40
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
				//.d=5
				System.out.println("N  = "+ arch.getN());
				System.out.println("xk = "+ arch.getXk());
				System.out.println("r  = "+ String.format("%.5f", arch.getR()));
				System.out.println("r2 = "+ String.format("%.5f", arch.getR2()));
				System.out.println("b0 = "+ String.format("%.5f", arch.getB0()));
				System.out.println("b1 = "+ String.format("%.5f", arch.getB1()));
				System.out.println("yk = "+ String.format("%.5f", arch.getYk()));
				break;
			case 1:
				System.out.println("-------------------------------------");
				System.out.println("Error: Archivo ingresado no fue encontrado"); //.m
				break;
			case 2:
				System.out.println("-------------------------------------");
				System.out.println("Error: Archivo ingresado esta vacio"); //.m
				break;
			case 3:
				System.out.println("-------------------------------------");
				System.out.println("Error: Archivo ingresado no tiene el formato correcto"); //.m
				break;
			//.d=8
		}
	}
}