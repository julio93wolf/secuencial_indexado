/*
 * @name: c_grafo.java
 * @description: Esta clase contiene los métodos para la creación, recuperación de archivos
 * de tipo binario organizados de manera secuencial indexada.
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
     * Contructor para la clase grafo.
     */
    public c_grafo(){
        m_Menu();
    }// Fin del constructor
    
    /**
     * Método para seleccionar una operacion de los archivos binarios secuenciales indexados.
     */
    private void m_Menu(){
        Scanner v_Entrada;
        int v_Opcion = 0;
        do{
            try{
                v_Entrada = new Scanner(System.in);
                System.out.println("\n\t\u001B[31mMenú\u001B[30m");
                System.out.println("\u001B[34m[1]\u001B[30m Ingresar Nodo");
                System.out.println("\u001B[34m[2]\u001B[30m Mostrar Nodos");
                System.out.println("\u001B[34m[3]\u001B[30m Muestra Nodo");
                System.out.println("\u001B[34m[4]\u001B[30m Modifica Nodo");
                System.out.println("\u001B[34m[5]\u001B[30m Eliminar Nodo");
                System.out.println("\u001B[34m[6]\u001B[30m Salir");
                System.out.print("\nOpción: ");
                v_Opcion = v_Entrada.nextInt();
                if(v_Opcion>0&&v_Opcion<7){
                    m_MenuOpcion(v_Opcion);
                }else{
                    System.out.println("\u001B[31mError: Ingrese una opción valida\u001B[30m");
                }
            }catch(Exception e){
                System.out.println("\u001B[31mError: Ingrese un número entero\u001B[30m ");
            }
        }while(v_Opcion!=6);
    }// Fin del Método
    
    /**
     * Método para redireccionar la opción elegida por el usuario.
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
                break;
            }
            case 3:{
                m_leeAleatorio();
                break;
            }
            case 4:{
                m_Modifica();
                break;
            }
            case 5:{
                m_Elimina();
                break;
            }
        }
    }// Fin de método
    
    /**
     * Método para escribir registros en el archivo maestro e indice.
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
            System.out.println("\u001B[31mError: No se pudieron abrir los archivos maestro e indice\u001B[30m");
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
                    
                    System.out.println("\n\u001B[31m¿Desea agregar otro nodo?\u001B[30m");
                    System.out.println("\u001B[34m[Si]\u001B[30m = 1");
                    System.out.println("\u001B[34m[No]\u001B[30m = Cualquier tecla");
                    System.out.print("\nOpción:");
                    v_Opcion=v_Entrada.next().charAt(0);
                }catch(Exception e){
                    System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
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
    
    /**
     * Método para mostrar por consola los registros del archivo maestro de manera secuencial.
     */
    private void m_leeSecuencial(){
        long v_apActual,v_apFinal;       
        System.out.println("\n\tGrafo\n");
        System.out.print("Llave\t");
        System.out.print("Origen\t");
        System.out.print("Destino\t");
        System.out.println("Peso");
        try{
            RandomAccessFile v_Archivo = new RandomAccessFile("src/archivos/maestro.dat","r");
            v_apActual=v_Archivo.getFilePointer();
            v_apFinal=v_Archivo.length();
            while(v_apActual!=v_apFinal){
                System.out.print(v_Archivo.readInt()+"\t");
                System.out.print("\u001B[31m"+v_Archivo.readChar()+"\t");
                System.out.print("\u001B[31m"+v_Archivo.readChar()+"\t");
                System.out.print("\u001B[34m"+v_Archivo.readFloat()+"\u001B[30m\n");
                v_apActual=v_Archivo.getFilePointer();
            }
            System.out.println("");
            v_Archivo.close();
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[30m");
        }
    }//Fin del Método
    
    /**
     * Lee de manera aleatoria un registro del archivo maestro, calculando el tamaño del registro,
     * posteriormente se ingresa el número de nodo y se desplaza hasta la direccion del registro especifico.
     */
    private void m_leeAleatorio(){        
        int v_Llave;
        char v_Opcion='1';
        long v_tamRegistro=0,v_Desplazamiento=0;
        RandomAccessFile v_Maestro = null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/archivos/maestro.dat","rw");
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[30m");
        }
        if(v_Maestro!=null){
            try{
                v_Maestro.readInt();
                v_Maestro.readChar();
                v_Maestro.readChar();
                v_Maestro.readFloat();
                v_tamRegistro=v_Maestro.getFilePointer(); //Regresa direccion del Puntero
            }catch(Exception e){
                System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[30m");
            }
            do{
                try{
                    v_Entrada=new Scanner(System.in);
                    System.out.print("Ingrese Llave: ");
                    v_Llave=v_Entrada.nextInt();
                    v_Desplazamiento=(v_Llave-1)*v_tamRegistro;
                    v_Maestro.seek(v_Desplazamiento);
                    System.out.print("\nLlave\t");
                    System.out.print("Origen\t");
                    System.out.print("Destino\t");
                    System.out.println("Peso");
                    System.out.print(v_Maestro.readInt()+"\t");
                    System.out.print("\u001B[31m"+v_Maestro.readChar()+"\t");
                    System.out.print(v_Maestro.readChar()+"\t");
                    System.out.print("\u001B[34m"+v_Maestro.readFloat()+"\u001B[30m\n");
                    System.out.println("\n\u001B[31m¿Desea buscar otro nodo?\u001B[30m");
                    System.out.println("\u001B[34m[Si]\u001B[30m = 1");
                    System.out.println("\u001B[34m[No]\u001B[30m = Cualquier tecla");
                    System.out.print("\nOpción:");
                    v_Opcion=v_Entrada.next().charAt(0);
                }catch(Exception e){
                    System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
                }
            }while(v_Opcion=='1');
        }
    }//Fin del Método
    
    
    private void m_Modifica(){        
        int v_Llave;
        char v_Opcion='1';
        long v_tamRegistro=0,v_Desplazamiento=0;
        RandomAccessFile v_Maestro = null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/archivos/maestro.dat","rw");
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[30m");
        }
        if(v_Maestro!=null){
            try{
                v_Maestro.readInt();
                v_Maestro.readChar();
                v_Maestro.readChar();
                v_Maestro.readFloat();
                v_tamRegistro=v_Maestro.getFilePointer(); //Regresa direccion del Puntero
            }catch(Exception e){
                System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[30m");
            }
            do{
                try{
                    v_Entrada=new Scanner(System.in);
                    System.out.print("Ingrese Llave: ");
                    v_Llave=v_Entrada.nextInt();
                    v_Desplazamiento=(v_Llave-1)*v_tamRegistro;
                    v_Maestro.seek(v_Desplazamiento);
                    v_Maestro.readInt();
                    
                    System.out.print("\nOrigen: ");
                    a_Origen = v_Entrada.next().charAt(0);
                    System.out.print("Destino: ");
                    a_Destino = v_Entrada.next().charAt(0);
                    System.out.print("Peso: ");
                    a_Peso = v_Entrada.nextFloat();
                    
                    v_Maestro.writeChar(a_Origen);
                    v_Maestro.writeChar(a_Destino);
                    v_Maestro.writeFloat(a_Peso);
                    
                    System.out.println("\n\u001B[31m¿Desea modificar otro nodo?\u001B[30m");
                    System.out.println("\u001B[34m[Si]\u001B[30m = 1");
                    System.out.println("\u001B[34m[No]\u001B[30m = Cualquier tecla");
                    System.out.print("\nOpción:");
                    v_Opcion=v_Entrada.next().charAt(0);
                }catch(Exception e){
                    System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
                }
            }while(v_Opcion=='1');
        }
    }//Fin del Método
    
    private void m_Elimina(){        
        int v_Llave;
        char v_Opcion='1';
        long v_tamRegistroMaestro=0,v_tamRegistroIndice=0,v_Desplazamiento=0;
        RandomAccessFile v_Maestro = null,v_Indice=null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/archivos/maestro.dat","rw");
            v_Indice = new RandomAccessFile("src/archivos/indice.dat","rw");
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[30m");
        }
        if(v_Maestro!=null && v_Indice!=null){
            try{
                v_Maestro.readInt();
                v_Maestro.readChar();
                v_Maestro.readChar();
                v_Maestro.readFloat();
                v_Indice.readInt();
                v_Indice.readLong();
                v_tamRegistroMaestro=v_Maestro.getFilePointer(); //Regresa direccion del Puntero
                v_tamRegistroIndice=v_Indice.getFilePointer();
            }catch(Exception e){
                System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[30m");
            }
            do{
                try{
                    v_Entrada=new Scanner(System.in);
                    System.out.print("Ingrese Llave: ");
                    v_Llave=v_Entrada.nextInt();
                    v_Desplazamiento=(v_Llave-1)*v_tamRegistroMaestro;
                    v_Maestro.seek(v_Desplazamiento);
                    v_Maestro.writeInt(-1);
                    
                    v_Desplazamiento=(v_Llave-1)*v_tamRegistroIndice;
                    v_Indice.seek(v_Desplazamiento);
                    v_Indice.writeInt(-1);
                    
                    System.out.println("\n\u001B[31m¿Desea eliminar otro nodo?\u001B[30m");
                    System.out.println("\u001B[34m[Si]\u001B[30m = 1");
                    System.out.println("\u001B[34m[No]\u001B[30m = Cualquier tecla");
                    System.out.print("\nOpción:");
                    v_Opcion=v_Entrada.next().charAt(0);
                }catch(Exception e){
                    System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
                }
            }while(v_Opcion=='1');
        }
    }//Fin del Método
}
