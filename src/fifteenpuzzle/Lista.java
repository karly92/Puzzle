/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fifteenpuzzle;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Lista
{
       
    private ArrayList elementos;

     
    public Lista()
    {
        elementos = new ArrayList();
    }

      
    public boolean push(Comparable objeto)
    {
        boolean insertado = false;
        if (objeto != null)
        {   
            elementos.add(objeto);
            Collections.sort(elementos);
            insertado = true;
        }
        return insertado;
    }

        
    public Object pop()
    {
        if (!elementos.isEmpty())
        {
            return elementos.remove(0);
        }
        else
        {
            return null;
        }
    }

    public boolean contains(Nodo n)
    {
        Iterator i = elementos.iterator();
        boolean b= false;
        while(i.hasNext()&&n!=null)
        {
            if(n.toString().equals(i.next().toString()))
                b=true;
        }
      return b;
    }

    public void reordenar()
    {
        Collections.sort(elementos);
    }
        
    public Nodo getElemento(Nodo n)
    {
        int tam = elementos.size();
        Nodo aux= null;
        for(int i = 0; i<tam ; i++)
        {
            if((elementos.get(i)).toString().equals(n.toString()))
                aux=(Nodo)elementos.get(i);
        }
        return aux;
    }
        
    public void removeElementoPos(Nodo n)
    {
        int tam = elementos.size();
        Nodo aux= null;
        for(int i = 0; i<tam ; i++)
        {
            if((elementos.get(i)).toString().equals(n.toString()))
                elementos.remove(i);
                reordenar();
        }
            
    }

    public void clear()
    {
        elementos.clear();
    }

    public int size()
    {
        return elementos.size();
    }

    public String toString()
    {
        return elementos.toString();
    }
        
    public boolean isEmpty()
    {
        return elementos.isEmpty();
    }

    public Object[] toArray()
    {
        int tamano = elementos.size();
        Object[] vector = new Object[tamano];
        for (int i = 0; i < tamano; i++)
        {
            vector[i] = elementos.get(i);
        }
     return vector;
    }
}

