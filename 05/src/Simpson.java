/**
 * @description
 *  Clase Simpson, en esta clase se llevan a cabo los calculos 
 *  para hacer la sumatoria de los rectangulos del metodo simpson 
 *  para sacar la integral y tambien hace el procedimiento para
 *  sacar la x
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 05/04/2021
 */
package lab05;

//.b=38
//.i
public class Simpson {
    private double x;
    private double p;
    private double num_seg;
    private double Error;
    private Tdistrib td;
    
    /**
    * @function Counstructor de la clase
    * @param p, dof
    * @return null
    */
    //.i
    public Simpson(double p, double dof){
        this.td = new Tdistrib(dof);
        this.p = p; //.m
        //.d=1
        this.Error = 0.0000000001;
    }
    
    /**
    * @function encuentra el valor de x de acuerdo a la integral de la funcion
    * @param null
    * @return curr
    */
    //.i
    public double findX(){
        double curr = 1;
        double p1 = prepProcedure(curr);
        
        double d = curr / 2;
        curr += (p1 < this.p ? d : -d);
        
        double p2 = prepProcedure(curr);
        
        int dirFlag = 0;
        
        while(Math.abs(p2-p1) > this.Error){
            p1 = p2;
            
            if(p1 < this.p && dirFlag <= 0){
                dirFlag = 1;
                d /= 2;
            }
            else if(p1 > this.p && dirFlag >= 0){
                dirFlag = -1;
                d /= 2;
            }
            
            curr += (p1 < this.p ? d : -d);
            p2 = prepProcedure(curr);
        }
        
        return curr;
    }
    
    /**
    * @function hace el procedimiento de preparacion previo
    * a hacer la integral
    * @param null
    * @return procedure()
    */
    //.i
    private double prepProcedure(double x){
        this.x = x;
        this.num_seg = 10;
        return procedure();
    }
    
    /**
    * @function hace el procedimiento de encontrar la cantidad
    * ideal de rectangulos para sacar la integral y retorna
    * el resultado de esta
    * @param null
    * @return p2
    */
    //.i
    private double procedure(){
        double p1 = this.getP(this.getW());
        this.num_seg *= 2;
        double p2 = this.getP(this.getW());
        
        while(Math.abs(p2-p1) > this.Error){
            p1 = p2;
            num_seg *= 2;
            p2 = this.getP(this.getW());
        }
        
        return p2;
    }
    
    /**
    * @function retorna el width de los rectangulos actuales
    * @param null
    * @return x/num_seg
    */
    //.i
    private double getW(){
        return this.x/this.num_seg;
    }
    
    /**
    * @function retorna la sumatoria de simpson actual
    * @param w
    * @return (w/3)*(first+second+third+fourth)
    */
    //.i
    private double getP(double w){
        double first = this.td.F(0);
        double second = this.sum4F(w);
        double third = this.sum2F(w);
        double fourth = this.td.F(this.x);
        
        return (w/3)*(first+second+third+fourth);
    }
    
    /**
    * @function retorna la sumatoria de los terminos impares
    * de la sumatoria de simpson
    * @param w
    * @return sum
    */
    //.i
    private double sum4F(double w){
        double sum = 0;
        
        for(int i=1; i<this.num_seg; i+=2){
            sum += (4 * this.td.F(i*w));
        }
        
        return sum;
    }
    
    /**
    * @function retorna la sumatoria de los terminos ipares
    * de la sumatoria de simpson
    * @param w
    * @return sum
    */
    //.i
    private double sum2F(double w){
        double sum = 0;
        
        for(int i=2; i<this.num_seg; i+=2){
            sum += (2 * this.td.F(i*w));
        }
        
        return sum;
    }
}
