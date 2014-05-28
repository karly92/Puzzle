/****************************************************
 * PROBLEMA: RESOLVER EL PUZZLE DE 4*4 IMPLEMENTANDO*
 * EL ALGORITMO A* Y LA HEURISTICA DE MANHATTAN,    *
 * COMPROBANDO SI DADO UN ESTADO INICIAL Y UNO FINAL*
 * ÉSTE TIENE SOLUCION.                             *               
 *GOMEZ SANTOS GABRIELA                             *
 *LOPEZ OJEDA CRISTINA                              *
 *SANCHEZ CAMACHO KARLA PAOLA                       *
 ****************************************************/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fifteenpuzzle;


import java.util.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FifteenPuzzle {  
    int[][] inicial = new int[4][4]; 
    int[][] fin = new int[4][4]; 
    private Nodo nodoInicial,nodoFin;
    int[] ini=new int[16];
    int[] finn=new int[16];
    int val=0;
    int cont = 0, fila=0, fila_finale=0;
    
    public FifteenPuzzle(int [][]inicial,int[][]fin, int fila, int fila_finale, int ini[],int finn[])
    {
        this.fila=fila;
        this.fila_finale=fila_finale;
        this.inicial =inicial;
        this.fin = fin;
        this.ini=ini;
        this.finn=finn;
        nodoInicial = new Nodo(inicial);
        nodoFin = new Nodo(fin);
    
    }
    public boolean contiene(ArrayList a, Nodo n)
    {
        Iterator i = a.iterator();
        boolean b= false;
        while(i.hasNext()&&n!=null)
        {
            if(n.toString().equals(i.next().toString()))
               b=true;
        }
      return b;
    }
    public ArrayList resolver()
    {
        val=sol(ini,fila)+sol(finn,fila_finale);   
        if((val==2) || (val==0))
        {
         val=0;   
        Lista listaAbierta = new Lista();
        ArrayList listaCerrada = new ArrayList<Nodo>();
        Nodo mejornodo = null;
        boolean caminoEncontrado = false;
        int iteraciones = 0;
        int manhattan = nodoInicial.manhattan(fin);
        nodoInicial.setH(manhattan);
        nodoInicial.setG();
        listaAbierta.push(nodoInicial);

        while (!listaAbierta.isEmpty() && !caminoEncontrado)
        {
            iteraciones++;
            mejornodo = (Nodo) listaAbierta.pop();
            listaCerrada.add(mejornodo);
            ArrayList<Nodo> nodosAdyacentes = mejornodo.sucesores();      
            Iterator<Nodo> iterator = nodosAdyacentes.iterator();
            while(iterator.hasNext()&&!caminoEncontrado)
            { 
                Nodo nodoAdyacente = iterator.next();
                int m= nodoAdyacente.manhattan(fin);
                nodoAdyacente.setH(m);
                nodoAdyacente.setG();
                if(!contiene(listaCerrada,nodoAdyacente))
                {
                    if(!listaAbierta.contains(nodoAdyacente))
                    {
                       nodoAdyacente.setEstadoPadre(mejornodo);                   
                        listaAbierta.push(nodoAdyacente);
                        if(esFinal(nodoAdyacente))
                        {
                            caminoEncontrado=true;
                            nodoFin = nodoAdyacente;
                        }
                    }
                    else
                    {
                        int nuevoF= nodoAdyacente.getF();
                        Nodo n = (Nodo) listaAbierta.getElemento(nodoAdyacente);
                        int F = n.getF();
                        if(nuevoF <  F)
                        {
                            listaAbierta.removeElementoPos(n);
                            listaAbierta.push(nodoAdyacente);
                            listaAbierta.reordenar();
                        }
                
                    }
                }
            }
        }   
        if (caminoEncontrado)
        {
            ArrayList camino = new ArrayList<Nodo>();
            Nodo nodoAuxiliar = nodoFin;
            while (nodoAuxiliar != null)
            {
                camino.add(0, nodoAuxiliar);
                nodoAuxiliar = nodoAuxiliar.getEstadoPadre();
            }
            return camino;
        }
        else
        {
            return null;
        }
    }
        else
        {  
            System.out.println("EL PUZZLE NO TIENE SOLUCIÓN :/");
            System.exit(0);        
            return null;
     }
     public boolean esFinal(Nodo n)
     {
         if(n.toString().compareTo(nodoFin.toString())==0)
         {
             return true;   
         }
         return false;
     }
        
     public int sol(int array[],int f)
     {
        int inversor=0; 
        for (int a=0;a<=15;a++)
        {
            for (int b=a; b<=15; b++)
            {
                if(array[a]>array[b])
                {
                    if(array[a]==0 || array[b]==0)
                    {
                    }
                    else
                    {                     
                        inversor=inversor+1;
                    }
                }
            }
        }
        if (((inversor+f)%2)!=0)//si es impar
        {  
          System.out.println("ES IMPAR");
          return 1;
        }
        else
        {   
          System.out.println("ES PAR");
          return 0;  
        }
    }
     
    public static void main(String [] args)
    {    
        String linea=null;
        int fila=0;
        int fila_finale=0;
        int[] posini=new int[16];
        int[] finale=new int[16];
        int[][] inicial=new int[4][4];
        int[][]  fin=new int[4][4];
        int i=0, c=0, cont=0,conini=0, confin=0, soniguales=0;
        try{
            FileInputStream archivo = new FileInputStream("tablero.txt");
            DataInputStream entrada = new DataInputStream(archivo);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            for(int j=0;j<=7;j++)
            {
                linea=buffer.readLine();
                StringTokenizer st = new StringTokenizer(linea,",");
                while (st.hasMoreTokens())
                {   
                    if(j<=3)
                    {
                        posini[i]=Integer.parseInt(st.nextToken());
                        if(posini[i]==0)
                            fila=j;  
                        i++;
                    }
                    else 
                    {
                        finale[c]=Integer.parseInt(st.nextToken());
                        if(finale[c]==0)
                            fila_finale=j;  
                        c++;
                    }  
                }
                
            }
            
            entrada.close();
        }
        catch (Exception e)
        { //Catch de excepciones
  
            System.err.println("Ocurrio un error: " + e.getMessage());
            System.exit(0);
        }
        //vaciado de arreglo a matriz
        for (int x = 0; x <4; x++) 
        {
            for (int y = 0; y <4; y++) 
            {
                inicial[x][y] = posini[cont];
                fin[x][y] = finale[cont];
                conini=conini+posini[cont];
                confin=confin+finale[cont];
                if(posini[cont]==finale[cont])
             {
                    soniguales++;
             }
                    cont++;
            }
        }
        if(soniguales==16)
        {
            System.out.println("LA CONFIGURACION INICIAL ES IGUAL A LA CONFIGURACION FINAL :)");
            System.out.println("**POSICION INICIAL**");
            for(int j=0;j<16;j++)
            {
                System.out.print(""+posini[j]+"| ");
                if (j==3 || j==7 || j==11) 
                    System.out.println("");
            }      
            System.out.println("\n**POSICION FINAL**");
            for(int j=0;j<16;j++)
            {
                System.out.print(""+finale[j]+"| ");
                if (j==3 || j==7 || j==11) 
                    System.out.println("");
            } 
   
        }
        else if (conini==120 && confin==120)
        {
            FifteenPuzzle p = new FifteenPuzzle(inicial,fin,fila,fila_finale,posini,finale);
        
            ArrayList link = p.resolver();
            Iterator<Nodo> nombreIterator = link.iterator();
      
            while(nombreIterator.hasNext())
            {
                Nodo elemento = nombreIterator.next();
                System.out.print(elemento.toString()+" / ");
            }
        }
 
        else 
        {
            System.out.println("VERIFIQUE QUE NO EXISTAN DATOS REPETIDOS O QUE NO HAYA VALORES FUERA DEL RANGO");
        }
    }
}
