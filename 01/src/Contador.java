/**
 * @description
 *  Clase contador donde se lleva registro de la cantidad de lineas que hay
 *  en un archivo, destinada a ser instanciada dentro de la clase archivo.
 * 
 * @author Heriberto Gil Morales A01383221
 * 
 * @date 22/02/2021
 */

package proyecto;

//.i
public class Contador {
    
    private boolean multicommentStatus;
    private int countBlank;
    private int countComment;
    private int countCode;
    private int totalCount;
    
    /**
    * @function Counstructor de la clase
    * @param null
    * @return null
    */
    //.i
    public Contador(){
        multicommentStatus = false;
        countBlank = 0;
        countComment = 0;
        countCode = 0;
        totalCount = 0;
    }
    
    /**
    * @function 
    *   retorna el estatus actual de multicodigo para verificar si
    *   se contabiliza en comment.
    * @param null
    * @return multicommentStatus
    */
    public boolean getMulticommentStatus(){
        return multicommentStatus;
    }
    
    /**
    * @function 
    *   retorna el contador de lineas en blanco
    * @param null
    * @return countBlank
    */
    public int getCountBlank(){
        return countBlank;
    }
    
    /**
    * @function 
    *   retorna el contador de los comentarios
    * @param null
    * @return countComment
    */
    public int getCountComment(){
        return countComment;
    }
    
    /**
    * @function 
    *   retorna el contador del codigo
    * @param null
    * @return countCode
    */
    public int getCountCode(){
        return countCode;
    }
    
    /**
    * @function 
    *   retorna el contador total de lineas
    * @param null
    * @return totalCount
    */
    public int getTotalCount(){
        return totalCount;
    }
    
    /**
    * @function 
    *   asigna el estatus de comentario multilinea
    * @param status
    * @return null
    */
    public void setCommentStatus(boolean status){
        multicommentStatus = status;
    }
    
    /**
    * @function 
    *   cuenta las lineas dependiendo de la opcion que se pasa como argumento
    * @param op
    * @return null
    */
    //.i
    public void countLine(int op){
        totalCount++;
        if(multicommentStatus){
            countComment++;
            //System.out.println("add comment: "+countComment);
        }
        else{
            switch(op){
                //blank
                case 1:
                    countBlank++;
                    //System.out.println("add blank: "+countBlank);
                    break;
                //comment
                case 2:
                    countComment++;
                    //System.out.println("add comment: "+countComment);
                    break;
                //code
                case 3:
                    countCode++;
                    //System.out.println("add code: "+countCode);
                    break;
            }
        }
    }
}
