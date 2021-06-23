/**
 * @description
 *  Clase Main desde aqui se corre el programa, se pide la entrada
 *  y se despliega la salida
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 26/04/2021
 */

package lab06;

//.b=33
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
				System.out.println("N  = "+ String.format("%.0f", arch.getN()));
				System.out.println("xk = "+ String.format("%.5f", arch.getXk()));
				System.out.println("r  = "+ String.format("%.5f", arch.getR()));
				System.out.println("r2 = "+ String.format("%.5f", arch.getR2()));
				System.out.println("b0 = "+ String.format("%.5f", arch.getB0()));
				System.out.println("b1 = "+ String.format("%.5f", arch.getB1()));
				System.out.println("yk = "+ String.format("%.5f", arch.getYk()));
                                System.out.println("Sig= "+ String.format("%.10f", arch.getSig()));
                                System.out.println("Ran= "+ String.format("%.5f", arch.getRan()));
                		System.out.println("LS = "+ String.format("%.5f", arch.getLS()));
                                System.out.println("LI = "+ String.format("%.5f", arch.getLI()));
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