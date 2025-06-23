
package com.practica3.controllers;
import com.practica3.services.ArbolService;
import com.practica3.domain.Arbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.UUID;

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


    @PostMapping("/guardar")
    public String guardar(@RequestParam("imagen") MultipartFile imagen, @ModelAttribute Arbol arbol) {
        if (!imagen.isEmpty()) {
            try {
                String nombreArchivo = UUID.randomUUID() + "_" + StringUtils.cleanPath(imagen.getOriginalFilename());
                String rutaRelativa = "/img/" + nombreArchivo;
                arbol.setRutaImagen(rutaRelativa);

                Path carpeta = Paths.get("src/main/resources/static/img/");
                if (!Files.exists(carpeta)) {
                    Files.createDirectories(carpeta);
                }

                Path rutaAbsoluta = carpeta.resolve(nombreArchivo);
                Files.write(rutaAbsoluta, imagen.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                // Aquí podrías manejar el error y dar feedback al usuario
            }
        }
        arbolService.save(arbol);
        return "redirect:/arbol/listado";
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
