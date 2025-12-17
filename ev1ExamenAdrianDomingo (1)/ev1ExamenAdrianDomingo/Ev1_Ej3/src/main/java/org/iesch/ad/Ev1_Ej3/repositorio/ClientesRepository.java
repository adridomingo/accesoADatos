package org.iesch.ad.Ev1_Ej3.repositorio;

import org.iesch.ad.Ev1_Ej3.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long> {

//    @Query("select c from cliente c join pedido p where p.fecha = ?1")
//    List<Cliente> findPedidosByFecha(String fecha);



}
