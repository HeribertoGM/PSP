/**
 * @description
 *  Clase Archivo donde se maneja la apertura, lectura y cierre del archivo,
 *  tambien tiene la tarea de agregar los datos obtenidos al registro
 *  de la clase Calculus.
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 26/04/03/2021
 */


package lab06; 

//.b=67

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

//.i
public class Archivo {
    private Calculus calc; 
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
        calc = new Calculus(); 
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
            String line;
            line = reader.readLine();

            if(line == null){
                raiseError(2);
            } else {
                calc.setXk(Double.parseDouble(line));
                line = reader.readLine();
            }
            
            while(line != null){
                
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
    public double getN(){ 
        return calc.getN(); 
    }
    
    /**
    * @function retorna la Xk
    * @param null
    * @return calc.getXk()
    */
    public double getXk(){ 
        return calc.getXk(); 
    }
    
    /**
    * @function retorna la R
    * @param null
    * @return calc.getR()
    */
    public double getR(){ 
        return calc.getR(); 
    }
    
    /**
    * @function retorna la R^2
    * @param null
    * @return calc.getR2()
    */
    public double getR2(){ 
        return calc.getR2(); 
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
    
    /**
    * @function retorna el valor de la significacia
    * @param null
    * @return 1 - (2 * simp.procedure())
    */
    //.i
    public double getSig(){
        double x = (Math.abs(calc.getR())*Math.sqrt(calc.getN()-2))/Math.sqrt(1-calc.getR2());
        
        double dof = calc.getN() - 2;
        
        Simpson4 simp = new Simpson4(x, dof);
        
        return 1 - (2 * simp.procedure());
    }
    
    /**
    * @function retorna el valor del rango
    * @param null
    * @return t*sd*r
    */
    //.i
    public double getRan(){
        double dof = calc.getN() - 2;
        Simpson5 simp = new Simpson5(0.35, dof);
        double t = simp.findX();
        
        LinkedList<Double> x = calc.getXLL();
        LinkedList<Double> y = calc.getYLL();
        
        double sdSum = 0;
        for(int i=0; i<calc.N; i++){
            sdSum += Math.pow((y.get(i)-calc.getB0()-(calc.getB1()*x.get(i))), 2);
        }
        double sd = Math.sqrt((1/(calc.getN()-2))*sdSum);
        
        double rSum = 0;
        for(int i=0; i<calc.N; i++){
            rSum += Math.pow((x.get(i)-calc.getAvgX()), 2);
        }
       double right = Math.pow((calc.getXk()-calc.getAvgX()),2)/rSum;
       double r = Math.sqrt(1+(1/calc.getN())+right);
       
       return t*sd*r;
    }
    
    /**
    * @function retorna el valor de LS
    * @param null
    * @return calc.getYk() + getRan()
    */
    //.i
    public double getLS(){
        return calc.getYk() + getRan();
    }
    
    /**
    * @function retorna el valor de LI
    * @param null
    * @return calc.getYk() - getRan()
    */
    //.i
    public double getLI(){
        double li = calc.getYk() - getRan();
        return (li > 0 ? li: 0);
    }
}
