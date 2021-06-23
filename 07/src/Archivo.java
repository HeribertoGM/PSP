/**
 * @description
 *  Clase Archivo donde se maneja la apertura, lectura y cierre del archivo,
 *  tambien tiene la tarea de agregar los datos obtenidos al registro
 *  de la clase Calculus.
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 10/05/2021
 */


package lab07; 

//.b=93

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

//.i
public class Archivo {
    private Gauss gauss; 
    private FileReader file;
    private BufferedReader reader;
    private int errorFlag;
    private double wk;
    private double xk;
    private double yk;
    
    /**
    * @function Counstructor de la clase
    * @param null
    * @return null
    */
    //.i
    public Archivo(){
        gauss = new Gauss(); 
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
    * se agregan los datos de la linea al registro de gauss
    * @param null
    * @return null
    */
    //.i
    public void parse(){
        if(errorFlag != 0){
            return;
        };
        
        try {
            String line;
            line = reader.readLine();

            if(line == null){
                raiseError(2);
            } else {
                String[] data = line.split(",");
                wk = (Double.parseDouble(data[0]));
                xk = (Double.parseDouble(data[1]));
                yk = (Double.parseDouble(data[2]));
                line = reader.readLine();
            }
            
            while(line != null){
                
                String[] data = line.split(",");
                
                if(data.length != 4){
                    raiseError(3);
                    break;
                }

                LinkedList<Double> row = new LinkedList();
                row.add(Double.parseDouble(data[0])); //.m
                row.add(Double.parseDouble(data[1])); //.m
                row.add(Double.parseDouble(data[2]));
                row.add(Double.parseDouble(data[3]));
                
                
                
                gauss.addRow(row); //.m
                
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        } catch (NumberFormatException e) {
            raiseError(3);
        }
    }
    
    /**
    * @function retorna un diccionario con los resultados
    * @param null
    * @return result
    */
    public Hashtable getRes(){ 
        Hashtable result = gauss.Perform();
        
        result.put("N", gauss.getN());
        
        result.put("wk", wk);
        result.put("xk", xk);
        result.put("yk", yk);
        
        double first = (double) result.get("b0");
        double second = (wk * (double) result.get("b1"));
        double third = (xk * (double) result.get("b2"));
        double fourth = (yk * (double) result.get("b3"));
        
        double zk = first+second+third+fourth;
        result.put("zk", zk);
        
        return result; 
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
    * @function retorna el valor de la flag de error
    * @param null
    * @return errorFlag
    */
    public int getError(){
        return errorFlag;
    }

    //.d=40
}