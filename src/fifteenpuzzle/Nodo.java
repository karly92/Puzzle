/****************************************************
 * PROBLEMA: RESOLVER EL PUZZLE DE 4*4 IMPLEMENTANDO*
 * EL ALGORITMO A* Y LA HEURISTICA DE MANHATTAN,    *
 * COMPROBANDO SI DADO UN ESTADO INICIAL Y UNO FINAL*
 * ÉSTE TIENE SOLUCION.                             *               
 *GOMEZ SANTOS GABRIELA                             *
 *LOPEZ OJEDA CRISTINA                              *
 *SANCHEZ CAMACHO KARLA PAOLA                       *
 ****************************************************/

package fifteenpuzzle;

import java.util.*;

public class Nodo implements Comparable {
    
    private int tablero[][] = new int[4][4];
    
    private int F = 0;
    
    private int G = 0;
    
    private int H = 0;
    
    private int xCero;
    
    private int yCero;
    
    private Nodo estadoPadre;
    
    
    public Nodo(int[][] tablero){
       
        this.tablero = tablero; 
        
        estadoPadre = null;
        
    }
    
    public int[][] getTablero(){
       return tablero;
    }
    
    public void setEstadoPadre(Nodo nodo){
        estadoPadre = nodo;
    }
   
    public Nodo getEstadoPadre(){
        return estadoPadre;
    }
        public void setG(){
        if(estadoPadre == null){
            G=0;
        }
        else{
            G=estadoPadre.G+1;
        }
        setF();
        
    }
    public int getG(){
        return G;
    }
    
    public void setH(int h){ 
         H = h ;
         setF();
         
    }
    
    public int getH(){
        return H;
    }
    
    public void setF(){
        F= G+H;
        
    }
    
    public int getF(){
        return F;
    }
     
    public int  manhattan(int[][]estadoFinal ){
        
        int nmanhattan = 0;
	for (int i=0; i < tablero.length; ++i)
        {
            for (int j=0; j < tablero[i].length; ++j)
            {

		if(estadoFinal[i][j]!=tablero[i][j]&&tablero[i][j]!=0)
                { 
                    int i2=0, j2=0;
			while(i2<estadoFinal.length && (estadoFinal[i2][j2]!=tablero[i][j]))
                        {
                        	j2++;
                      		if (j2 >= estadoFinal[i2].length) 
                                {
                                      i2++;
			              j2=0;
                            	}  
                       }
                       nmanhattan+=Math.abs((i2+1)-(i+1))+Math.abs((j2+1)-(j+1));
                }
            }
        }
     return nmanhattan;
    }
    
    public void casillaVacia(){
        for(int i = 0; i < tablero.length; i++)
        {
            for (int j = 0 ; j < tablero.length ; j++)
            {
                if(tablero[i][j] == 0)
                {
                    xCero = i;
                    yCero = j;
                }
            }
        }
    }    
    public int xCero(){
        
        return xCero;
    }
    
    public int yCero(){
        
        return yCero;
    }
    public ArrayList<Nodo> sucesores(){
        
        ArrayList<Nodo> sucesores = new ArrayList<Nodo>();
        
        casillaVacia();
        int fi = xCero();
	int co = yCero();
        
	if((fi+1)<tablero.length)
        {    
            int[][] copia = new int[tablero.length][tablero[0].length];
			
            for (int i=0; i < tablero.length; ++i)
                for (int j=0; j < tablero[i].length; ++j)
                    copia[i][j]= tablero[i][j];
			
            copia[fi][co] = copia[fi+1][co];
            copia[fi+1][co] = 0;
            Nodo nodo = new Nodo(copia);
            sucesores.add(nodo);
        }
		
        if((co+1) < tablero[0].length)
        {
            
            int[][] copia = new int[tablero.length][tablero[0].length];
            
            for (int i=0; i < tablero.length; ++i)
                for (int j=0; j < tablero[i].length; ++j)
                    copia[i][j]=tablero[i][j];
                    
            copia[fi][co] = copia[fi][co+1];
            copia[fi][co+1] = 0;
            Nodo nodo = new Nodo(copia);
            sucesores.add(nodo);
        }
		
        if((fi-1)>=0)
        {
            
            int[][] copia = new int[tablero.length][tablero[0].length];
			
            for (int i=0; i < tablero.length; ++i)
                for (int j=0; j < tablero[i].length; ++j)
                    copia[i][j]=tablero[i][j];
			
            copia[fi][co] = copia[fi-1][co];
            copia[fi-1][co] = 0;
            
            Nodo nodo = new Nodo(copia);
            sucesores.add(nodo);
        }
		
        if((co-1)>=0)
        {
            int[][] copia = new int[tablero.length][tablero[0].length];
			
            for (int i=0; i < tablero.length; ++i)
                for (int j=0; j < tablero[i].length; ++j)
                    copia[i][j]=tablero[i][j];
			
           copia[fi][co] = copia[fi][co-1];
           copia[fi][co-1] = 0;
           
           Nodo nodo = new Nodo(copia);
           sucesores.add(nodo);
        }
		
        return sucesores;
    
    }
    

    public int compareTo(Object objeto){
        if (getF() > ((Nodo) objeto).getF()) return 1;
            else if (F < ((Nodo) objeto).getF()) return -1;
                else return 0;
    }
     
    public String toString(){
        String s = "";
        for(int i = 0; i < tablero.length; i++)
        {
             s+="\n";
             for(int j = 0 ; j < tablero.length ; j++)
             {
                 s+=tablero[i][j]+",";  
             }
        }
        return s;
    } 
}

