/**
 * @description
 *  Clase Calculus, en esta clase se guardan todos los datos
 *  que hay en el archivo y desde aqui tambien se hacen todos
 *  los calculos
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 26/04/2021
 */

package lab06;

//.b=72

import java.util.LinkedList;
import java.lang.Math;

//.i
public class Calculus {
    LinkedList<Double> x;
    LinkedList<Double> y;
    double Xk;
    double N;
    
    /**
    * @function Counstructor de la clase
    * @param null
    * @return null
    */
    //.i
    public Calculus(){
        x = new LinkedList();
        y = new LinkedList();
        N = 0;
    }
    
    /**
    * @function Agrega pares de numeros a las linkedlist
    * @param tx, ty
    * @return null
    */
    //.i
    public void add(double tx, double ty){
        x.add(tx);
        y.add(ty);
        N++;
    }
    
    /**
    * @function settea el valor de Xk
    * @param x
    * @return null
    */
    public void setXk(double x){
        Xk = x;
    }
    
    /**
    * @function retorna la suma de todas las x
    * @param null
    * @return sum
    */
    //.i
    private double getSumX(){
        double sum = 0;
        
        for (double i : x){
            sum += i;
        }
        
        return sum;
    }
    
    /**
    * @function retorna la suma de todas las y
    * @param null
    * @return sum
    */
    //.i
    private double getSumY(){
        double sum = 0;
        
        for (double i : y){
            sum += i;
        }
        
        return sum;
    }
    
    /**
    * @function retorna la suma de todas las x multiplicadas
    *           por todas las y
    * @param null
    * @return sum
    */
    //.i
    private double getSumXY(){
        double sum = 0;
        
        for (int i=0; i < x.size(); i++){
            sum += x.get(i) * y.get(i);
        }
        
        return sum;
    }
    
    /**
    * @function retorna la suma de todas las x al cuadrado
    * @param null
    * @return sum
    */
    //.i
    private double getSumX2(){
        double sum = 0;
        
        for (double i : x){
            sum += Math.pow(i, 2);
        }
        
        return sum;
    }
    
    /**
    * @function retorna la suma de todas las y al cuadrado
    * @param null
    * @return sum
    */
    //.i
    private double getSumY2(){
        double sum = 0;
        
        for (double i : y){
            sum += Math.pow(i, 2);
        }
        
        return sum;
    }
    
    /**
    * @function retorna el promedio de todas las x
    * @param null
    * @return sumX/N
    */
    //.i
    public double getAvgX(){ //.m
        return getSumX()/N;
    }
    
    /**
    * @function retorna el promedio de todas las y
    * @param null
    * @return sumY/N
    */
    //.i
    private double getAvgY(){
        return getSumY()/N;
    }
    
    /**
    * @function retorna la cantidad de pares que hay
    * @param null
    * @return N
    */
    public double getN(){
        return N;
    }
    
    /**
    * @function retorna el valor de Xk
    * @param null
    * @return Xk
    */
    public double getXk(){
        return Xk;
    }
    
    /**
    * @function calcula y retorna el valor de r
    * @param null
    * @return r
    */
    //.i
    public double getR(){
        double ul, ur, dl, dr;
        double sumx = getSumX();
        double sumy = getSumY();
        
        ul = N * getSumXY();
        ur = sumx * sumy;
        dl = (N * getSumX2()) - Math.pow(sumx, 2);
        dr = (N * getSumY2()) - Math.pow(sumy, 2);
        
        double r = (ul - ur)/Math.sqrt(dl * dr);
        
        return r;
    }
    
    /**
    * @function calcula y retorna el valor de r^2
    * @param null
    * @return r^2
    */
    //.i
    public double getR2(){
        return Math.pow(getR(), 2);
    }
    
    /**
    * @function calcula y retorna el valor de B1
    * @param null
    * @return B1
    */
    //.i
    public double getB1(){
        double up, down;
        
        up = getSumXY() - (N * getAvgX() * getAvgY());
        down = getSumX2() - (N * Math.pow(getAvgX(), 2));
        
        return up / down;
    }
    
    /**
    * @function calcula y retorna el valor de B0
    * @param null
    * @return B0
    */
    //.i
    public double getB0(){
        return getAvgY()-(getB1()*getAvgX());
    }
    
    /**
    * @function calcula y retorna el valor de Yk
    * @param null
    * @return B0 + B1*Xk
    */
    //.i
    public double getYk(){
        return getB0() + (getB1() * Xk);
    }
    
    /**
    * @function retorna la linked list x
    * @param null
    * @return x
    */
    public LinkedList<Double> getXLL(){
        return this.x;
    }
    
    /**
    * @function retorna la linked list x
    * @param null
    * @return y
    */
    public LinkedList<Double> getYLL(){
        return this.y;
    }
}
