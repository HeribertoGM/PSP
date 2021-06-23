/**
 * @description
 *  Clase Archivo donde se maneja la apertura, lectura y cierre del archivos,
 *  tambien tiene la tarea de discernir el tipo de tags.
 *
 * @author Heriberto Gil Morales A01383221
 *
 * @date 07/03/2021
 */
package lab02;

//.b=78
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

//.i
public class Archivo {

    private Contador counter;
    private FileReader file;
    private BufferedReader reader;
    private int errorFlag;
    private String fileGeneral;
    private LinkedList<String> extentions;

    //.i
    /**
     * @function Counstructor de la clase
     * @param null
     * @return null
     */
    public Archivo(String filename) {
        counter = new Contador();
        fileGeneral = filename;
        errorFlag = 0;
        extentions = new LinkedList();
    }

    //.i
    /**
     * @function agrega extencion a la lista por revisar
     * @param ext
     * @return null
     */
    public void addExtention(String ext) {
        if (!ext.contains(" ") && ext.contains(".")) {
            if (!extentions.contains(ext)) {
                extentions.add(ext);
            }
        }
    }

    //.i
    /**
     * @function inicia la rutina para parsear todos los archivos
     * @param null
     * @return int
     */
    public int start() {
        for (int i = 0; i < extentions.size(); i++) {
            //System.out.println("con terminacion: "+extentions.get(i));
            String fName = fileGeneral + extentions.get(i);

            open(fName);
            //System.out.println("paso open, errorFlag="+errorFlag);
            parse();
            //System.out.println("paso parse, errorFlag="+errorFlag);
            close();
            //System.out.println("paso close, errorFlag="+errorFlag);
        }
        getType();
        //System.out.println("paso type, errorFlag="+errorFlag);
        
        if (errorFlag != 0) {
            return -1;
        }
        
        return 0;
    }

    //.i
    /**
     * @function Abre el archivo y lo coloca en una variable
     * @param fileName
     * @return null
     */
    private void open(String fileName) { //.m
        try {
            file = new FileReader(fileName);
            reader = new BufferedReader(file);
        } catch (IOException e) {
            raiseError(1);
        }
    }

    //.i
    /**
     * @function Cierra el archivo
     * @param null
     * @return null
     */
    private void close() { //.m
        if (errorFlag != 0) {
            return;
        };

        try {
            reader.close();
        } catch (IOException e) {
            raiseError(4); //.m
        }
    }

    //.i
    /**
     * @function Funcion en la que se se lee linea por linea y se identifica que
     * tipo de tag es
     * @param null
     * @return null
     */
    private void parse() { //.m
        if (errorFlag != 0) {
            return;
        };

        try {
            //.d=1

            String line;
            line = reader.readLine();

            if (line == null) {
                raiseError(3);
            }

            boolean multicommentFlag = false;
            while (line != null) {

                //.d=27
                String formatedLine = line.trim();
                
                if (!multicommentFlag) {
                    if (formatedLine.contains("/*")) {
                        multicommentFlag = true;
                    }
                } else {
                    if (formatedLine.contains("*/")) {
                        multicommentFlag = false;
                    }
                }
                
                if (formatedLine.length() > 2) {
                    boolean slashFlag = formatedLine.startsWith("//");
                    boolean bTag = formatedLine.startsWith("//.b=");
                    boolean mTag = formatedLine.endsWith("//.m");
                    boolean iTag = formatedLine.startsWith("//.i");
                    boolean dTag = formatedLine.startsWith("//.d=");
                    
                    if(!multicommentFlag){
                        if (bTag) {
                            String num = formatedLine.split("//.b=")[1];
                            int cant = Integer.parseInt(num);
                            counter.countLine(1, cant);
                        } else {
                            if (mTag) {
                                counter.countLine(2, 0);
                            } else {
                                if (iTag) {
                                    counter.countLine(3, 0);
                                } else {
                                    if (dTag) {
                                        String num = formatedLine.split("//.d=")[1];
                                        int cant = Integer.parseInt(num);
                                        counter.countLine(4, cant);
                                    } else {
                                        if(!slashFlag){
                                            counter.countLine(0, 0);
                                        }
                                        else{
                                            //System.out.println(formatedLine);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            raiseError(4); //.m
        }
    }

    /**
     * @function Asigna valor a la flag de error para manejo de output en caso
     * de errores
     * @param op
     * @return null
     */
    private void raiseError(int op) {
        errorFlag = op;
    }

    /**
     * @function retorna la cantidad de tags B
     * @param null
     * @return counter.getBCount()
     */
    public int getBCount() { //.m
        return counter.getBCount(); //.m
    }

    /**
     * @function retorna la cantidad de tags M
     * @param null
     * @return counter.getMCount()
     */
    public int getMCount() { //.m
        return counter.getMCount(); //.m
    }

    /**
     * @function retorna la cantidad de tags I
     * @param null
     * @return counter.getICount()
     */
    public int getICount() { //.m
        return counter.getICount(); //.m
    }

    /**
     * @function retorna la cantidad de tags D
     * @param null
     * @return counter.getDCount()
     */
    public int getDCount() {
        return counter.getDCount();
    }

    /**
     * @function retorna la cantidad de lineas agregadas
     * @param null
     * @return counter.getACount()
     */
    public int getACount() {
        return counter.getACount();
    }

    /**
     * @function retorna la cantidad de lineas totales
     * @param null
     * @return counter.getTotalCount()
     */
    public int getFinalTotal() {
        return counter.getTotalCount();
    }

    /**
     * @function retorna el valor de la flag de error
     * @param null
     * @return errorFlag
     */
    public int getError() {
        return errorFlag;
    }

    //.i
    /**
     * @function retorna el tipo de archivo que es
     * @param null
     * @return int
     */
    public int getType() {
        /*System.out.println("B="+getBCount());
        System.out.println("M="+getMCount());
        System.out.println("D="+getDCount());
        System.out.println("A="+getACount());*/
        if (getBCount() > 0 && (getMCount() > 0 || getDCount() > 0 || getACount() > 0)) {
            return 1;
        } else if (getBCount() == 0 && getMCount() == 0 && getDCount() == 0 && getACount() > 0) {
            return 2;
        } else if (getBCount() > 0 && getMCount() == 0 && getDCount() == 0 && getACount() == 0) {
            return 3;
        } else {
            if(errorFlag == 0){
                raiseError(2);
            }
            return 0;
        }
    }
    
    /**
     * @function retorna el nombre de la familia de archivos
     * @param null
     * @return fileGeneral
     */
    public String getFileName() {
        return fileGeneral;
    }
}
