/**
 * @description
 *  Clase Main desde aqui se corre el programa, se pide la entrada
 *  y se despliega la salida
 *
 * @author Heriberto Gil Morales A01383221
 *
 * @date 22/02/2021
 */
package lab02;

//.b=40

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static PrintWriter writer;

    private static void openResultArchive(){
        try{
            writer = new PrintWriter(new FileWriter("ConteoLDC.txt"));
        }
        catch (IOException ioe) {
            System.out.println("File Not fund Call 911");
        }
    }
    
    private static void closeResultArchive(){
        writer.close();
    }
    
    //.i
    /**
     * @function funcion main
     * @param null
     * @return null
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //.d=1
        LinkedList<String> names = new LinkedList();
        LinkedList<Archivo> archs = new LinkedList();

        System.out.println("Nombre de los archivos (Ingresa linea vacia una vez terminado): "); //.m
        String entrada = input.nextLine();
        
        while(!entrada.isEmpty()){
            // separa en parte la entrada general y 
            String[] parts = entrada.split("\\.");
            
            //revisa si existe la familia dentro de la lista para agregar extension
            //si no existe la crea
            if(names.contains(parts[0])){
                int index = names.indexOf(parts[0]);
                archs.get(index).addExtention("."+parts[1]);
            }
            else{
                names.add(parts[0]);
                
                Archivo temp = new Archivo(parts[0]);
                temp.addExtention("."+parts[1]);
                archs.add(temp);
            }
            
            entrada = input.nextLine();
        }
        
        //inicia el parseo de todos los archivos y
        //busca si hay errores durante la ejecucion
        int errorFlag=0;
        for(int i=0; i<archs.size(); i++){
            errorFlag = archs.get(i).start();
            
            if(errorFlag != 0){
                break;
            }
        }
        
        //.d=3

        //despliega los datos
        //si no hay errores se despliega normal el resultado
        if(errorFlag == 0){
            openResultArchive();
            int type;
            //ciclo de los tipos de archivos
            for(int i=1; i<=4; i++){
                //switch para determinar encabezado de seccion
                switch(i){
                    case 1:
                        System.out.println("-------------------------------------");
                        System.out.println("CLASES BASE:");
                        writer.println("CLASES BASE:");
                        break;
                    case 2:
                        System.out.println("-------------------------------------");
                        System.out.println("CLASES NUEVAS:");
                        writer.println("-------------------------------------");
                        writer.println("CLASES NUEVAS:");
                        break;
                    case 3:
                        System.out.println("-------------------------------------");
                        System.out.println("CLASES REUSADAS:");
                        writer.println("-------------------------------------");
                        writer.println("CLASES REUSADAS:");
                        break;
                    case 4:
                        System.out.println("-------------------------------------");
                        System.out.print("Total de LDC=");
                        writer.println("-------------------------------------");
                        writer.print("Total de LDC=");
                        break;
                }
                
                //despliege de datos propios de cada seccion
                if(i<4){
                    for(int j=0; j<archs.size(); j++){
                        type = archs.get(j).getType();

                        if(type == i){
                            System.out.print("   "+archs.get(j).getFileName()+": ");
                            System.out.print("T="+archs.get(j).getFinalTotal()+", ");
                            System.out.print("I="+archs.get(j).getICount());
                            writer.print("   "+archs.get(j).getFileName()+": ");
                            writer.print("T="+archs.get(j).getFinalTotal()+", ");
                            writer.print("I="+archs.get(j).getICount());
                            if(i != 2){
                                System.out.print(", B="+archs.get(j).getBCount());
                                writer.print(", B="+archs.get(j).getBCount());
                            }
                            if(i == 1){
                                System.out.print(", D="+archs.get(j).getDCount()+", ");
                                System.out.print("M="+archs.get(j).getMCount()+", ");
                                System.out.print("A="+archs.get(j).getACount());
                                writer.print(", D="+archs.get(j).getDCount()+", ");
                                writer.print("M="+archs.get(j).getMCount()+", ");
                                writer.print("A="+archs.get(j).getACount());
                            }
                            System.out.print("\n");
                            writer.print("\n");
                        }
                    }
                }
                else{
                    int classTotal = 0;
                    for(int j=0; j<archs.size(); j++){
                        classTotal += archs.get(j).getFinalTotal();
                    }
                    System.out.println(classTotal);
                    writer.println(classTotal);
                }
            }
            closeResultArchive();
        }
        else{ //despliege de errores
            
            //ciclo para buscar el tipo de error que surgio a traves de
            //las familias de archivos
            int errorType = 0;
            for(int i=0; i<archs.size(); i++){
                errorType = archs.get(i).getError();
                if(errorType != 0){
                    break;
                }
            }
            
            //despliegue de salida dependiendo del tipo de errores encontrados
            switch(errorType){ //.m
                default:
                    break;
                case 1:
                    System.out.println("-------------------------------------");
                    System.out.println("Error: Archivo u Archivos inexistentes"); //.m
                    break;
                case 2:
                    System.out.println("-------------------------------------");
                    System.out.println("Error: Archivo u Archivos no clasificables"); //.m
                    break;
                case 3:
                    System.out.println("-------------------------------------");
                    System.out.println("Error: Archivo u Archivos vacios"); //.m
                    break;
                case 4:
                    System.out.println("-------------------------------------");
                    System.out.println("Error: Error de sistema"); //.m
                    break;
            }
        }
        //.d=12
    }
}
