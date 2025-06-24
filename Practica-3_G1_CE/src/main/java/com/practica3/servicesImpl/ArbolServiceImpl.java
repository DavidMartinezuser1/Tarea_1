
package com.practica3.servicesImpl;

import com.practica3.dao.ArbolDao;
import com.practica3.domain.Arbol;
import com.practica3.services.ArbolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ArbolServiceImpl implements ArbolService {
    
    @Autowired //Crea el objeto sin tener que hacer instancias, lo inyecta directamente
    private ArbolDao arbolDao;

    @Override
    @Transactional(readOnly = true)
    public List<Arbol> getArboles() {
        var list = arbolDao.findAll();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Arbol getArbol(Arbol arbol) {
        arbol = arbolDao.findById(arbol.getIdArbol()).orElse(null);
        return arbol;
    }

    @Override
    @Transactional
    public void save(Arbol arbol) {
        arbolDao.save(arbol);
    }

    @Override
    @Transactional
    public void delete(Arbol arbol) {
        arbolDao.delete(arbol); //Lo borra con respecto al ID, lo hace automatico con el DAO y su libreria
    }
    
}
