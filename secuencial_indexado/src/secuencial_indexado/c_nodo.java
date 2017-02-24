/*
 * To change this license header, choose License Headers in Project Properties.
 */
package secuencial_indexado;

public class c_nodo {
    
    private int a_Llave;
    private long a_Direccion;
    private c_nodo a_Izquierdo;
    private c_nodo a_Derecha;
    
    public c_nodo(int p_Llave,long p_Direccion, c_nodo p_Izquierdo, c_nodo p_Derecho){
        a_Llave = p_Llave;
        a_Direccion = p_Direccion;
        a_Izquierdo = p_Derecho;
    }
    
}
