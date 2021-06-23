/**
 * @description
 *  Clase Tdistrib, en esta clase se hacen los calculos
 *  para las llamadas a la funcion de la distribucion t
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 22/03/2021
 */
package lab06;

//.b=17

//.i
public class Tdistrib {
    private double dof;
    
    /**
    * @function constructor de la clase
    * @param dof
    * @return void
    */
    //.i
    public Tdistrib(double dof){
        this.dof = dof;
    }
    
    /**
    * @function funcion gamma, auxiliar para la funcion de distribucion t
    * @param n
    * @return (n-1)!
    */
    //.i
    private double gamma(double n){
        if(n == 1){
            return 1;
        }
        
        if(n == 0.5){
            return Math.sqrt(Math.PI);
        }
        
        return (n-1) * gamma(n-1);
    }
    
    /**
    * @function funcion de distribucion t
    * @param x
    * @return (up/(dl*dr))*right
    */
    //.i
    public double F(double x){
        double right = Math.pow((1+(Math.pow(x, 2)/this.dof)), -((this.dof+1)*0.5));
        double up = gamma((this.dof+1)*0.5);
        double dl = Math.pow((this.dof*Math.PI), 0.5);
        double dr = gamma(this.dof*0.5);

        return (up/(dl*dr))*right;
    }
}
