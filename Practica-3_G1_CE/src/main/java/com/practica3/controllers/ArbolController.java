
package com.practica3.controllers;
import com.practica3.services.ArbolService;
import com.practica3.domain.Arbol;
import com.practica3.services.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // para las img
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/arbol")
public class ArbolController {
    
    @Autowired
    private ArbolService arbolService;
    
    @GetMapping("/listado")
    public String listado(Model model){ //Vista del listado
        var lista = arbolService.getArboles();
        model.addAttribute("arboles", lista);
        model.addAttribute("arbol", new Arbol());
        return "/arbol/listado";
    }
    
    @Autowired
    FirebaseStorageService firebaseStorageService;
    
    @PostMapping("/guardar")
    public String guardar(Arbol arbol, 
            @RequestParam("imagenFile") MultipartFile imagenFile){
        if (!imagenFile.isEmpty()) {
            arbolService.save(arbol);
            arbol.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "categoria", 
                            arbol.getIdArbol()));
        }
        arbolService.save(arbol);
        return "redirect:/arbol/listado"; //El redirect es para volver a la vista de listado
    }
    
    @GetMapping("/eliminar/{idArbol}")
    public String eliminar(Arbol arbol){
        arbolService.delete(arbol); //no es necesario el get porque esa función solo necesita el Id
        return "redirect:/arbol/listado";
    }
    
    @GetMapping("/modifica/{idArbol}")
    public String modifica(Model model, Arbol arbol){ //Vista del modifica
        arbol = arbolService.getArbol(arbol);
        model.addAttribute("arbol", arbol);
        return "/arbol/modifica";
    }
}
