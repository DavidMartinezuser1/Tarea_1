
package com.practica3.dao;
import com.practica3.domain.Arbol;
import org.springframework.data.jpa.repository.JpaRepository;

//El JPA ya hace la conexión directa a la BD y trae los metodos de insert, select, etc..
public interface ArbolDao extends JpaRepository<Arbol, Long> {
}
