
package com.practica3.services;
import com.practica3.domain.Arbol;
import java.util.List;

public interface ArbolService {
    public List<Arbol> getArboles();
    
    public Arbol getArbol(Arbol arbol);
    
    public void save(Arbol arbol);
    
    public void delete(Arbol arbol);
    
}
