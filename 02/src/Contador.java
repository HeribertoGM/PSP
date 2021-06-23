/**
 * @description
 *  Clase contador donde se lleva registro de la cantidad de lineas que hay
 *  en un archivo, destinada a ser instanciada dentro de la clase archivo.
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 07/03/2021
 */

package lab02;

//.b=40

//.i
public class Contador {

    //.d=1
    private int bCount; //.m
    private int mCount; //.m
    private int iCount; //.m
    private int dCount;
    private int totalCount;

    //.i
    /**
     * @function Counstructor de la clase
     * @param null
     * @return null
     */
    public Contador() {
        //.d=1
        bCount = 0; //.m
        mCount = 0; //.m
        iCount = 0; //.m
        dCount = 0;
        totalCount = 0;
    }

    //.d=2
    
    /**
     * @function retorna el contador de los tags b
     * @param null
     * @return bCount
     */
    public int getBCount() { //.m
        return bCount; //.m
    }

    /**
     * @function retorna el contador de los tags m
     * @param null
     * @return mCount
     */
    public int getMCount() { //.m
        return mCount; //.m
    }

    /**
     * @function retorna el contador de los tags i
     * @param null
     * @return iCount
     */
    public int getICount() { //.m
        return iCount; //.m
    }

    /**
     * @function retorna el contador de los tags d
     * @param null
     * @return dCount
     */
    public int getDCount() {
        return dCount;
    }
    
    //.i
    /**
     * @function retorna el contador de las lineas agregadas
     * @param null
     * @return totalCount - bCount + dCount
     */
    public int getACount() {
        return totalCount - bCount + dCount;
    }

    /**
     * @function retorna el contador total de lineas
     * @param null
     * @return totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    //.d=2
    
    //.i
    /**
     * @function Contabiliza las lineas dependiendo de la opcion pasada
     * @param op, cant
     * @return null
     */
    public void countLine(int op, int cant) {
        //.d=3
        switch (op) {
            //blank
            case 1:
                bCount += cant; //.m
                break;
            //comment
            case 2:
                mCount++; //.m
                totalCount++;
                break;
            //code
            case 3:
                iCount++; //.m
                break;
            case 4:
                dCount += cant;
                break;
            default:
                totalCount++;
                break;
        }

    }
}
