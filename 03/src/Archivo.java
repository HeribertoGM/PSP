/**
 * @description
 *  Clase Archivo donde se maneja la apertura, lectura y cierre del archivo,
 *  tambien tiene la tarea de agregar los datos obtenidos al registro
 *  de la clase Calculus.
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 15/03/2021
 */

//.b=78

package lab03; //.m

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//.i
public class Archivo {
    private Calculus calc; //.m
    private FileReader file;
    private BufferedReader reader;
    private int errorFlag;
    
    /**
    * @function Counstructor de la clase
    * @param null
    * @return null
    */
    //.i
    public Archivo(){
        calc = new Calculus(); //.m
        errorFlag = 0;
    }
    
    /**
    * @function Abre el archivo y lo coloca en una variable
    * @param fileName
    * @return null
    */
    //.i
    public void open(String fileName){
        try{
            file = new FileReader(fileName);
            reader = new BufferedReader(file);
        }
        catch(IOException e){
            raiseError(1);
        }
    }
    
    /**
    * @function Cierra el archivo
    * @param null
    * @return null
    */
    //.i
    public void close(){
        if(errorFlag != 0){
            return;
        };
        
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo");
        }
    }
    
    /**
    * @function Funcion en la que se se lee linea por linea y 
    * se agregan los datos de la linea al registro de calculus
    * @param null
    * @return null
    */
    //.i
    public void parse(){
        if(errorFlag != 0){
            return;
        };
        
        try {
            //.d=1
            String line;
            line = reader.readLine();

            if(line == null){
                raiseError(2);
            } else {
                calc.setXk(Double.parseDouble(line));
                line = reader.readLine();
            }
            
            while(line != null){
                //.d=28
                
                String[] data = line.split(",");
                
                if(data.length != 2){
                    raiseError(3);
                    break;
                }

                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                
                calc.add(x, y);
                
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        } catch (NumberFormatException e) {
            raiseError(3);
        }
    }
    
    /**
    * @function Asigna valor a la flag de error para 
    * manejo de output en caso de errores
    * @param op
    * @return null
    */
    private void raiseError(int op){
        errorFlag = op;
    }
    
    /**
    * @function retorna la cantidad de pares totales
    * @param null
    * @return calc.getN()
    */
    public double getN(){ //.m
        return calc.getN(); //.m
    }
    
    /**
    * @function retorna la Xk
    * @param null
    * @return calc.getXk()
    */
    public double getXk(){ //.m
        return calc.getXk(); //.m
    }
    
    /**
    * @function retorna la R
    * @param null
    * @return calc.getR()
    */
    public double getR(){ //.m
        return calc.getR(); //.m
    }
    
    /**
    * @function retorna la R^2
    * @param null
    * @return calc.getR2()
    */
    public double getR2(){ //.m
        return calc.getR2(); //.m
    }
    
    /**
    * @function retorna la B0
    * @param null
    * @return calc.getB0()
    */
    public double getB0(){
        return calc.getB0();
    }
    
    /**
    * @function retorna la B1
    * @param null
    * @return calc.getB1()
    */
    public double getB1(){
        return calc.getB1();
    }
    
    /**
    * @function retorna la Yk
    * @param null
    * @return calc.getYk()
    */
    public double getYk(){
        return calc.getYk();
    }
    
    /**
    * @function retorna el valor de la flag de error
    * @param null
    * @return errorFlag
    */
    public int getError(){
        return errorFlag;
    }
}
