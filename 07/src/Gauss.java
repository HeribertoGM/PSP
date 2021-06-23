/**
 * @description
 *  Clase Gauss donde se hace la eliminacion de gauss y se lleva el registro
 *  de los cuadruplos.
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 10/05/2021
 */

package lab07;

//.i

import java.util.Hashtable;
import java.util.LinkedList;

public class Gauss {
    private LinkedList<LinkedList<Double>> register;
    private LinkedList<LinkedList<Double>> matrix;
    
    /**
    * @function Counstructor de la clase
    * @param null
    * @return null
    */
    //.i
    public Gauss(){
        register = new LinkedList();
        matrix = new LinkedList();
    }
    
    /**
    * @function se encarga de crear la matriz a partir 
    * de los cuadruplos del registro
    * @param null
    * @return null
    */
    //.i
    public void makeMatrix(){
        double sumW=0, sumX=0, sumY=0, sumZ=0;
        double sumW2=0, sumWX=0, sumWY=0, sumWZ=0;
        double sumX2=0, sumXY=0, sumXZ=0;
        double sumY2=0, sumYZ=0;
        
        double w, x, y, z;
        for(LinkedList<Double> i : register){
            w = i.get(0);
            x = i.get(1);
            y = i.get(2);
            z = i.get(3);
            
            sumW += w;
            sumX += x;
            sumY += y;
            sumZ += z;
            
            sumW2 += w*w;
            sumX2 += x*x;
            sumY2 += y*y;
            
            sumWX += w*x;
            sumWY += w*y;
            sumWZ += w*z;
            
            sumXY += x*y;
            sumXZ += x*z;
            
            sumYZ += y*z;
        }
        
        LinkedList<Double> first = new LinkedList();
        first.add(getN());
        first.add(sumW);
        first.add(sumX);
        first.add(sumY);
        first.add(sumZ);
        LinkedList<Double> second = new LinkedList();
        second.add(sumW);
        second.add(sumW2);
        second.add(sumWX);
        second.add(sumWY);
        second.add(sumWZ);
        LinkedList<Double> third = new LinkedList();
        third.add(sumX);
        third.add(sumWX);
        third.add(sumX2);
        third.add(sumXY);
        third.add(sumXZ);
        LinkedList<Double> fourth = new LinkedList();
        fourth.add(sumY);
        fourth.add(sumWY);
        fourth.add(sumXY);
        fourth.add(sumY2);
        fourth.add(sumYZ);
        
        matrix.add(first);
        matrix.add(second);
        matrix.add(third);
        matrix.add(fourth);
    }
    
    /**
    * @function hace la eliminacion de gauss y retorna las betas
    * @param null
    * @return res
    */
    //.i
    public Hashtable Perform() {
        makeMatrix();
        
        for(int i=0; i < 4; i++){
            double pivot = matrix.get(i).get(i);
            for(int j=0; j < 5; j++){
                matrix.get(i).set(j, (matrix.get(i).get(j) / pivot));
            }
            for(int k=0; k < 4; k++){
                if(i != k){
                    double fact = (-1) * matrix.get(k).get(i);
                    for(int h=0; h < 5; h++){
                        matrix.get(k).set(h, matrix.get(k).get(h)+(fact*matrix.get(i).get(h)));
                    }
                }
            }
        }
        
        Hashtable res = new Hashtable();
        res.put("b0", matrix.get(0).get(4));
        res.put("b1", matrix.get(1).get(4));
        res.put("b2", matrix.get(2).get(4));
        res.put("b3", matrix.get(3).get(4));
        
        return res;
    }
    
    /**
    * @function agrega una row al registro
    * @param tRow
    * @return null
    */
    //.i
    public void addRow(LinkedList<Double> tRow) {
        register.add(tRow);
    }
    
    /**
    * @function retorna la cantidad de registros que hay
    * @param null
    * @return register.size()
    */
    //.i
    public double getN(){
        return register.size();
    }
}
