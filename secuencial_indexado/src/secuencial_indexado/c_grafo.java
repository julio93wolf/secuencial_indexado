/*
 * c_grafo.java : Esta clase contiene los metodos para la creacion, recuperacion y modificacion de archivos
 *                de tipo binario organizados de manera secuencial indexada
 * @version 17.2.24
 * @author Valle Rodriguez Julio Cesar
 */

package secuencial_indexado;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class c_grafo {
    
    //Atributos de la clase 
    private int a_Llave;
    private char a_Origen;
    private char a_Destino;
    private float a_Peso;
    
    /**
     * Contructor para la clase grafo
     */
    public c_grafo(){
        m_Menu();
    }// Fin del constructor
    
    /**
     * Método para seleccionar una de las opciones de los archivos de tipo binarios secuenciales indexados
     */
    private void m_Menu(){
        Scanner v_Entrada;
        int v_Opcion = 0;
        do{
            try{
                v_Entrada = new Scanner(System.in);
                System.out.println("\n\tMenú\n");
                System.out.println("[1] Ingresa Nodo");
                System.out.println("[2] Mostrar Nodos");
                System.out.println("[3] Muestra Nodo");
                System.out.println("[4] Salir");
                System.out.print("\nOpción:");
                v_Opcion = v_Entrada.nextInt();
                if(v_Opcion>0&&v_Opcion<5){
                    m_MenuOpcion(v_Opcion);
                }else{
                    System.out.println("Error: Ingrese una opcion valida");
                }
            }catch(Exception e){
                System.out.println("Error: Ingrese un número entero");
            }
        }while(v_Opcion!=4);
    }// Fin del Método
    
    /**
     * Método para redireccionar la opción elegida por el usuario
     * @param p_Opcion Operacion seleccionada por el usuario
     */
    private void m_MenuOpcion(int p_Opcion){
        switch(p_Opcion){
            case 1:{
                m_escribeArchivo();
                break;
            }
            case 2:{
                m_leeSecuencial();
            }
            case 3:{}
        }
    }// Fin de método
    
    /**
     * Método para escribir registros en el archivo maestro e indice
     */
    private void m_escribeArchivo(){
        char v_Opcion = '1';
        long v_Direccion;
        RandomAccessFile v_Maestro = null;
        RandomAccessFile v_Indice = null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/archivos/maestro.dat","rw");
            v_Indice = new RandomAccessFile("src/archivos/indice.dat","rw");
        }catch(Exception e){
            System.out.println("Error: No se pudieron abrir los archivos maestro e indice");
        }
        if(v_Maestro!=null&&v_Indice!=null){
            do{
                v_Opcion = '1';
                try{
                    v_Entrada = new Scanner(System.in);
                    v_Maestro.seek(v_Maestro.length());
                    v_Indice.seek(v_Indice.length());
                    v_Direccion=v_Maestro.getFilePointer();
                    
                    System.out.print("\nLlave: ");
                    a_Llave = v_Entrada.nextInt();
                    System.out.print("Origen: ");
                    a_Origen = v_Entrada.next().charAt(0);
                    System.out.print("Destino: ");
                    a_Destino = v_Entrada.next().charAt(0);
                    System.out.print("Peso: ");
                    a_Peso = v_Entrada.nextFloat();
                    
                    v_Maestro.writeInt(a_Llave);
                    v_Maestro.writeChar(a_Origen);
                    v_Maestro.writeChar(a_Destino);
                    v_Maestro.writeFloat(a_Peso);
                    
                    v_Indice.writeInt(a_Llave);
                    v_Indice.writeLong(v_Direccion);
                    
                    System.out.println("\n\t¿Desea agregar otro nodo?");
                    System.out.println("[Si] = 1");
                    System.out.println("[No] = Cualquier tecla");
                    System.out.print("\nOpción:");
                    v_Opcion=v_Entrada.next().charAt(0);
                }catch(Exception e){
                    System.out.println("Error: Valor insertado invalido");
                }
            }while(v_Opcion=='1');
            
            try{
                v_Maestro.close();
                v_Indice.close();
            }catch(Exception e){
                System.out.println(e.toString());
            }
            
        }
    }//Fin del método
    
    private void m_leeSecuencial(){
        long v_apActual,v_apFinal;       
        System.out.println("\n\tGrafo\n");
        try{
            RandomAccessFile v_Archivo = new RandomAccessFile("src/archivos/maestro.dat","r");
            v_apActual=v_Archivo.getFilePointer();
            v_apFinal=v_Archivo.length();
            while(v_apActual!=v_apFinal){
                System.out.print(v_Archivo.readInt()+"\t");
                System.out.print(v_Archivo.readChar()+"\t");
                System.out.print(v_Archivo.readChar()+"\t");
                System.out.print(v_Archivo.readFloat()+"\n");
                v_apActual=v_Archivo.getFilePointer();
            }
            System.out.println("");
            v_Archivo.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    private void m_leeAleatorio(){        
        int v_Llave;
        long v_Direccion;
        RandomAccessFile v_Maestro = null;
        RandomAccessFile v_Indice = null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/archivos/maestro.dat","rw");
            v_Indice = new RandomAccessFile("src/archivos/indice.dat","rw");
        }catch(Exception e){
            System.out.println("Error: No se pudieron abrir los archivos maestro e indice");
        }
        if(v_Maestro!=null&&v_Indice!=null){
            
        }
    }
}
