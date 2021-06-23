/**
 * @description
 *  Clase Archivo donde se maneja la apertura, lectura y cierre del archivo,
 *  tambien tiene la tarea de discernir el tipo de linea.
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 22/02/2021
 */

package proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//.i
public class Archivo {
    private Contador counter;
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
        counter = new Contador();
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
    * se identifica que tipo es.
    * @param null
    * @return null
    */
    //.i
    public void parse(){
        if(errorFlag != 0){
            return;
        };
        
        try {
            boolean OM, CM, SC;
            String line;
            line = reader.readLine();
            
            if(line == null){
                raiseError(2);
            }
            
            while(line != null){
                
                OM = line.contains("/*");
                CM = line.contains("*/");
                SC = line.contains("//");
                
                if(!line.trim().startsWith("//") && SC){
                    raiseError(3);
                    break;
                }
                if(OM && CM){
                    raiseError(4);
                    break;
                }
                
                if(OM){
                    if(counter.getMulticommentStatus()){
                        raiseError(5);
                        break;
                    }
                    else{
                        counter.setCommentStatus(true);
                        //System.out.println("multicoment open");
                    }
                }
                
                //System.out.print(line.trim());
                //System.out.print(line.trim().length()+"\n");
                if(line.trim().length() == 0){
                    counter.countLine(1);
                }
                else if(line.trim().startsWith("//") || counter.getMulticommentStatus()){
                    counter.countLine(2);
                }
                else{
                    counter.countLine(3);
                }
                
                if(CM){
                    if(counter.getMulticommentStatus()){
                        counter.setCommentStatus(false);
                        //System.out.println("multicoment close");
                    }
                    else{
                        raiseError(5);
                        break;
                    }
                }
                
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error mientras se leia el archivo");
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
    * @function retorna la cantidad de lineas en blanco
    * @param null
    * @return counter.getCountBlanck()
    */
    public int getFinalBlank(){
        return counter.getCountBlank();
    }
    
    /**
    * @function retorna la cantidad de lineas con comentarios
    * @param null
    * @return counter.getCountComment()
    */
    public int getFinalComment(){
        return counter.getCountComment();
    }
    
    /**
    * @function retorna la cantidad de lineas de codigo
    * @param null
    * @return counter.getCountCode()
    */
    public int getFinalCode(){
        return counter.getCountCode();
    }
    
    /**
    * @function retorna la cantidad de lineas totales
    * @param null
    * @return counter.getTotalCount()
    */
    public int getFinalTotal(){
        return counter.getTotalCount();
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
