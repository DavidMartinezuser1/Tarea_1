package com.practica3.domain;
import jakarta.persistence.*; //Para asignar todo lo relacionado con base de datos @Entity @Table @Column
import java.io.Serializable; //Para pasar los datos como todo en uno a traves de la red o archivos dentro de la maquina
import lombok.Data;

@Data //Getters y setters
@Entity //definición de que este objeto es una entidad de la BD
@Table(name="arbol")
public class Arbol implements Serializable {
    
    private static final long serializableID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es para hacerlo autoincrementable y no tener que meter más lógica de eso
    @Column(name="id_arbol")
    private Long idArbol;
    @Column(name="ruta_imagen")
    private String rutaImagen;
    private String nombre;
    @Column(name="tipo_flor")
    private String tipoFlor;
    @Column(name="tiempo_vida")
    private int tiempoVida;

    public Arbol(String nombre, String tipoFlor, int tiempoVida) {
        this.nombre = nombre;
        this.tipoFlor = tipoFlor;
        this.tiempoVida = tiempoVida;
    }

    public Arbol() {
    }

    // 👇 Este lo agregás manual
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}
